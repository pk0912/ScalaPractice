package exercises

import scala.util.Random

object Options extends App {

  val config: Map[String, String] = Map(
    // fetched from elsewhere
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected" // connect to some server
  }
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] = {
      if (random.nextBoolean()) Some(new Connection)
        else None
    }
  }

  // try to establish a connection, if so - print the connect method
//  val newConnection = Connection(Option(config).map(x => x("host")).toString, Option(config).map(x => x("port")).toString)
//  val status = newConnection.map(_.connect)
//  println(status)
//  status.foreach(println)

  // other solutions
  val host: Option[String] = config.get("host")
  val port = config.get("port")
  /*
    if (h != null)
      if (p != null)
        return Connection.apply(h, p)
    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  /*
    if (c != null)
      return c.connect
     return null
   */
  val connectionStatus = connection.map(c => c.connect)
  // if (connectionStatus == null) println(None) else print(Some(connectionStatus.get))
  println(connectionStatus)
  /*
    if (connectionStatus != null)
      println(connectionStatus)
   */
  connectionStatus.foreach(println)

  // another way
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
      .map(connection => connection.connect))
    .foreach(println)

  // for-comprehensions
//  println(config.get("port")) // Some(null) if port is null
  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)
}
