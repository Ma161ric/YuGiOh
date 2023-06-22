package de.htwg.se.yuGiOh.model.fieldComponent

import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._
//to do: check if it would be better to player instead of playerinterface
trait FieldInterface {
  def copy(size: Int = this.getSize, round: Int = this.getRound, deck: Deck = this.getDeck, player1: PlayerInterface = this.getPlayer1, player2: PlayerInterface = this.getPlayer2): FieldInterface
  def getCurrentPlayer(): PlayerInterface
  def nextPlayer(): Unit
  def getSize: Int
  def getPlayer1: PlayerInterface
  def getPlayer2: PlayerInterface
  def getRound: Int
  def getDeck: Deck
  def outerBar(cellWidth: Int, cellNum: Int): String
  def outerOuterBar(cellWidth: Int, cellNum: Int): String
  def playerHandRow(cellWidth: Int, cellNum: Int, hand: Hand): String
  //to do: check if outerbar etc could be made private
  def toString: String
}

trait CardInterface {
  def atkToString: String
  def defeToString: String
  def getFirstName: String
  def getLastName: String
  def getAtk: Int
  def getDefe: Int
  def getPosition: String
  def isEmpty(card: Card): Boolean
}

trait StartingGameInterface {
  def prepare(player1Name: String, player2Name: String): Field
}

trait DeckInterface {
  def getDeck: List[Card]
  def getDeckCount: Int
  def createIterator: DeckIterator
  def startingHand(size: Int): (Hand, Deck)
}

trait PlayerInterface {
  def copy(name: String = this.getName, hand: Hand = this.getHand, fightField: FightField = this.getFightField, lp: Int = this.getLp): PlayerInterface
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
