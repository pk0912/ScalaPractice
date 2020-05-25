package lectures.part2oop
/*
Lecture Pointers
 - Inheritance
              - Single class inheritance allowed
              - extending a class means inheriting all non-private vars/vals/members
              - protected modifier allows the vars/vals/methods to be used inside a subclass but not outside the subclass
              - constructor of superclass is called first
              - 'override' keyword is used for overriding vals/vars/methods
              - fields can be overridden directly in the constructor
              - In cases of type substitution (polymorphism), a method call will always go to the most overridden version whenever possible.
              - 'super' keyword is used for calling method of super class
              - 'final' keyword is used before methods to prevent them from getting overridden in child class.
              - final keyword before class can be used to prevent the class from getting extended by other class.
              - 'sealed' keyword is used to prevent the class from getting extended in other file (it allows inheritance in the same file)
 */
object Inheritance extends App {

  // single class inheritance
  class Animal { // use final to prevent inheritance; use sealed to prevent inheritance in other file
    val creatureType = "wild"
    protected def eat(): Unit = println("nomnom") // use final to prevent from overriding
    def species(): Unit = println("Animal")
  }

  class Cat extends Animal {
    def crunch(): Unit = {
      eat()
      println("crunch crunch")
    }
  }
  val cat = new Cat()
  cat.crunch()

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)
  class Adult2(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  class Dog(dogType: String) extends Animal {
    //    override val creatureType: String = "domestic"
    override val creatureType: String = dogType
    override protected def eat(): Unit = println("crunch, crunch")
    def crunch(): Unit = {

      // super
      super.eat()
      eat()
    }
  }
  val dog = new Dog("domestic")
  dog.crunch()
  println(dog.creatureType)

  class Turtle extends Animal {
    override def species(): Unit = println("Reptile")
  }

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Turtle
  unknownAnimal.species()

}
