package exercises
/*
  1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
  2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
  3. MyList:
           - map(transformer) => MyList
           - filter(predicate) => MyList
           - flatMap(transformer from A to MyList[B]) => MyList[B]

     class EvenPredicate extends MyPredicate[Int]
     class StringToIntTransformer extendsMyTransformer[String, Int]

     [1,2,3].map(n * 2) = [2,4,6]
     [1,2,3,4].filter(n % 2) = [2,4]
     [1,2,3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]
 */

trait MyPredicate[-T] {
  def test(t: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(a: A): B
}

abstract class MyExtendedGenericList[+A]() {
  def head: A
  def tail: MyExtendedGenericList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyExtendedGenericList[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"

  def ++[B >: A](list: MyExtendedGenericList[B]): MyExtendedGenericList[B]
  def map[B](transformer: MyTransformer[A, B]): MyExtendedGenericList[B]
  def flatMap[B](transformer: MyTransformer[A, MyExtendedGenericList[B]]): MyExtendedGenericList[B]
  def filter(predicate: MyPredicate[A]): MyExtendedGenericList[A]
}

case object ExtendedGenericEmpty extends MyExtendedGenericList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyExtendedGenericList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyExtendedGenericList[B] =
    new ExtendedGenericCons(element, ExtendedGenericEmpty)

  override def printElements: String = ""

  override def ++[B >: Nothing](list: MyExtendedGenericList[B]): MyExtendedGenericList[B] = list

  override def map[B](transformer: MyTransformer[Nothing, B]): MyExtendedGenericList[B] = ExtendedGenericEmpty

  override def flatMap[B](transformer: MyTransformer[Nothing, MyExtendedGenericList[B]]): MyExtendedGenericList[B] =
    ExtendedGenericEmpty

  override def filter(predicate: MyPredicate[Nothing]): MyExtendedGenericList[Nothing] = ExtendedGenericEmpty
}

case class ExtendedGenericCons[+A](h: A, t: MyExtendedGenericList[A]) extends MyExtendedGenericList[A] {
  override def head: A = h

  override def tail: MyExtendedGenericList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyExtendedGenericList[B] = new ExtendedGenericCons(element, this)

  override def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }

  /*
    [1,2,3] ++ [4,5]
    = new ExtendedGenericCons(1, [2,3] ++ [4,5])
    = new ExtendedGenericCons(1, new ExtendedGenericCons(2, [3] ++ [4,5]))
    = new ExtendedGenericCons(1, new ExtendedGenericCons(2, new ExtendedGenericCons(3, ExtendedGenericEmpty ++ [4,5])))
    = new ExtendedGenericCons(1, new ExtendedGenericCons(2, new ExtendedGenericCons(3, new ExtendedGenericCons(4, new ExtendedGenericCons(5, ExtendedGenericEmpty)
   */
  override def ++[B >: A](list: MyExtendedGenericList[B]): MyExtendedGenericList[B] =
    new ExtendedGenericCons[B](h, t ++ list)

  /*
    [1,2,3].map(n * 2)
      = new ExtendedGenericCons(2, [2,3].map(n * 2))
      = new ExtendedGenericCons(2, new ExtendedGenericCons(4, [3].map(n * 2)))
      = new ExtendedGenericCons(2, new ExtendedGenericCons(4, new ExtendedGenericCons(6, ExtendedGenericEmpty.map(n * 2))))
      = new ExtendedGenericCons(2, new ExtendedGenericCons(4, new ExtendedGenericCons(6, ExtendedGenericEmpty)))
   */
  override def map[B](transformer: MyTransformer[A, B]): MyExtendedGenericList[B] =
    new ExtendedGenericCons[B](transformer.transform(h), t.map(transformer))

  /*
    [1,2].flatMap(n => [n, n+1])
      = [1,2] ++ [2].flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ ExtendedGenericEmpty.flatMap(n => [n, n+1])
      = [1,2] ++ [2,3] ++ ExtendedGenericEmpty
      = [1,2,2,3]
   */
  override def flatMap[B](transformer: MyTransformer[A, MyExtendedGenericList[B]]): MyExtendedGenericList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  /*
    [1,2,3].filter(n % 2 == 0)
      = [2,3].filter(n % 2 == 0)
      = new ExtendedGenericCons(2, [3].filter(n % 2 == 0))
      = new ExtendedGenericCons(2, ExtendedGenericEmpty.filter(n % 2 == 0))
      = new ExtendedGenericCons(2, ExtendedGenericEmpty)
   */
  override def filter(predicate: MyPredicate[A]): MyExtendedGenericList[A] = {
    if (predicate.test(h)) new ExtendedGenericCons[A](h, t.filter(predicate))
    else t.filter(predicate)
  }
}

object ExtendedGenericTest extends App {
  val listOfIntegers: MyExtendedGenericList[Int] =
    new ExtendedGenericCons[Int](1, new ExtendedGenericCons[Int](2, new ExtendedGenericCons[Int](3, ExtendedGenericEmpty)))
  val cloneListOfIntegers: MyExtendedGenericList[Int] =
    new ExtendedGenericCons[Int](1, new ExtendedGenericCons[Int](2, new ExtendedGenericCons[Int](3, ExtendedGenericEmpty)))
  val anotherListOfIntegers: MyExtendedGenericList[Int] =
    new ExtendedGenericCons[Int](4, new ExtendedGenericCons[Int](5, ExtendedGenericEmpty))
  val listOfChar: MyExtendedGenericList[Char] =
    new ExtendedGenericCons[Char]('a', new ExtendedGenericCons[Char]('b', new ExtendedGenericCons[Char]('c', ExtendedGenericEmpty)))

  println(listOfIntegers.toString)
  println(listOfChar.toString)
  println(listOfChar.add[Char]('d').toString)

  println(anotherListOfIntegers.toString)

  println(listOfIntegers.map(new MyTransformer[Int, Int] {
    override def transform(a: Int): Int = a * 2
  }).toString)
  // shorthand representation of above expression
  println(listOfIntegers.map((a: Int) => a * 2).toString)

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(t: Int): Boolean = t % 2 == 0
  }).toString)
  // shorthand representation of above expression
  println(listOfIntegers.filter((t: Int) => t % 2 == 0).toString)

  println(listOfIntegers ++ anotherListOfIntegers)

  println(listOfIntegers.flatMap(new MyTransformer[Int, MyExtendedGenericList[Int]] {
    override def transform(a: Int): MyExtendedGenericList[Int] = new ExtendedGenericCons[Int](a, new ExtendedGenericCons[Int](a + 1, ExtendedGenericEmpty))
  }))

  println(cloneListOfIntegers == listOfIntegers) // because of case class (equals method is implemented out of the box)
}
