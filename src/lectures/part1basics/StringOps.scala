package lectures.part1basics
/*
Lecture Pointers
 - String Operations
                    => +: is a prepending operator
                    => :+ is an appending operator
                    - Scala specific
                                    - String interpolator
                                                         => s-interpolator
                                                                          - Uses s-prefix and $ sign to substitute variable values
                                                                          - can evaluate complex expressions (use curly braces)
                                                         => f-interpolator
                                                                          - can work as s-interpolator
                                                                          - can receive printf like format (see example below)
                                                                          - uses f-prefix
                                                                          - can check for type corrections
                                                         => raw-interpolator
                                                                            - can work as s-interpolator
                                                                            - it can print the characters literally
                                                                            - uses raw-prefix
                                                                            - ignores escape character if inserted as a value
 */
object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  println(str.charAt(2))
  println(str.substring(7, 11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str)
  println(str.toLowerCase())
  println(str.length())
  println(str.length)

  val aNumberString = "2"
  val aNumber = aNumberString.toInt
  println(aNumberString)
  println(aNumber)
  println(aNumberString.toList)
  println('a' +: aNumberString :+ 'z') // a2z
  println("a" +: aNumberString :+ "z") // Vector(a, 2, z)
  println('a' + aNumberString + 'z') // a2z
  println("a" + aNumberString + "z") // a2z
  println('a' +: aNumberString) // a2
  println(aNumberString :+ 'z') //2z
  println(aNumber + 'z') //124
  println(aNumber + "z") //2z

  println(str.reverse)
  println(str.take(2))

  // SCALA-specific: String interpolator

  // s-interpolator
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old."
  println(greeting)

  val anotherGreeting = s"Hello, my name is $name and I will be turning ${age + 1} years old."
  println(anotherGreeting)

  // f-interpolator
  val speed = 1.2f
  val myth = f"$name can eat $speed%2.4f burgers per minute."
  println(myth)

  // raw-interpolator
  println(raw"This is a \n newline character.")
  val escaped = "This is a \n newline."
  println(raw"$escaped")

}
