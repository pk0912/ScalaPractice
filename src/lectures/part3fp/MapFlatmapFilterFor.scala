package lectures.part3fp
/*
Lecture Pointers
- map, flatMap, filter, for comprehensions
                                          - iterations are done using flatMap, map
                                          - for comprehensions are used for iterations for better readability
 */
object MapFlatmapFilterFor extends App {

  val list = List(1, 2, 3)
  println(list)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  // print all combinations between two lists
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val strings = List("black", "white")
  // List("a1", "a2", ...., "d4")

  // "iterating"
  val combinations = chars.flatMap(c => numbers.flatMap(n => strings.map(s => "" + c + n + "-" + s)))
  println(combinations)
  println(combinations.length)

  // foreach
  list.foreach(println)

  // for comprehensions
  val forCombinations = for {
    c <- chars
    n <- numbers if n % 2 == 0
    s <- strings
  } yield "" + c + n + "-" + s
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  println(list.map {
    _ * 2
  })
}
