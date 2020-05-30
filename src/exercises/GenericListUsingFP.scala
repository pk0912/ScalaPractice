package exercises

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
}

case object GenericEmptyUsingFP extends GenericListUsingFP[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: GenericListUsingFP[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): GenericListUsingFP[B] =
    new ExtendedGenericConsFP(element, GenericEmptyUsingFP)

  override def printElements: String = ""

  override def ++[B >: Nothing](list: GenericListUsingFP[B]): GenericListUsingFP[B] = list

  override def map[B](transformer: Nothing => B): GenericListUsingFP[B] = GenericEmptyUsingFP

  override def flatMap[B](transformer: Nothing => GenericListUsingFP[B]): GenericListUsingFP[B] =
    GenericEmptyUsingFP

  override def filter(predicate: Nothing => Boolean): GenericListUsingFP[Nothing] = GenericEmptyUsingFP
}

case class ExtendedGenericConsFP[+A](h: A, t: GenericListUsingFP[A]) extends GenericListUsingFP[A] {
  override def head: A = h

  override def tail: GenericListUsingFP[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): GenericListUsingFP[B] = new ExtendedGenericConsFP(element, this)

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
}

object GenericListUsingFPTest extends App {
  val listOfIntegers: GenericListUsingFP[Int] =
    new ExtendedGenericConsFP[Int](1, new ExtendedGenericConsFP[Int](2, new ExtendedGenericConsFP[Int](3, GenericEmptyUsingFP)))
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

  println(listOfIntegers.map((a: Int) => a * 2).toString)

  // shorthand representation of above expression
  println(listOfIntegers.filter((t: Int) => t % 2 == 0).toString)

  println(listOfIntegers ++ anotherListOfIntegers)

  println(listOfIntegers.flatMap((a: Int) => new ExtendedGenericConsFP[Int](a, new ExtendedGenericConsFP[Int](a + 1, GenericEmptyUsingFP))))


  println(cloneListOfIntegers == listOfIntegers) // because of case class (equals method is implemented out of the box)
}

