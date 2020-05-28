package lectures.part2oop
/*
Lecture Pointers
 - Exceptions
             - 'throw' keyword to throw an exception
             - throwing an exception is an expression in scala
             - throwable classes extend the Throwable class
             - Exception and Error are the major Throwable subtypes.
             - Exception denotes something that went wrong with program like NullPointerException
             - Error denotes something that happened wrong with JVM like StackOverflowError
             - RuntimeException gets a parameter for message.
             - Exceptions are JVM specific thing.
             - Value of try-catch block is the unification of both of the return types of try and catch block.
             - finally is optional & does not influence the return type of expression.
             - use finally for side effects
             - For defining custom exception class inherit the Exception class.
             - can write only try block.
 */
object Exceptions extends App {

  val x: String = null
//  println(x.length) // This will crash with a NullPointerException

  // throwing and catching exceptions
  // 1. throwing exceptions
//  val aWeirdValue = throw new NullPointerException // It returns nothing
//  val aWeirdValue: String = throw new NullPointerException // It returns String

  // 2. how to catch exceptions
  def getInt(withExceptions: Boolean): Int = {
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42
  }

  val aTryCatchValue = try {
    getInt(false)
  }

  println(aTryCatchValue)

  val potentialFail = try {
    // code that might throw
    getInt(true)
    throw new NullPointerException
  }
  catch {
    case f: NullPointerException => {
      44
    }
    case e: RuntimeException => {
      println(e)
      43
    }
  }
  finally {
    // code that will get executed NO MATTER WHAT
    println("finally")
  }
  println(potentialFail)

  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException
  throw exception

}
