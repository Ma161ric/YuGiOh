package de.htwg.se.yuGiOh.model

import java.io.{File, FileWriter, PrintWriter}

class FileIOXml {

  override def save(field: Field, playStrategy: PlayStrategy): Unit =
    createDirectory("XML")
    savePlayStrategy(playStrategy) // to do
    saveString(field) // to do

  override def load: Field = {
    //to do
  }

  def saveXML(field: Field): Unit = { // to do: not sure where its supposed to be used yet
    scala.xml.XML.save("field.xml", fieldToXml(field))
  }

  private def createDirectory(path: String): Unit =
    val directory = new File(path)
    if (!directory.exists()) {
      directory.mkdir()
    }

  // to do: überall muss als eingabeparameter das interface rein später
  private def saveString(field: Field): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("field.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(fieldToXml(field))
    val tryPw = Try(pw.write(xml))
    pw.close()
    tryPw.isSuccess
  }

  private def fieldToXml(field: Field) = {

  }
}
