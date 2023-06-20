package de.htwg.se.yuGiOh
package controller

import model._
import util.{Event, Move, Observable, UndoManager}

import scala.collection.mutable.ListBuffer
import scala.util.Random

// Singelton Pattern
object Controller {
  private var instance: Controller = _

  def getInstance(field: Field): Controller = {
    if (instance == null) {
      instance = new Controller(field)
    }
    instance
  }
}

val ERROR: Int = -1
val EXIT: Int = 0
val SUCCESS: Int = 1

//remember: controller changed from case class to class
class Controller(var field: Field) extends Observable {
  override def toString: String = field.toString
  var attStrategy: AttackStrategy = AttackStrategyAttDef
  // hardcoded for now
  var actStrategy: ActionStrategy = DrawStrategy
  //val this.field = field

  private val undoManager = new UndoManager[Field]

  def setAttackStrategy(strategy: AttackStrategy): Unit = {
    this.attStrategy = strategy
  }

  def setActionStrategy(strategy: ActionStrategy): Unit = {
    this.actStrategy = strategy
  }

  def attack(opponentsCard: Int, playersCard: Int): Boolean =
    if (
      opponentsCard < 0 || opponentsCard > 4 || playersCard < 0 || playersCard > 4
    ) {
      println("Invalid card index for opponents card.")
      return false
    }
    actStrategy = AttStrategy
    if (actStrategy == AttStrategy) {
      actStrategy = NextStrategy
      attStrategy = AttackStrategyAttDef
      attStrategy.attack(field, playersCard, opponentsCard)
      notifyObservers(Event.Attack) // Notify the observers about the state change in the field
      true
    } else {
      println(
        "No attack strategy set. Please set an attack strategy before attacking. Maybe skip drawing a card"
      )
      false
    }

  //remember: drawCard doesnt return field anymore
  def drawCard(moveString: String): Boolean =
    actStrategy = DrawStrategy // to do: hardgecodede strategy zuweisung ist ein no no
    if (actStrategy == DrawStrategy) {
      field = actStrategy.performAction(field)
      //actStrategy = AttStrategy
      //val move = Move(moveString) //
      //field = undoManager.doStep(field, DoCommand(move, field)) // to do: für jedes draw, attack, etc ein eigenes command anstatt move übergeben und case ausprobiere
      notifyObservers(Event.Draw) // Notify the observers about the state change in the field
      true
    } else {
      println(
        "No action strategy set. Please set an action strategy before drawing a card."
      )
      false
    }

  def newGame(): Unit =
    field = StartingGame.prepare(field.getPlayer1.name, field.getPlayer2.name)
    notifyObservers(Event.NewGame)

  def playCard(card: Card, moveString: String): Boolean =
    //if (actStrategy == AttStrategy) {
      //actStrategy = NextStrategy
    actStrategy = PlayStrategy
      //val move = Move(moveString) //
      //field = actStrategy.performAction(field)
      field = undoManager.doStep(field, DoCommand(Move("playCard"), field, card)) //
      notifyObservers(Event.PlayCard) // Notify the observers about the state change in the field
      true
    //} else {
      //println(
      //  "No attack strategy set. Please set an attack strategy before attacking."
      //)
      //false
    //}

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

  //to do: change name roundIncrement to smth like nextRound
  def roundIncrement(newRound: Int): Boolean = {
    //to do: auskommentierte strategy hier rein verheiraten
    //if (actStrategy == NextStrategy) {

    actStrategy = NextStrategy

    field = actStrategy.performAction(field)
      //field = field.copy(round = newRound)
      notifyObservers(Event.Next) // Notify the observers about the state change in the field
      true
    //} else {
     // println(
    //   "No next strategy set. Please set an next strategy before switching to the next player."
    //  )
   //   false
  //  }
    //actStrategy = DrawStrategy
    //notifyObservers() // Notify the observers about the state change in the field
    //return true
  }
  
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
  
}
