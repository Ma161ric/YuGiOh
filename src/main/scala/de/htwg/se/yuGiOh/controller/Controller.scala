package de.htwg.se.yuGiOh
package controller

import model.{Card, CardLastName, CardName, Deck, Field, FightField, Hand, Player, StartingGame}
import util.{Event, Move, Observable, UndoManager}

import scala.collection.mutable.ListBuffer
import scala.util.Random



case class Controller(var field: Field) extends Observable {

  override def toString: String = field.toString

  private val undoManager = new UndoManager[Field]

  def attack(/*opponentsCard: String, playersCard: String*/): Unit =
    //actual attack mechanic
    notifyObservers(Event.Attack)

  def drawCard(moveString: String): Field =
    val move = Move(moveString)
    field = undoManager.doStep(field, DoCommand(move, field)) //für jedes draw, attack, etc ein eigenes command anstatt move übergeben und case ausprobiere

    notifyObservers(Event.Draw)

    field

  def roundIncrement(newRound: Int): Unit = {
    field = field.copy(round = newRound)
    notifyObservers(Event.Next)
  }

  def newGame(): Unit =
    field = StartingGame.prepare(field.getPlayer1.name, field.getPlayer2.name)
    notifyObservers(Event.NewGame)


  def playCard(card: Card, moveString: String): Field =
    val move = Move(moveString)
    field = undoManager.doStep(field, DoCommand(move, field, card))
    notifyObservers(Event.PlayCard)

    field

  def printHelp(): Unit =
    print(
      """
      Befehlsuebersicht:
      - help | h                  : this help comment
      - exit | q                  : leaves the game
      - new  | n                  : creates new game
      - attack | a                : attack with card from player
      - draw | d                  : draw one card from deck to hand
      - play | p                  : places card from player hand to fight field
      """ + "\n")

  def quit(): Unit =
    System.exit(0)

  def redo: Field =
    field = undoManager.redoStep(field)
    field

  def undo: Field =
    field = undoManager.undoStep(field)
    //print("field after undoManager" + field)
    notifyObservers(Event.Move)

    field


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

  // def countRound(fightField: FightField, round: Int) =
  //   val newRound = round + 1
  //   fightField.innerRoundBar(10, fightField.getSize, newRound)
  //   notifyObservers

  // def setFightFieldPlayer1(fightField: FightField) =
  //   fightField.playerRow(10, fightField.getSize, fightField.getCards, 40)
  //   notifyObservers

  // def setFightFieldPlayer2(fightField: FightField) =
  //   fightField.playerRow(10, fightField.getSize, fightField.getCards, 40)
  //   notifyObservers*/
}

