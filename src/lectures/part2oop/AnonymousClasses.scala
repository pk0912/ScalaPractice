package lectures.part2oop
/*
Lecture Pointers
 - Anonymous Classes
                    - When instantiating a class or trait and overriding fields or methods on the spot, compiler creates an anonymous class that extends the class that we are trying to instantiate and then create an instance of that anonymous class.
                    - We need to pass in the required constructor arguments if needed.
                    - Also, we need to implement all abstract fields and methods if there are any.
 */
object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("eat eat")
  }
  /*
    equivalent with

    class AnonymousClasses$$anon$1 extends Animal {
      override def eat: Unit = println("eat eat")
    }
    val funnyAnimal: Animal = new AnonymousClasses$$anon$1
   */

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"Hi, my name is $name, how can I help?")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
  }
  jim.sayHi
  println(jim.getClass) // class lectures.part2oop.AnonymousClasses$$anon$2
}
