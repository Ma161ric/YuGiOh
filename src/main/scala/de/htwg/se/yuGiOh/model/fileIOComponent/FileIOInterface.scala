package de.htwg.se.yuGiOh.model.fileIOComponent

import de.htwg.se.yuGiOh.model.playerComponent.PlayerInterfaceOld
import de.htwg.se.yuGiOh.model.fieldComponent.FieldInterface

trait FileIOInterface:
  def load: (FieldInterface, PlayerInterfaceOld)
  def save(g: FieldInterface, p: PlayerInterfaceOld): Unit
