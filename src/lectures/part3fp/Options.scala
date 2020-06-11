package lectures.part3fp
/*
Lecture Pointers
 - Special type of collection
 - An option is a wrapper for a value that might be present or not.
 - Option's apply method takes care for returning Some or None.
 - orElse can be used to call a substitute method if the preferred method returns None.
 - functions on Options
                       - isEmpty
                       - get -> unsafe method; Don't use this.
                       - map, flatMap, filter
                       - for comprehensions
 * get() method of a map always returns an Option wrapper over the value
 */
object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // unsafe APIs
  def unsafeMethod(): String = null
//   val result = Some(null) // wrong; This will return Some(null)
  val result = Option(unsafeMethod()) // Some or None
  println(result)
  println(result.map(_.length))

  // chained methods
  def backupMethod(): String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))
  println(chainedResult)

  // Design unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterBackupMethod(): Option[String] = Some("A valid result")
  val betterChainedResult = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterChainedResult)

  // functions on Options
  println(myFirstOption.isEmpty)
  println(noOption.isEmpty)

  // map, flatMap, filter
  println(myFirstOption.map(_ * 2))
  println(noOption.map(_ * 2)) //  None
  println(myFirstOption.filter(_ > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

}
