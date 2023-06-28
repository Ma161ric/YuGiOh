package de.htwg.se.yuGiOh.model.fileIOComponent

import de.htwg.se.yuGiOh.model.fieldComponent
import de.htwg.se.yuGiOh.model.fieldComponent.{
  FieldInterface,
  PlayerInterface
}

trait FileIOInterface:
  def load: (FieldInterface)
  def save(g: FieldInterface): Boolean
