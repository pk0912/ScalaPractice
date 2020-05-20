package lectures.part2oop
/*
Lecture Pointers
 - Object
         - A dedicated concept in Scala.
         - Scala does not have class level functionality like in Java, so it does not know static.
         - It has something even better called object.
         - So an object can have static like functionality.
         - Object can have value, variables, methods definitions.
         - Object do not receive parameters (i.e. does not have constructor)
         - Object is a singleton instance.
         - Define a class with the same name as the object in the same scope to have instance level functionality.
         - Instantiate an object without using new keyword.
 - Companions
             - Object and class with same name in same scope.
             - Object has class level functionality.
             - Class has instance level functionality.
 - Factory Method
                 - sole purpose to build an instance of the companion class.
                 - apply method can be used for this purpose (widely used)
 - Scala Application
                     - It is only a scala object.
                     - It has a very important method:
                                                      def main(args: Array[String]): Unit
                     - This above signature can be replaced by extending the Scala Object with App class
                     - App class contains this method
                     - If not extending the App class then for running the object, runnable code should be written inside the main method.
 */
object Objects extends App {

  object Person { // type + its only instance; object is of type Person and this is its only instance
    // "static"/"class" - level functionality
    val N_EYES = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person, child: String): Person = new Person(child)
  }

  class Person(val name: String) {
    // instance - level functionality
  }
  // COMPANIONS

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala object = SINGLETON INSTANCE
  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john) // false

  val person1 = Person
  val person2 = Person
  println(person1 == person2) // true

  val bobbie = Person(mary, john, "Tom") // object Person apply method is getting called, which is creating a new Person instance
  println(bobbie.name)
}
