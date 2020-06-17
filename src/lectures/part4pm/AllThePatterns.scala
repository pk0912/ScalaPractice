package lectures.part4pm

import exercises.{ExtendedGenericConsFP, GenericEmptyUsingFP, GenericListUsingFP}

/*
Lecture Pointers - Concepts explained along with examples below
 */
object AllThePatterns extends App {

  // 1. Constants
  val x: Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "The Scala"
    case true => "The Truth"
    case AllThePatterns => "A Singleton Object"
  }

  // 2. Match anything
  // 2.1 - Wildcard
  val matchAnything = x match {
    case _ => "match anything"
  }

  // 2.2 variable
  val matchAVariable = x match {
    case something => s"I've found $something"
  }

  // 3. Tuples
  val aTuple = (1, 2)
  val matchATuple = aTuple match {
    case (1, 1) => (1, 1)
    case (something, 2) => s"I've found $something"
  }

  val nestedTuple = (1, (2, 3))
  val matchANestedTuple = nestedTuple match {
    case (_, (2, v)) => v
  }
  // PMs can be nested!

  // 4. case classes - constructor pattern
  // PMs can be nested with CCs as well
  val aList: GenericListUsingFP[Int] = ExtendedGenericConsFP(1, ExtendedGenericConsFP(2, GenericEmptyUsingFP))
  val matchAList = aList match {
    case GenericEmptyUsingFP => "Empty"
    case ExtendedGenericConsFP(head, ExtendedGenericConsFP(subhead, subtail)) => "Some values"
  }

  // 5. List Patterns
  val aStdList = List(1,2,3,42)
  val stdListMatching = aStdList match {
    case List(1, _, _, _) => "extractor - advanced"
    case List(1, _*) => "list of arbitrary length (varargs) - advanced"
    case 1 :: List(_) => "Infix Pattern"
    case List(1,2,3) :+ 42 => "Another infix pattern"
  }

  // 6. Type specifiers
  val unknown: Any = 2
  val unknownMatch = unknown match {
    case list: List[Int] => "matched a List type"
    case _ => "other types"
  }

  // 7. Name binding
  val nameBindingMatch = aList match {
    case nonEmptyList @ ExtendedGenericConsFP(h, t) => nonEmptyList
    case ExtendedGenericConsFP(1, rest @ ExtendedGenericConsFP(h, t)) => rest // name binding inside nested patterns
  }

  // 8. multi-pattern
  val multiPattern = aList match {
    case GenericEmptyUsingFP | ExtendedGenericConsFP(0, _) => "compound pattern (multi-pattern)"
    case _ => "something else"
  }

  // 9. if guards
  val secondElementSpecial = aList match {
    case ExtendedGenericConsFP(_, ExtendedGenericConsFP(spclElement, _)) if spclElement % 2 == 0 => "even tail"
  }

  /*
    Question - JVM Trick Question
   */
  val nums = List(1,2,3)
  val numsMatch = nums match {
    case listOfStrings: List[String] => "a list of string"
    case listOfIntegers: List[Int] => "a list of Int"
    case _ => ""
  }
  println(numsMatch) // a list of string
  /*
   Since JVM is compatible with all the previous versions, and Generics were not up till Java 5 so the compiler erases all generic types after type checking.
   Because of JVM, Scala also suffers from this.
   */

}
