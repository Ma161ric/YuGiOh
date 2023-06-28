package de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.yuGiOh.model.fieldComponent.FieldInterface
import de.htwg.se.yuGiOh.util._

trait ActionStrategy {
  val undoManager = new UndoManager[FieldInterface]
  def performAction(field: FieldInterface): FieldInterface
}

object DrawStrategy extends ActionStrategy {
  override def performAction(field: FieldInterface): FieldInterface = {
    println("Karte ziehen...")
    val updatedField = undoManager.doStep(field, DrawCommand(field))
    updatedField
  }
}

object AttStrategy extends ActionStrategy {
  override def performAction(field: FieldInterface): FieldInterface = {
    println("Angreifen...")
    //  to do: implement attack
    field
  }
}

object PlayStrategy extends ActionStrategy {
  override def performAction(field: FieldInterface): FieldInterface = {
    // to do: hier müsste als parameter noch reinkommen die card die gelegt wird
    // Implementiere die Logik für das Legen einer Karte
    println("Karte legen...")
    val updatedField = undoManager.doStep(field, PlayCardCommand(field, 0)) // to do:
    // val updatedField = undoManager.doStep(field, DoCommand(Move("playCard"), field, card))
    updatedField
  }
}

object NextStrategy extends ActionStrategy {
  override def performAction(field: FieldInterface): FieldInterface = {
    println("Zug beenden...")
    field.nextPlayer()
    val newRound = field.getRound + 1
    val updatedField = field.copy(round = newRound)
    updatedField
  }
}
