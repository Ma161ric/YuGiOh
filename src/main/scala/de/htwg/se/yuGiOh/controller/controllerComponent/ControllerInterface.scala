package main.scala.de.htwg.se.yuGiOh.controller.controllerComponent

import main.scala.de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl._
import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.FieldInterface
import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.*
import main.scala.de.htwg.se.yuGiOh.util.Observable

trait ControllerInterface extends Observable {
  def getField: FieldInterface
  def setAttackStrategy(strategy: AttackStrategy): Unit
  def setActionStrategy(strategy: ActionStrategy): Unit
  def attack(opponentsCard: Int, playersCard: Int): Boolean
  def drawCard(): Boolean
  def newGame(): Unit
  def newStartingGame(
      optionStringPlayer1: Option[String],
      optionStringPlayer2: Option[String]
  ): Unit
  def playCard(): Boolean
  def printHelp(): Unit
  def quit(): Unit
  def redo: Unit
  def undo: Unit
  def restart(): Unit
  def newRound(newRound: Int): Boolean
  def save(): Boolean
  def load(): Boolean
}
