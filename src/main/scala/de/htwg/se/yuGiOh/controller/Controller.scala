package de.htwg.se.yuGiOh
package controller

import model.{Card, Deck, Field, FightField, Hand, Player}
import util.{Event, Move, Observable, UndoManager}



case class Controller(var field: Field) extends Observable {


  override def toString: String = field.toString

  private val undoManager = new UndoManager[Field]

  def undo: Field =
    field = undoManager.undoStep(field)
    //print("field after undoManager" + field)
    notifyObservers(Event.Move)

    field

  def redo: Field =
    field = undoManager.redoStep(field)
    field

  /*def attack(opponentsCard: String, playersCard: String): Unit =
    1*/

  def drawCard(moveString: String): Field =
    val move = Move(moveString)
    field = undoManager.doStep(field, DoCommand(move, field) )
    notifyObservers(Event.Draw)

    field

  def roundIncrement(currentRound: Int): Unit = {
    field = field.copy(round = currentRound + 1)
    notifyObservers(Event.Next)
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
  def printhelp(): Unit =
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
}

