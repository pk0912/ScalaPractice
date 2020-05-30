package lectures.part3fp
/*
Lecture Pointers
 - Anonymous Functions
                      - Also known as LAMBDA
                      - LAMBDA thing is a value which is basically an instance of Function1 to Function23
                      - While using LAMBDA notation, always use parenthesis to call a function
 */
object AnonymousFunctions extends App {

  // anonymous function (LAMBDA)
//  val doubler = (x: Int) => x * 2
  val doubler: Int => Int = x => x * 2
  println(doubler(2))

  // multiple params in a lambda
//  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b
  val adder: (Int, Int) => Int = (a, b) => a + b
  println(adder(1, 3))

  // np params
//  val justDoSomething: () => Int = () => 3
  val justDoSomething = () => 42

  // careful
  println(justDoSomething) // function itself
  println(justDoSomething()) // call

  // curly braces with lambdas
  val stringToInt: String => Int = {
    x => x.toInt
  }
  println(stringToInt("4"))

  // MORE SYNTACTIC SUGAR
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b
  println(niceIncrementer(3))
  println(niceAdder(1, 3))
}
