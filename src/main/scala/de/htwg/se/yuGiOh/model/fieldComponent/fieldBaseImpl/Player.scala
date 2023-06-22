package de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

import com.google.inject.Inject

//import de.htwg.se.yuGiOh.model.*
import de.htwg.se.yuGiOh.model.fieldComponent.PlayerInterfaceOld

case class Player @Inject()(name: String, hand: Hand, fightField: FightField, lp: Int = 1000) extends PlayerInterfaceOld:
  override def toString: String = name
  private var gameState: GameState = new PlayerTurnState()

  def copy(name: String = this.name, hand: Hand = this.hand, fightField: FightField = this.fightField, lp: Int = this.lp): PlayerInterfaceOld =
    Player(name, hand, fightField, lp)

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

  def playCard(): Unit = { //to do: bekommt noch eine karte übergeben
    gameState.playCard()
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

  def getName: String = name
  def getHand: Hand = hand
  def getFightField: FightField = fightField
  def getLp: Int = lp

  // to do: def changeName(playerName: String) = playerName
