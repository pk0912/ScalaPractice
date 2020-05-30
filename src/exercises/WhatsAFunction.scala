package exercises
/*
  1. a function takes 2 strings and concatenates them
  2. transform the MyPredicate and MyTransformer into function types
  3. define a function which takes an Int and returns another function which takes an int and returns an int
     - what's the type of this function
     - how to do it
 */
object WhatsAFunction extends App {

  def concatenateStrings: (String, String) => String = (v1: String, v2: String) => v1 + v2
  def concatStrings = new ((String, String) => String) {
    override def apply(v1: String, v2: String): String = v1 + v2
  }
  println(concatenateStrings("Hello ", "World"))
  println(concatStrings("Hello ", "World"))

  def superAdder: (Int) => Function1[Int, Int] = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Function1[Int, Int] = new Function[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }
  // shorthand expression for above expression
  def supAdd: (Int) => ((Int) => Int) = (v1: Int) => (v2: Int) => v1 + v2

  println(superAdder(1))
  println(superAdder(1)(1))
  println(supAdd(1))
  println(supAdd(1)(1)) // curried function
}
