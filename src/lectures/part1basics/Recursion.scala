package lectures.part1basics

import scala.annotation.tailrec

/*
  Lecture Pointers
    - Recursion
               - Stack Recursion
                                - The JVM on which Scala runs, maintains a call stack to keep partial result to compute the desired result
                                - This approach is vulnerable to runtime exception, i.e. StackOverflowError
               - Tail Recursion
                               - It reuses the call stack
                               - It works by accumulating the result and passing it as the parameter of the recursively called function
                               - This approach addresses the shortcomings of the Stack recursion approach
 */
object Recursion extends App {

  def aFactorialFunction(n: Int): BigInt = {
    if (n <= 1) {
      println("Computed factorial of " + n)
      1
    }
    else {
      println("Computing factorial of " + n + " - I first need factorial of " + (n-1))
      val result = n * aFactorialFunction(n-1)
      println("Computed factorial of " + n)
      result
    }
  }
  println(aFactorialFunction(3497)) // Error for 3498

  def anotherFactorialFunction(n: Int): BigInt = {
    @tailrec
    def handler(x: Int, accumulator: BigInt): BigInt = {
      if (x <= 1) accumulator
      else handler(x-1, x * accumulator)
    }
    handler(n, 1)
  }
  println(anotherFactorialFunction(5000))
}
