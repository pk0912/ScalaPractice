package lectures.part3fp

import javax.xml.bind.util.ValidationEventCollector

import scala.util.Random

/*
Lecture Pointers
- Collections
             - Scala offers both mutable and immutable collections
             - Immutable Collections - found in scala.collection.immutable package
             - Mutable Collections - found in scala.collection.mutable package
 - Traversable
              - Base Trait for all collections.
              - Offers a great variety of methods.
                - maps: map, flatMap, collect
                - conversions: toArray, toList, toSeq
                - size info: isEmpty, size, nonEmpty
                - tests: exists, forall
                - folds: foldLeft, foldRight, reduceLeft, reduceRight
                - retrieval: head, find, tail
                - string ops: mkString
 - Sequences
            - denoted by 'seq'
            - have a well defined order
            - can be indexed
            - supports various operations:
                                         - apply, iterator, length, reverse for indexing and iterating
                                         - concatenation, appending, prepending
                                         - a lot of others: grouping, sorting, zipping, searching, slicing
 - Range
        - uses 'to'/'until' keyword
 - List
       - head, tail, isEmpty methods are fast : O(1)
       - most operations are O(n): length, reverse
       - is sealed
       - has two subtypes
                         - object Nil (Empty)
                         - class ::
       - for prepending
                       - 42 :: aList
                       - 42 +: aList
       - for appending - aList :+ 24
       - fill method takes a count and value and creates a list with the count of that value
       - Advantage: List keeps reference to tail
       - Disadvantage: deleting an element in the middle takes time
 - Array
        - equivalent of simple java arrays
        - can be mutated
        - can be manually constructed with predefined lengths
        - are interoperable with Java's native arrays
        - indexing is fast
        - conversion of seq and array can be done using implicit conversion
 - Vector
         - Default implementation for immutable sequences
         - effectively constant indexed read and write: O(log32(n)) (32 is base)
         - fast element addition: append/ prepend
         - implemented as a fixed branched trie (branch factor 32)
         - good performance for large sizes
         - Advantage: Depth of tree is small
         - Disadvantage: needs to replace an entire 32-element chunk
 */
object Sequences extends App {

  // Seq
  val aSequence = Seq(1,3,2,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(7,5,6))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  (1 to 10).foreach(x => println("Hello"))

  // Lists
  val aList = List(1,2,3)
  val prepended = 42 +: aList :+ 24
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5)

  println(aList.mkString("-|-"))

  // arrays
  val numbers = Array(1,2,3,4)
  println(numbers)
  val threeElements = Array.ofDim[String](3)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq)

  // vectors
  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs list

  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      _ <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numbersList))
  println(getWriteTime(numbersVector))
}
