package main.scala.de.htwg.se.yuGiOh.model.fileIOComponent

import main.scala.de.htwg.se.yuGiOh.model.fieldComponent
import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.{
  FieldInterface,
  PlayerInterface
}

trait FileIOInterface:
  def load: (FieldInterface)
  def save(g: FieldInterface): Boolean
