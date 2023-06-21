package de.htwg.se.yuGiOh.controller.controllerComponent

import de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl._
import de.htwg.se.yuGiOh.model.fieldComponent.FieldInterface
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.*
import de.htwg.se.yuGiOh.util.Observable

trait ControllerInterface extends Observable{
  def getField: FieldInterface
  def setAttackStrategy(strategy: AttackStrategy): Unit
  def setActionStrategy(strategy: ActionStrategy): Unit
  def attack(opponentsCard: Int, playersCard: Int): Boolean
  def drawCard(): Boolean
  def newGame(): Unit
  def playCard(): Boolean
  def printHelp(): Unit
  def quit(): Unit
  def redo: Unit
  def undo: Unit
  def roundIncrement(newRound: Int): Boolean
}
