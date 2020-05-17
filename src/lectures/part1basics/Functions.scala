package lectures.part1basics
/*
Lecture Pointers
 - Functions
            - def keyword is used
            - parameter less function can be called without the parenthesis
            - In Scala (or any functional programming), use recursive function instead of loops
            - Compiler can figure out return type for a non-recursive function
            - Needs explicit return type for a recursive function
            - As best practice, always provide return type
            - Code blocks allow to have auxiliary functions
 */
object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }
  println(aFunction("hello", 4))

  def aParameterLessFunction(): Int = 42
  println(aParameterLessFunction())
  println(aParameterLessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }
  println(aRepeatedFunction("hello", 4))

  // WHEN YOU NEED LOOPS, USE RECURSION.

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)
  println(aFunctionWithSideEffects("hello"))

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b
    aSmallerFunction(n, n-1)
  }
  println(aBigFunction(42))
}
