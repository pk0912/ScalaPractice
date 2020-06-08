package exercises
/*
  1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900 and I map the key to Lowercase.
  2. Overly simplified social network based on maps
     Person = String
     - add a Person to the network
     - remove
     - friend (mutual)
     - unfriend

     - number of friends of a person
     - person with most friends
     - how many people have NO friends
     - if there is a social connection between two people (direct or not)
 */
object TuplesAndMaps extends App {

  val phonebook = Map(("Jim", 555), "JIM" -> 789)
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2)) // Answer for 1: key's value gets overwritten

  // Simplified Social Network

  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] =
    network + (person -> Set())

  def friend(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)
    network + (personA -> (friendsA + personB)) + (personB -> (friendsB + personA))
  }

  def unfriend(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)
    network + (personA -> (friendsA - personB)) + (personB -> (friendsB - personA))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    @scala.annotation.tailrec
    def handler(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAcc
      else handler(friends.tail, unfriend(networkAcc, person, friends.head))
    }
    val unfriended = handler(network(person), network)
    unfriended - person
  }

  val emptyNetwork: Map[String, Set[String]] = Map()
  val network = add(add(emptyNetwork, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  // Jim, Bob, Mary
  val people = add(add(add(emptyNetwork, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")
  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))
  println(nFriends(testNet, "Jack"))
  println(nFriends(testNet, "Mary"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
//    network.filterKeys(k => network(k).isEmpty).size
//    network.filter(_._2.isEmpty).size
//     network.count(pair => pair._2.isEmpty)
    network.count(_._2.isEmpty)
  }

  println(nPeopleWithNoFriends(testNet))
  println(nPeopleWithNoFriends(add(testNet, "Jack")))

  def socialConnection(network: Map[String, Set[String]], personA: String, personB: String): Boolean = {
    @scala.annotation.tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(personB, Set(), network(personA) + personA)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))
}
