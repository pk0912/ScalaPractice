package lectures.part4pm

object PatternsEverywhere extends App {

  // Big Idea 1 : catches are actually Matches
  try {
    println("some code")
  }
  catch {
    case e: RuntimeException => "runtime"
    case npe: NullPointerException => "npe"
    case _ => "other"
  }

  /*
    try {
      // code
    } catch(e) {
        e match {
          case e: RuntimeException => "runtime"
          case npe: NullPointerException => "npe"
          case _ => "other"
        }
    }
   */

  // Big Idea 2 : Generators are also based on pattern matching
  val list = List(1,2,3,4)
  val evenOne = for {
    x <- list if x % 2 == 0
  } yield 10 * x

  val tuples = List((1,2), (3,4))
  val filterTuples = for {
    (first, second) <- tuples
  } yield first * second
  // case classes, :: operators, ...

  // Big Idea 3 : Multiple value definitions based on pattern matching
  val tuple = (1,2,3)
  val (a, b, c) = tuple
  println(b)
  // ALL THE POWER

  val head :: tail = list
  println(head)
  println(tail)

  // Big Idea 4 : Partial Function based on pattern matching
  val mappedList = list.map {
    case v if v % 2 == 0 => v + " is even "
    case 1 => "the ONE"
    case _ => "something else"
  } // partial function literal

  val mappedList2 = list.map(x => x match {
    case v if v % 2 == 0 => v + " is even "
    case 1 => "the ONE"
    case _ => "something else"
  })
  println(mappedList)
  println(mappedList2)

}
