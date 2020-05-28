package exercises
/*
  1. Crash your program with an OutOfMemoryError
  2. Crash with StackOverflowError
  3. PocketCalculator
     - add (x,y)
     - subtract (x,y)
     - multiply (x,y)
     - divide (x,y)

     Throw
          - OverflowException if add(x,y) exceeds Int.MAX_VALUE
          - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
          - MathCalculationException for division by 0
 */
object Exceptions extends App {

//  val array = Array.ofDim(Int.MaxValue) // out of memory error

  def selfCallingFunction: String = selfCallingFunction + selfCallingFunction
//  val soe = selfCallingFunction // StackOverflowError

  class OverflowException extends RuntimeException{
    override def getMessage: String = "OverflowException"
  }
  class UnderflowException extends RuntimeException{
    override def getMessage: String = "UnderflowException"
  }
  class MathCalculationException extends RuntimeException{
    override def getMessage: String = "Divide by zero exception"
  }

  object PocketCalculator{
    def add(x: Int, y: Int): Int = {
      val sum = x + y
      if (x > 0 && y > 0 && sum < x) throw new OverflowException
      else if (x < 0 && y < 0 && sum > x) throw new UnderflowException
      else sum
    }

    def subtract(x: Int, y: Int): Int = {
      val difference = x - y
      if (x > 0 && y < 0 && difference < 0) throw new OverflowException
      else if (x < 0 && y > 0 && difference > 0) throw new UnderflowException
      else difference
    }

    def multiply(x: Int, y: Int): Int = {
      val product = x * y
      if (x > 0 && y > 0 && product < x) throw new OverflowException
      else if (x < 0 && y < 0 && product > x) throw new OverflowException
      else if (x > 0 && y < 0 && product < x) throw new UnderflowException
      else if (x < 0 && y > 0 && product > x) throw new UnderflowException
      else product
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

//  println(PocketCalculator.add(Int.MaxValue, 1)) // overflow exception
//  println(PocketCalculator.add(-1, Int.MinValue)) // underflow exception
//  println(PocketCalculator.subtract(Int.MaxValue, -1)) // overflow exception
//  println(PocketCalculator.subtract(-2, Int.MaxValue)) // underflow exception
//  println(PocketCalculator.multiply(2, Int.MaxValue)) // overflow exception
//  println(PocketCalculator.multiply(Int.MinValue, -2)) // overflow exception
//  println(PocketCalculator.multiply(Int.MaxValue, -2)) // underflow exception
//  println(PocketCalculator.multiply(2, Int.MinValue)) // underflow exception
//  println(PocketCalculator.divide(Int.MinValue, 0)) // MathCalculationException
}
