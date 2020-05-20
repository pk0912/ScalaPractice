package exercises

/*
  1. Overload the + operator
     mary + "the rockstar" => new Person "Mary (the rockstar)"

  2. Add an age to the Person class
     Add a unary + operator => new Person with the age + 1
     +mary => mary with the age incremented

  3. Add a "learns" method in the Person class => "Mary learns Scala"
     Add a learnsScala method, calls learns method with "Scala".
     Use it in postfix notation.

  4. Overload the apply method
     mary.apply(2) => "Mary watched Inception 2 times."
 */
object MethodNotations extends App {

  class Person(val name: String, favMovie: String, val age: Int) {
    def likes(movie: String): Boolean = movie == favMovie

    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(alias: String): Person = new Person(name + "(" + alias + ")", favMovie, age) // Exercise 1

    def unary_! : String = s"$name, what the heck?!"
    def unary_+ : Person = new Person(name, favMovie, age + 1) // Exercise 2

    def isAlive: Boolean = true

    def learns(language: String): String = s"$name learns $language" // Exercise 3.1
    def learnsScala: String = this learns "Scala" // Exercise 3.2
//    def learnsScala: String - learns("Scala")

    def apply(movie: String): String = s"Hi, my name is $name and I also like $movie"
    def apply(): String  = s"Hi, my name is $name and I like $favMovie"
    def apply(n: Int): String = s"$name watched $favMovie $n times." // Exercise 4
  }

  val mary = new Person("Mary", "Inception", 25)

  println((mary + "the rockstar")())
  val newMary = mary + "the rockstar"
  println(newMary.name)

  println((+mary).age)
  val oldMary = +mary
  println(oldMary.age)

  println(mary learnsScala)

  println(mary(2))
}
