package exercises
/*
  2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
     fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

  3. compose(f,g) => x => f(g(x))
     andThen(f,g) => x => g(f(x))
 */
object HOFsCurries extends App {

  def toCurry[A, B, C](f: (A, B) => C): (A => B => C) = x => y => f(x, y)
  def fromCurry[A, B, C](f: (A => B => C)): (A, B) => C = (x, y) => f(x)(y)

  println(toCurry[Int, Int, Int](_ + _)(1)(2))
  println(toCurry[Char, Char, String]((x, y) => (x + " " + y))('a')('b'))
  println(fromCurry[Int, Int, Int](x => y => x + y)(1, 2))

  // FunctionX
  def compose[A, B, C](f: B => C, g: A => B): A => C = x => f(g(x))
  def andThen[A, B, C](f: A => B, g: B => C): A => C = x => g(f(x))

  println(compose[Int, Int, Int](2 * _, 3 * _)(5))
  println(andThen[String, String, String]("Rock" + _, _ + "JVM")("The"))

  def superAdder2: (Int => Int => Int) = toCurry(_ + _)
  def add4 = superAdder2(4)
  println(add4(5))

  val superAdder: Int => (Int => Int) = (x: Int) => (y: Int) => x + y
  val simpleAdder = fromCurry(superAdder)
  println(simpleAdder(4, 5))

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2, times3)
  val ordered = andThen(add2, times3)

  println(composed(4))
  println(ordered(4))
}
