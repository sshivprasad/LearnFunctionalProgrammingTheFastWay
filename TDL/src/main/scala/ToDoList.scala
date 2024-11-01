import scala.util.Try

@main
def ToDoList(args: String*): Unit = {
    val database = Database("src/main/scala/db.txt")
    val inputProcessor = InputProcessor(database)

    def mainLoop(): Try[Unit] = {
      for {
        _ <- IOHelper.promptUser()
        input <- IOHelper.readInput()
        _ <- inputProcessor.handleInput(input)
        _ <- mainLoop()
      } yield ()
    }
    mainLoop()
}
