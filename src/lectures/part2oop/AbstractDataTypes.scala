package lectures.part2oop
/*
Lecture Pointers
 - Abstract
           - abstract members -> unimplemented vars/vals/methods
           - abstract class -> class with abstract members
           - abstract class is a type of thing
           - only one abstract class can be inherited by a class at a time
           - 'abstract' keyword is used to make a class abstract
           - abstract class can't be instantiated
           - override keyword is not mandatory; compiler can figure it out
           - can have both abstract and non-abstract members
 - traits
         - traits are behaviour (what a class do)
         - 'trait' keyword is used
         - contains abstract vars/vals/methods
         - can have non-abstract members as well
         - traits do not have constructor
         - multiple traits can be inherited by the same class
         - 'with' keyword is used by a class for inheriting a trait
 - Scala's Type Hierarchy :
                           scala.Any
                              /|\
                       ________|_________
                     /|\                /|\
                      |                  |
                 scala.AnyVal       scala.AnyRef
                 (Int, Unit,        (java.lang.Object)
                 Boolean,           ( String, Set,
                 Float....)          List...)
                     /|\           /|\      /|\      /|\
                      |             |________|________|
                      |                     /|\
                      |                      |
                      |                  scala.Null
                      |                     /|\
                      |            __________|
                      |           /
                    scala.Nothing
 */
object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String = "wild"
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine" // non-abstract val
    override def eat: Unit = println("crunch crunch")
  }

  val dog = new Dog
  dog.eat

  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "fresh meat" // non-abstract val
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc" // since this val is not abstract, override keyword is compulsory

    def eat: Unit = println("nomnomnom") // override keyword is optional

    override def eat(animal: Animal): Unit = println(s"I'm a $creatureType and I'm eating a ${animal.creatureType}")
  }

  val crocodile = new Crocodile
  crocodile.eat
  crocodile.eat(dog)

}
