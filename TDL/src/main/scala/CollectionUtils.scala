object CollectionUtils {
  
  def removeElementByIndex(elements: Seq[String], index: Int): Seq[String] = {
    elements.zipWithIndex
      .filter((_, idx) => idx != index)
      .map((line, _) => line)
  }
}
