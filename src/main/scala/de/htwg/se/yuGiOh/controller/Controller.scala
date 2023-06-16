package de.htwg.se.yuGiOh.controller

import de.htwg.se.yuGiOh.util.Observable
import de.htwg.se.yuGiOh.model._

// Singelton Pattern
object GameController {
  private var instance: GameController = _

  def getInstance(field: Field): GameController = {
    if (instance == null) {
      instance = new GameController(field)
    }
    instance
  }
}

val ERROR: Int = -1
val EXIT: Int = 0
val SUCCESS: Int = 1

class GameController(field: Field) extends Observable {
  override def toString: String = field.toString
  var attStrategy: AttackStrategy = AttackStrategyAttDef
  // hardcoded for now
  var actStrategy: ActionStrategy = DrawStrategy
  val this.field = field

  def setAttackStrategy(strategy: AttackStrategy): Unit = {
    this.attStrategy = strategy
  }

  def setActionStrategy(strategy: ActionStrategy): Unit = {
    this.actStrategy = strategy
  }

  // Karte von Stapel ziehen
  def drawCard(): Boolean = {
    val currentPlayer = field.getCurrentPlayer()
    if (actStrategy == DrawStrategy) {
      actStrategy.performAction(field)
      actStrategy = AttStrategy
      notifyObservers() // Notify the observers about the state change in the field
      return true
    } else {
      println(
        "No action strategy set. Please set an action strategy before drawing a card."
      )
      return false
    }
  }

  def layCard(card: Card): Boolean = {
    if (actStrategy == AttStrategy) {
      actStrategy = NextStrategy

      // field.fightField.addCard(card)
      notifyObservers() // Notify the observers about the state change in the field
      return true
    } else {
      println(
        "No attack strategy set. Please set an attack strategy before attacking."
      )
      return false
    }
  }

  def attack(opponentsCard: Int, playersCard: Int): Boolean = {
    if (
      opponentsCard < 0 || opponentsCard > 4 || playersCard < 0 || playersCard > 4
    ) {
      println("Invalid card index for opponents card.")
      return false
    }
    if (actStrategy == AttStrategy) {
      actStrategy = NextStrategy
      attStrategy = AttackStrategyAttDef
      attStrategy.attack(field, playersCard, opponentsCard)
      notifyObservers() // Notify the observers about the state change in the field
      return true
    } else {
      println(
        "No attack strategy set. Please set an attack strategy before attacking. Maybe skip drawing a card"
      )
      return false
    }
  }

  def nextPlayer(): Boolean = {

    if (actStrategy == NextStrategy) {
      actStrategy.performAction(field)
      notifyObservers() // Notify the observers about the state change in the field
    } else {
      println(
        "No next strategy set. Please set an next strategy before switching to the next player."
      )
      return false
    }
    actStrategy = DrawStrategy
    notifyObservers() // Notify the observers about the state change in the field
    return true
  }

  /*def drawStartingHand(): Unit =
    // ziehe die ersten drei karten vom deck
    val hand = Hand(List.fill(6)(Card.emptyCard))*/

  // def setNamePlayer1(name: String) =
  //   field.playerName(10, name)
  //   notifyObservers

  // def setNamePlayer2(name: String) =
  //   field.playerName(10, name)
  //   notifyObservers

  // def setLpPlayer1(lp: String) =
  //   field.playerLp(10, lp)
  //   notifyObservers

  // def setLpPlayer2(lp: String) =
  //   field.playerLp(10, lp)
  //   notifyObservers

  /*def setHandPlayer(hand: Hand) =
    hand.playerHandRow(10, hand.getSize, hand.getCards)
    notifyObservers
   */
  // def countRound(fightField: FightField, round: Int) =
  //   val newRound = round + 1
  //   fightField.innerRoundBar(10, fightField.getSize, newRound)
  //   notifyObservers

  // def setFightFieldPlayer1(fightField: FightField) =
  //   fightField.playerRow(10, fightField.getSize, fightField.getCards, 40)
  //   notifyObservers

  // def setFightFieldPlayer2(fightField: FightField) =
  //   fightField.playerRow(10, fightField.getSize, fightField.getCards, 40)
  //   notifyObservers
}
