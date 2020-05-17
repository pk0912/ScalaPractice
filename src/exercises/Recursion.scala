package exercises

import scala.annotation.tailrec

object Recursion extends App {
  /*
    1. Concatenate a string n times
   */
  def concatenateString(aString: String, n: Int): String = {
    @tailrec
    def handler(x: Int, accumulator: String): String = {
      if (x <= 1) accumulator
      else handler(x-1, accumulator+aString)
    }
    if (n <= 0) ""
    else handler(n, aString)
  }
  println(concatenateString("Pk", 4))

  /*
    2. IsPrime function tail recursive
   */
  def isPrime(n: Int): Boolean = {
    @tailrec
    def handler(x: Int, accumulator: Boolean): Boolean = {
      if (!accumulator) false
      else if (x < 2) accumulator
      else handler(x-1, accumulator && n % x != 0)
    }
    handler(n / 2, accumulator = true)
  }
  println(isPrime(2))
  println(isPrime(3))
  println(isPrime(4))
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 2003))

  /*
    3. Fibonacci function, tail recursive
   */
  def fibonacci(n: Int): Int = {
    @tailrec
    def handler(x: Int, prev: Int, secondPrev: Int): Int = {
      if (x <= 2) prev
      else handler(x-1, prev + secondPrev, prev)
    }
    handler(n, 1, 1)
  }
  println(fibonacci(1))
  println(fibonacci(2))
  println(fibonacci(3))
  println(fibonacci(4))
  println(fibonacci(5))
  println(fibonacci(6))
  println(fibonacci(7))
  println(fibonacci(8))
}
