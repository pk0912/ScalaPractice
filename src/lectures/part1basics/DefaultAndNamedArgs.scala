package lectures.part1basics

object DefaultAndNamedArgs extends App {

  def trFact(n: Int, acc: Int = 1): Int = {
    if (n <= 1) acc
    else trFact(n-1, n*acc)
  }

  println(trFact(10)) // use of default argument
  println(trFact(10, 2))

  def savePicture(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("saving picture")

  // using named args
  savePicture(width = 800)
  savePicture(height = 600, width = 800, format = "bmp")
}
