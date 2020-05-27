package lectures.part2oop
/*
Lecture Pointers
 - Case Class
             - Class parameters are fields
             - sensible toString method (prints class name with parameters)
             - equals and hashCode implemented out of the box; makes useful for getting used in collections
             - have handy copy method; also receives named parameters
             - have companion object that has handy factory method (apply)
             - are serializable; useful in distributed systems
             - have extractor patterns; can be used in pattern matching
             - case object is also possible
 */
object CaseClasses extends App {

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  // println(instance) = println(instance.toString) // syntactic sugar
  println(jim.toString)
  println(jim)

  // 3. equals and hashCode implemented out of the box
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)

  // 4. have handy copy method
  val jim3 = jim.copy(age = 45)
  println(jim3)

  // 5. have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23) // factory method (apply) is getting called

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
}
