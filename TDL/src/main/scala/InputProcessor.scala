import scala.util.{Failure, Success, Try}

class InputProcessor(db: Database) {
  def handleInput(input: String): Try[Unit] = {
    Try {
      input match {
        case add if add.startsWith("a ") =>
          handleAdd(add.drop(2))
          handleView()
        case del if del.startsWith("d ") =>
          handleDelete(del.drop(2).toInt)
          handleView()
        case "v" => handleView()
        case "h" => IOHelper.showHelp()
        case "q" => Try {System.exit(0)}
        case _ =>
          handleUnknown()
          IOHelper.showHelp()
      }
    }
  }

  def handleView(): Try[Unit] = Try {
    db.selectAll() match {
      case Success(tasks) => {
        for ((task, count) <- tasks.zip(LazyList from 1)) {
          println(s"${count} : ${task}")
        }
      }
      case Failure(ex) =>
        println(s"Error when reading the to-do list : ${ex}")
    }
  }

  def handleAdd(task: String): Try[Unit] = Try {
    db.insert(task)
  }

  def handleDelete(index: Int): Try[Int] = Try {
   val delCount = db.delete(index - 1)
    delCount.get
  }

  def handleUnknown(): Try[Unit] = Try {
    println("This isn't a valid command")
  }
}
