package de.htwg.se.yuGiOh
package model

import util.{Event, Move, Observable, UndoManager}
import controller.DoCommand

trait ActionStrategy {
  val undoManager = new UndoManager[Field]
  def performAction(field: Field): Field
}

object DrawStrategy extends ActionStrategy {
  override def performAction(field: Field): Field = {
    println("Karte ziehen...")
    val updatedField = undoManager.doStep(field, DoCommand(Move("draw"), field)) // to do: für jedes draw, attack, etc ein eigenes command anstatt move übergeben und case ausprobiere
    updatedField
  }
}

// angriff
object AttStrategy extends ActionStrategy {
  override def performAction(field: Field): Field = {
    println("Angreifen...")
    // AttStrategy.attack(field, playersCard, opponentsCard)
    field
  }
}

object PlayStrategy extends ActionStrategy {
  override def performAction(field: Field): Field = {
    //to do: hier müsste als parameter noch reinkommen die card die gelegt wird
    // Implementiere die Logik für das Legen einer Karte
    println("Karte legen...")
    //val updatedField = undoManager.doStep(field, DoCommand(Move("playCard"), field, card))
    //updatedField
    field
  }
}

object NextStrategy extends ActionStrategy {
  override def performAction(field: Field): Field = {
    println("Zug beenden...")
    field.nextPlayer()
    val newRound = field.getRound + 1
    val updatedField = field.copy(round = newRound)
    updatedField
  }
}
