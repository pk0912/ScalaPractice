package lectures.part4pm

import scala.util.Random

/*
Lecture Pointers
 - Pattern Matching
                   - match and case keywords are used
                   - '_' used as wildcard
                   - cases are matched in order
                   - scala.MatchError if no cases match
                   - type of pattern matching expression - unification of return types of all cases
                   - sealed classes help in covering all possible cases by giving warning at compile time
                   - Pattern matching works really well with case classes
 */
object PatternMatching extends App {

  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val desc = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    case _ => "something else" // _ = WILDCARD
  }

  println(x)
  println(desc)

  // Decompose values
  case class Person(name: String, age: Int)
  val bob = new Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I am not adult." // here if condition acts as a guard
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know who I am"
  }
  println(greeting)

  // PM on sealed hierarchies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Cat(breed: String) extends Animal

  val animal: Animal = Dog("Terra Nova")
  animal match {
    case Dog(someBreed) => println(s"matched a dog of some $someBreed breed")
  }
}
