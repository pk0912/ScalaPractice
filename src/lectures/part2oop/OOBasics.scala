package lectures.part2oop
/*
Lecture Pointers
 - class
        - uses class keyword
        - new keyword for instantiating the class
        - organizes data and behaviour (i.e. code)
        - class parameters are not fields
        - to convert a class parameter to a field is by adding val or var keyword before the parameter
        - val or var definitions inside the class are fields
        - functions defined inside class body are known as methods
 - overloading
              - means defining methods with same name but different signature
              - different signature means different number of parameters or different types of parameters
 - constructor
              - auxiliary constructor can call only primary or secondary constructor
              - because of above limitation, auxiliary constructors are not used much
 */
object OOBasics extends App {

  val person = new Person("John", 26)
  println(person.age)
  println(person.x)

  person.greet("Daniel")
  person.greet()
}

// constructor
class Person(name: String, val age: Int) {
  // body
  val x = 2 // expression

  println(1 + 3) // side-effect

  //method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // method-overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructors
  def this(name: String) = this(name, 0) // This can be replaced by just adding a default value for the parameter age in the primary constructor
}
