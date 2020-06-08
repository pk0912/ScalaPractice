package lectures.part3fp
/*
Lecture Pointers
 - Tuples
         - finite, ordered, kind of like lists
         - called using Tuple keyword or by just writing within parenthesis
         - can have 22 elements of different types at max.
         - Why 22 ? Because they are used in conjunction with function types.
 - Maps
       - associate keys to values
       - populating a map can be done using tuples.
       - .withDefaultValues - gives a default value for non-existing keys (if not used NoSuchElementException will be raised while accessing non-existent key)
       - use '+' to add new pair in an existing map
       - map, filter, flatMap takes a pairing
       - filterKeys, mapValues methods are also available by default
       - groupBy method to convert a list to a map grouped by some properties.
 */
object TuplesAndMaps extends App {

  // Tuples
  val aTuple = (2, "hello, Scala") // Tuple2[Int, String] = (Int, String)
  val anotherTuple = 2 -> "hello, Scala"

  println(aTuple)
  println(anotherTuple)
  println(aTuple == anotherTuple)
  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "hey Java"))
  println(aTuple.swap) // ("hello, Scala", 2)

  println(2 -> 3 -> 4)
  println(Tuple3(1,2,3)) // swap only for Tuple2

  // Maps - keys -> Values
  val aMap: Map[String, Int] = Map()
  println(aMap)

  val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)
  // a -> b is syntactic sugar for (a, b)
  println(phonebook)

  // map ops
  println(phonebook.contains("Jim"))
  println(phonebook.contains("Jym"))
  println(phonebook("Mary"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2 * 10))

  // filterKeys
  println(phonebook.filterKeys(x => x.startsWith("J")))
  // mapValues
  println(phonebook.mapValues(number => "0245-" + number))

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
}
