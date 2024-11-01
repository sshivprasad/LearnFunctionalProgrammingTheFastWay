import java.io.{BufferedWriter, FileWriter, File}
import scala.util.Try
import scala.io.Source
import scala.util.Using


object FileUtils {
  def readFromFile(filename: String): Try[Seq[String]] = {
    Using(Source.fromFile(filename)) { source =>
      source.getLines().toSeq
    }
  }

  def writeToFile(filename: String, records: Seq[String], append: Boolean): Try[Unit] = {
    Using(new BufferedWriter(
      new FileWriter(
        new File(filename), append
      )
    )) { bw =>
      for (record <- records) {
        bw.write(s"${record}\n")
      }
    }
  }
}
