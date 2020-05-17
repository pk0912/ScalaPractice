package exercises

object Functions extends App {

  /*
    1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old."
   */
  def aGreetingFunction(name: String, age: Int): String = "Hi, my name is " + name + " and I am " + age + " years old."
  println(aGreetingFunction("Praveen", 26))

  /*
    2. Factorial function 1 * 2 * 3 * .. * n
   */
  def aFactorialFunction(n: Int): Int = {
    if (n <= 1) 1
    else n * aFactorialFunction(n-1)
  }
  println(aFactorialFunction(0))
  println(aFactorialFunction(1))
  println(aFactorialFunction(5))
  println(aFactorialFunction(10))

  /*
    3. A Fibonacci function
       f(1) = 1
       f(2) = 1
       f(n) = f(n - 1) + f(n - 2)
   */
  def aFibonacciFunction(n: Int): Int = {
    if (n <= 2) 1
    else aFibonacciFunction(n - 1) + aFibonacciFunction(n - 2)
  }
  println(aFibonacciFunction(0))
  println(aFibonacciFunction(1))
  println(aFibonacciFunction(2))
  println(aFibonacciFunction(3))
  println(aFibonacciFunction(4))
  println(aFibonacciFunction(5))
  println(aFibonacciFunction(8))

  /*
    4. A Prime Function
   */
  def isPrime(n: Int): Boolean = {
    @scala.annotation.tailrec
    def isPrimeUntil(t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)
    }
    isPrimeUntil(n / 2)
  }
  println(isPrime(1))
  println(isPrime(2))
  println(isPrime(3))
  println(isPrime(4))
  println(isPrime(5))
  println(isPrime(6))
  println(isPrime(7))
  println(isPrime(37))
  println(isPrime(2003))
  println(isPrime(37 * 17))
}
