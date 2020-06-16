package exercises

import scala.util.{Random, Try}

object HandlingFailure extends App {

  val hostname = "localhost"
  val port = "8080"
  def renderHTML(page: String): Unit = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection Interrupted")
    }
  }

  object HttpService {

    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }
  }

  val connection = Try(HttpService.getConnection(hostname, port))
  val page = connection.map(x => x.get(hostname + ":" + port + "/index.html"))
  page.foreach(renderHTML)

  // shorthand version
  Try(HttpService.getConnection(hostname, port))
    .map(conn => conn.get(hostname + ":" + port + "/index.html"))
    .foreach(renderHTML)

  // for-comprehension
  for {
    conn <- Try(HttpService.getConnection(hostname, port))
  } renderHTML(conn.get(hostname + ":" + port + "/index.html"))

}
