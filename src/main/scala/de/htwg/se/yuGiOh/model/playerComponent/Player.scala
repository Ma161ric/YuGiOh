package de.htwg.se.yuGiOh.model.playerComponent

import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._
import de.htwg.se.yuGiOh.model._

case class Player(name: String, hand: Hand, fightField: FightField, lp: Int = 1000):
  override def toString: String = name
  private var gameState: GameState = new PlayerTurnState()

  def iterateHand(): Unit = {
    val handIterator = hand.iterator
    while (handIterator.hasNext) {
      val card = handIterator.next()
    }
  }

  def startTurn(): Unit = {
    gameState.startTurn()
  }

  def endTurn(): Unit = {
    gameState.endTurn()
  }

  def playCard(card: Card): Unit = {
    gameState.playCard(card)
  }

  def changeState(newState: GameState): Unit = {
    gameState = newState
  }

  // Weitere Methoden und Funktionen der Spielerklasse

  /*   private var state: GameState = TurnState // Initialer Zustand: Zugzustand

  def executeTurn(): Unit = {
    state.executeTurn(this)
  }

  def executeBattle(): Unit = {
    state.executeBattle(this)
  }

  def changeState(newState: GameState): Unit = {
    state = newState
  }
   */

  def getHand: Hand = hand
  def getFightField: FightField = fightField
  def getLp: Int = lp

  // to do: def changeName(playerName: String) = playerName
