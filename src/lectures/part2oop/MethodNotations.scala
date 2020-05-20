package lectures.part2oop
/*
Lecture Pointers
 - Operators in Scala
                     - Methods can be named by just using normal mathematical operators (like + , -)
                     - All operators are methods
 - Method Notations
                   - Infix notation
                                   - Also called as operator notation (example of syntactic sugar)
                                   - works with methods having only one parameter
                                   - method acts like an operator
                                   - called in this way: object method parameter
                   - Prefix notation
                                    - all about unary operators
                                    - are also methods
                                    - unary_ prefix is used by operators
                                    - unary_ prefix only works with some operators (+ - ~ !)
                                    - if parameters are passed then it can be called like a regular method but not using syntactic sugar
                                    - for syntactic sugar no parameters should be passed
                   - Postfix notation
                                    - methods which does not have parameters can be used for postfix notation
 - apply method
               - parenthesis are important
               - can be called without writing apply (by just writing parenthesis after object name)
 - Examples below
 */
object MethodNotations extends App {

  class Person(val name: String, favMovie: String) {
    def likes(movie: String): Boolean = movie == favMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def unary_! : String = s"$name, what the heck?!"
    def isAlive: Boolean = true
    def apply(movie: String): String = s"Hi, my name is $name and I also like $movie"
    def apply(): String  = s"Hi, my name is $name and I like $favMovie"
  }

  val mary = new Person("Mary", "Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // equivalent
  // infix notation = operator notation

  // "operators" in scala
  val tom = new Person("Tom", "Fight Club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS
  // Akka actors have ! ?

  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-
  println(x)
  println(y)

  println(mary.unary_!)
  println(!mary)

  // postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply method
  println(mary.apply("Interstellar"))
  println(mary("Interstellar")) // works with or without parameters
  println(mary()) // apply method overloading
}
