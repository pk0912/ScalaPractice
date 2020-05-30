package lectures.part3fp
/*
Lecture Pointers
 - Functional Programming
                         - DREAM : use functions as first class elements
                         - Function Types
                                         - 23 Different available Functions
                                         - Supports up to 22 parameters
                                         - Ex: Function2[A, B, R] === (A,B) => R
                                         - All scala functions are objects.
                         - Higher Order Function
                                                - Which either has a function as its parameter or returns a function
                         - Curried Function - A function which returns another function
 */
object WhatsAFunction extends App {

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(2))

  // function types = Function[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  println(stringToIntConverter("4") + 5)

  val adder: (Int, Int) => Int = (v1: Int, v2: Int) => v1 + v2 // Syntactic sugar
  println(adder(4, 5))
}

trait MyFunction[A, B] {
  def apply(element: A): B
}
