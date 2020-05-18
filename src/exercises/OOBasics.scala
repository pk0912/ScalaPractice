package exercises

object OOBasics extends App {

  val writer = new Writer("George", "Orwell", 1920)
  println(writer.fullName)

  val novel = new Novel("1984", 1947, writer)
  println(novel.authorAge)
  println(novel.isWrittenBy(writer))

  val impostor = new Writer("George", "Orwell", 1920)
  println(novel.isWrittenBy(impostor)) // writer and impostor have the same values but they are different objects, so output of this will be false

  val counter = new Counter
  counter.print()
  counter.inc.print()
  counter.inc.inc.print()
  counter.inc(3).print()
  counter.dec.print()
  counter.dec.dec.print()
  counter.dec(3).print()
  counter.inc(3).dec(3).print()
}

/*
  1. Novel and a Writer

     Writer: first name, surname, year
           - method
                   - fullname

     Novel: name, year of release, author
          - method
                  - authorAge
                  - isWrittenBy(author)
                  - copy (new year of release) = new instance of Novel
 */

class Writer(firstName: String, surname: String, val birthYear: Int) {

  def fullName: String = firstName + " " + surname
}

class Novel(name: String, releaseYear: Int, author: Writer) {

  def authorAge: Int = releaseYear - author.birthYear

  def isWrittenBy(author: Writer): Boolean = author == this.author

  def copy(newReleaseYear: Int): Novel = new Novel(name, newReleaseYear, author)
}

/*
  2. Counter class
                  - receives an int value
                  - method current count
                  - method to increment/decrement => new Counter
                  - overload inc/dec to receive an amount
 */

class Counter(val count: Int = 0) {

  def inc: Counter = {
    println("Incrementing")
    new Counter(count + 1)  // immutability
  }

  def dec: Counter = {
    println("Decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n <= 0) this
    else inc.inc(n-1)
  }

  def dec(n: Int): Counter = {
    if (n <= 0) this
    else dec.dec(n-1)
  }

  def print(): Unit = println(count)
}

