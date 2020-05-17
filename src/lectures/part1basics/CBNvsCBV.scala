package lectures.part1basics
/*
Lecture Pointers
  - Call By Value
                 - Exact value is computed before & then passed
  - Call By Name
                - Uses => operator
                - Expression is passed literally and is called and evaluated the time it is used within the function
                - Lazy Evaluation
 */
object CBNvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println("by value : " + x)
    println("by value : " + x)
  }

  def calledByName(x: => Long): Unit = {
    println("by name : " + x)
    println("by name : " + x)
  }

  calledByValue(System.nanoTime())
  calledByName(System.nanoTime())

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int): Unit = println(x)

//  printFirst(infinite(), 42) // This will result in stackoverflow error
  printFirst(42, infinite()) // This works fine because the infinite loop is not called, so its not evaluated

}
