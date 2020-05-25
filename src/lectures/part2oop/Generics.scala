package lectures.part2oop

import sun.reflect.generics.reflectiveObjects.NotImplementedException

/*
Lecture Pointers
 - Generics
           - Type parameterized
                               - class, trait, method can be type parameterized
                               - object can't be type parameterized
           - Variance Problem 1 : If a class B extends a class A, then can a generic class of type A can have an instance of a generic class of type B.
                               - COVARIANCE
                                           - uses [+A]
                                           - yes for the above problem (displayed in example)
                                           - val someVal: Generic[SuperClass] = new Generic[SubClass]
                                           - but this raises another problem
                                           - Problem 2 : If a class C extends class A, then can the above COVARIANT generic class will be able to add instance of generic class of type C.
                               - INVARIANCE
                                           - uses [A]
                                           - no for the Problem 1 (displayed in example)
                                           - Generic class of type A will only have an instance of a generic class of type A.
                                           - val someVal: Generic[SuperClass] = new Generic[SuperClass]
                               - CONTRAVARIANCE
                                               - uses [-A]
                                               - no for the Problem 1
                                               - Generic class of type B can have an instance of a generic class of type A.
                                               - val someVal: Generic[SubClass] = new Generic[SuperClass]
           - Bounded Types
                          - Upper Bounded Type
                                              - uses ClassName/methodName[A <: B]
                                              - Above notation means either class or the method accepts only a typed parameter of class A which is a subclass of B.
                          - Lower Bounded Type
                                              - uses ClassName/methodName[A >: B]
                                              - Above notation means either class or the method accepts only a typed parameter of class A which is a superclass of B.
           * Problem 2 (described in COVARIANCE section) can be solved by using Lower Bounded Type (i.e by returning an instance of superclass) in the method definition (explained in example)
 */
object Generics extends App {

  class MyList[+A] {
    // use the type A
//    def add(element: A): MyList[A] = ??? // throws error
    def add[B >: A](element: B): MyList[B] = ???
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Problem 1: Can List[Cat] also extends List[Animal]
  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION (Problem 2)

  // 2. No = INVARIANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell, no! CONTRAVARIANCE
  class Trainer[-A]
  val trainer: Trainer[Cat] = new Trainer[Animal]

  // bounded types
  class Cage[A <: Animal](animal: A)
  val cage = new Cage(new Dog)

  class Car
  // generic type needs proper bounded type
//  val newCage = new Cage(new Car)
}
