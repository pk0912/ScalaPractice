package exercises

abstract class Maybe[+T]() {
  def map[B](transformer: T => B): Maybe[B]

  def flatMap[B](transformer: T => Maybe[B]): Maybe[B]

  def filter(predicate: T => Boolean): Maybe[T]
}

case object EmptyMaybe extends Maybe[Nothing] {
  override def map[B](transformer: Nothing => B): Maybe[B] = EmptyMaybe

  override def flatMap[B](transformer: Nothing => Maybe[B]): Maybe[B] = EmptyMaybe

  override def filter(predicate: Nothing => Boolean): Maybe[Nothing] = EmptyMaybe
}

case class ConsMaybe[+A](element: A) extends Maybe[A] {
  override def map[B](transformer: A => B): Maybe[B] = ConsMaybe(transformer(element))

  override def flatMap[B](transformer: A => Maybe[B]): Maybe[B] = transformer(element)

  override def filter(predicate: A => Boolean): Maybe[A] =
    if (predicate(element)) this
    else EmptyMaybe
}


object MapFlatmapFilterFor extends App {

  val element = ConsMaybe[Int](2)
  println(element)
  println(element.map(_ * 2))
  println(element.filter(_ % 2 == 0))
  println(element.filter(_ % 2 != 0))
  println(element.flatMap(x => ConsMaybe(x * 4)))
  println(element.flatMap(x => (ConsMaybe(x % 2 == 0))))
}
