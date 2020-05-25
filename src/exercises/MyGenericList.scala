package exercises
/*
 Generic implementation of Singly Linked List
 */
abstract class MyGenericList[+A] {
  def head: A
  def tail: MyGenericList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyGenericList[B]
  def printElements: String
  // polymorphic call
  override def toString: String = "[" + printElements + "]"
}

object GenericEmpty extends MyGenericList[Nothing] {
  override def head: Nothing = throw new NoSuchElementException

  override def tail: MyGenericList[Nothing] = throw new NoSuchElementException

  override def isEmpty: Boolean = true

  override def add[B >: Nothing](element: B): MyGenericList[B] = new GenericCons(element, GenericEmpty)

  override def printElements: String = ""
}

class GenericCons[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A] {
  override def head: A = h

  override def tail: MyGenericList[A] = t

  override def isEmpty: Boolean = false

  override def add[B >: A](element: B): MyGenericList[B] = new GenericCons(element, this)

  override def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }
}

object GenericTest extends App {
  val listOfIntegers: MyGenericList[Int] = new GenericCons[Int](1, new GenericCons[Int](2, new GenericCons[Int](3, GenericEmpty)))
  val listOfChar: MyGenericList[Char] = new GenericCons[Char]('a', new GenericCons[Char]('b', new GenericCons[Char]('c', GenericEmpty)))

  println(listOfIntegers.toString)
  println(listOfChar.toString)

  println(listOfChar.add[Char]('d').toString)
}
