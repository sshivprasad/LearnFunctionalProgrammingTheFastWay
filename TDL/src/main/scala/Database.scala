import scala.util.{Failure, Success, Try, Using}
import java.io.{BufferedReader, BufferedWriter, File, FileReader, FileWriter}
import scala.io.Source
import FileUtils.{writeToFile, readFromFile}

class Database(dbfilename: String) {
  def insert(record: String): Try[Unit] = {
    writeToFile(dbfilename, List(record), true)
  }

  def selectAll(): Try[Seq[String]] = Using(Source.fromFile(dbfilename)) { source =>
    source.getLines().toSeq
  }

  def delete(index: Int): Try[Int] = {
    for {
      rows <- selectAll()
      newRows = CollectionUtils.removeElementByIndex(rows, index)
      deletedCount = rows.size - newRows.size
      _ = writeToFile(dbfilename, newRows.toList, false)
    } yield
        deletedCount
  }
}
