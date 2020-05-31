package exercises
/*
  1. Expand GenericListUsingFP
     - foreach method A => Unit
       [1,2,3].foreach(x => println(x))

     - sort function ((A, A) => Int) => GenericListUsingFP
       [1,2,3].sort((x,y) => y - x) => [3,2,1]

     - zipWith(list, (A, A) => B) => GenericListUsingFP
       [1,2,3].zipWith([4,5,6], x * y) => [1 * 4, 2 * 5, 3 * 6] = [4, 10, 18]

     - fold(start)(function) => a Value
       [1,2,3].fold(0)(x + y) = 6

  2. GenericListUsingFP supports for comprehensions?
 */
abstract class GenericListUsingFP[+A]() {
  def head: A
  def tail: GenericListUsingFP[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): GenericListUsingFP[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  def ++[B >: A](list: GenericListUsingFP[B]): GenericListUsingFP[B]

  // Higher Order Function
  def map[B](transformer: A => B): GenericListUsingFP[B]
  def flatMap[B](transformer: A => GenericListUsingFP[B]): GenericListUsingFP[B]
  def filter(predicate: A => Boolean): GenericListUsingFP[A]

  def foreach(f: A => Unit)
  def sort(compare: (A, A) => Int): GenericListUsingFP[A]
  def zipWith[B, C](list: GenericListUsingFP[B], f: (A, B) => C): GenericListUsingFP[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object GenericEmptyUsingFP extends GenericListUsingFP[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: GenericListUsingFP[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): GenericListUsingFP[B] =
    ExtendedGenericConsFP(element, GenericEmptyUsingFP)

  override def printElements: String = ""

  override def ++[B >: Nothing](list: GenericListUsingFP[B]): GenericListUsingFP[B] = list

  override def map[B](transformer: Nothing => B): GenericListUsingFP[B] = GenericEmptyUsingFP

  override def flatMap[B](transformer: Nothing => GenericListUsingFP[B]): GenericListUsingFP[B] =
    GenericEmptyUsingFP

  override def filter(predicate: Nothing => Boolean): GenericListUsingFP[Nothing] = GenericEmptyUsingFP

  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int): GenericListUsingFP[Nothing] = GenericEmptyUsingFP

  override def zipWith[B, C](list: GenericListUsingFP[B], f: (Nothing, B) => C): GenericListUsingFP[C] =
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length!!")
    else GenericEmptyUsingFP

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class ExtendedGenericConsFP[+A](h: A, t: GenericListUsingFP[A]) extends GenericListUsingFP[A] {
  override def head: A = h

  override def tail: GenericListUsingFP[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): GenericListUsingFP[B] = ExtendedGenericConsFP(element, this)

  override def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }

  /*
    [1,2,3] ++ [4,5]
    = new ExtendedGenericConsFP(1, [2,3] ++ [4,5])
    = new ExtendedGenericConsFP(1, new ExtendedGenericConsFP(2, [3] ++ [4,5]))
    = new ExtendedGenericConsFP(1, new ExtendedGenericConsFP(2, new ExtendedGenericConsFP(3, GenericEmptyUsingFP ++ [4,5])))
    = new ExtendedGenericConsFP(1, new ExtendedGenericConsFP(2, new ExtendedGenericConsFP(3, new ExtendedGenericConsFP(4, new ExtendedGenericConsFP(5, GenericEmptyUsingFP)
   */
  override def ++[B >: A](list: GenericListUsingFP[B]): GenericListUsingFP[B] =
    new ExtendedGenericConsFP[B](h, t ++ list)

  /*
    [1,2,3].map(n * 2)
      = new ExtendedGenericConsFP(2, [2,3].map(n * 2))
      = new ExtendedGenericConsFP(2, new ExtendedGenericConsFP(4, [3].map(n * 2)))
      = new ExtendedGenericConsFP(2, new ExtendedGenericConsFP(4, new ExtendedGenericConsFP(6, GenericEmptyUsingFP.map(n * 2))))
      = new ExtendedGenericConsFP(2, new ExtendedGenericConsFP(4, new ExtendedGenericConsFP(6, GenericEmptyUsingFP)))
   */
  override def map[B](transformer: A => B): GenericListUsingFP[B] =
    new ExtendedGenericConsFP[B](transformer(h), t.map(transformer))

  /*
    [1,2].flatMap(n => [n, n+1])
      = [1,2] ++ [2].flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ GenericEmptyUsingFP.flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ GenericEmptyUsingFP
      = [1,2,2,3]
   */
  override def flatMap[B](transformer: A => GenericListUsingFP[B]): GenericListUsingFP[B] =
    transformer(h) ++ t.flatMap(transformer)

  /*
    [1,2,3].filter(n % 2 == 0)
      = [2,3].filter(n % 2 == 0)
      = new ExtendedGenericConsFP(2, [3].filter(n % 2 == 0))
      = new ExtendedGenericConsFP(2, GenericEmptyUsingFP.filter(n % 2 == 0))
      = new ExtendedGenericConsFP(2, GenericEmptyUsingFP)
   */
  override def filter(predicate: A => Boolean): GenericListUsingFP[A] = {
    if (predicate(h)) new ExtendedGenericConsFP[A](h, t.filter(predicate))
    else t.filter(predicate)
  }

  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  override def sort(compare: (A, A) => Int): GenericListUsingFP[A] = {
    def insert(x: A, sortedList: GenericListUsingFP[A]): GenericListUsingFP[A] = {
      if (sortedList.isEmpty) ExtendedGenericConsFP(x, GenericEmptyUsingFP)
      else if (compare(x, sortedList.head) <= 0) ExtendedGenericConsFP(x, sortedList)
      else ExtendedGenericConsFP(sortedList.head, insert(x, sortedList.tail))
    }
    val sortedTail = t.sort(compare)
    insert(h, sortedTail)
  }

  override def zipWith[B, C](list: GenericListUsingFP[B], f: (A, B) => C): GenericListUsingFP[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length!!")
    else ExtendedGenericConsFP(f(h, list.head), t.zipWith(list.tail, f))
  }

  override def fold[B](start: B)(operator: (B, A) => B): B = t.fold(operator(start, h))(operator)
}

object GenericListUsingFPTest extends App {
  val listOfIntegers: GenericListUsingFP[Int] =
    new ExtendedGenericConsFP[Int](10, new ExtendedGenericConsFP[Int](2, new ExtendedGenericConsFP[Int](6, GenericEmptyUsingFP)))
  val cloneListOfIntegers: GenericListUsingFP[Int] =
    new ExtendedGenericConsFP[Int](1, new ExtendedGenericConsFP[Int](2, new ExtendedGenericConsFP[Int](3, GenericEmptyUsingFP)))
  val anotherListOfIntegers: GenericListUsingFP[Int] =
    new ExtendedGenericConsFP[Int](4, new ExtendedGenericConsFP[Int](5, GenericEmptyUsingFP))
  val listOfChar: GenericListUsingFP[Char] =
    new ExtendedGenericConsFP[Char]('a', new ExtendedGenericConsFP[Char]('b', new ExtendedGenericConsFP[Char]('c', GenericEmptyUsingFP)))

  println(listOfIntegers.toString)
  println(listOfChar.toString)
  println(listOfChar.add[Char]('d').toString)

  println(anotherListOfIntegers.toString)

  println(listOfIntegers.map(a => a * 2).toString)
  // another shorthand way for above expression
  println(listOfIntegers.map(_ * 2).toString)

  println(listOfIntegers.filter(t => t % 2 == 0).toString)
  // another shorthand way for above expression
  println(listOfIntegers.filter(_ % 2 == 0).toString)

  println(listOfIntegers ++ anotherListOfIntegers)

  println(listOfIntegers.flatMap(a => new ExtendedGenericConsFP[Int](a, new ExtendedGenericConsFP[Int](a + 1, GenericEmptyUsingFP))))
  // not for the above expression as the parameter to the anonymous function is getting used twice, but multiple use of underscore refers to multiple parameters


  println(cloneListOfIntegers == listOfIntegers) // because of case class (equals method is implemented out of the box)

  listOfIntegers.foreach(println)

  println(listOfIntegers.sort((x, y) => y - x))

  println(listOfIntegers.zipWith[Char, String](listOfChar, (x: Int, y: Char) => x + "" + y))

  // shorthand notation for above expression
  println(listOfIntegers.zipWith[Char, String](listOfChar, _ + "" + _))

  println(listOfIntegers.fold(0)(_ + _))
  println(listOfChar.fold("")(_ + "" + _))

  for {
    n <- listOfIntegers
  } println(n)

  val forCombinations = for {
    c <- listOfChar
    n <- listOfIntegers
  } yield "" + c + n
  println(forCombinations)
}

