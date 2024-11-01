import scala.io.StdIn
import scala.util.Try

object IOHelper {

  def readInput(): Try[String] = Try {
    StdIn.readLine()
  }

  def promptUser(): Try[Unit] = Try {
    println("(Commands: a \"task\", d 1, h, q, v)")
    print("yo: ")
  }

  def showHelp(): Try[Unit] = Try {
    val text =
      """ Possible commands:
        |-------------------
        | h - help with commands
        | a <task> - add a to-do item
        | d <task number> - delete a task by its number
        | v - view the to-do list
        | q - quit
        |""".stripMargin.trim
    println(text)
  }
}
