package de.htwg.se.yuGiOh.model.fieldComponent

import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._
import de.htwg.se.yuGiOh.model.playerComponent.Player

trait FieldInterface {
  def copy(size: Int = this.getSize, round: Int = this.getRound, deck: Deck = this.getDeck, player1: Player = this.getPlayer1, player2: Player = this.getPlayer2): FieldInterface
  def getCurrentPlayer(): Player
  def nextPlayer(): Unit
  def getSize: Int
  def getPlayer1: Player
  def getPlayer2: Player
  def getRound: Int
  def getDeck: Deck
  def outerBar(cellWidth: Int, cellNum: Int): String
  def outerOuterBar(cellWidth: Int, cellNum: Int): String
  def playerHandRow(cellWidth: Int, cellNum: Int, hand: Hand): String
  //to do: check if outerbar etc could be made private
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

