package de.htwg.se.yuGiOh.model.playerComponent

import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._

trait PlayerInterfaceOld {
  def copy(
      name: String = this.getName,
      hand: Hand = this.getHand,
      fightField: FightField = this.getFightField,
      lp: Int = this.getLp
  ): PlayerInterfaceOld
  def iterateHand(): Unit
  def startTurn(): Unit
  def endTurn(): Unit
  def playCard(): Unit
  def changeState(newState: GameState): Unit
  def getName: String
  def getHand: Hand
  def getFightField: FightField
  def getLp: Int
}
