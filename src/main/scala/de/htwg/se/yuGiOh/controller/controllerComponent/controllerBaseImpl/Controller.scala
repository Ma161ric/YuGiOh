package de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl

import scala.collection.mutable.ListBuffer
import scala.util.Random
import net.codingwell.scalaguice.InjectorExtensions.*
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector
import com.google.inject.{AbstractModule, Guice, Inject}
import de.htwg.se.yuGiOh.util.*
import de.htwg.se.yuGiOh.controller.controllerComponent.ControllerInterface
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.StartingGame
import de.htwg.se.yuGiOh.model.fieldComponent.{CardInterface, FieldInterface, StartingGameInterface}
import de.htwg.se.yuGiOh.model.fileIOComponent.*
import de.htwg.se.yuGiOh.Module

object Controller {
  private var instance: Controller = _

  def getInstance(field: FieldInterface): Controller = {
    if (instance == null) {
      instance = new Controller(field)
    }
    instance
  }
}

val ERROR: Int = -1
val EXIT: Int = 0
val SUCCESS: Int = 1

class Controller @Inject() (var field: FieldInterface)
    extends ControllerInterface
    with Observable {

  field = StartingGame.prepare(
    field.getPlayer2.getName,
    field.getPlayer2.getName
  )

  override def toString: String = field.toString
  var attStrategy: AttackStrategy = AttackStrategyAttDef
  var actStrategy: ActionStrategy = DrawStrategy
  // to do: hardcoded for now

  private val undoManager = new UndoManager[FieldInterface]
  private val startingGameInterface: StartingGameInterface = StartingGame

  def getField: FieldInterface = field

  def save(): Boolean = {
    val injector = Guice.createInjector(new Module)
    val fileIo: FileIOInterface = injector.getInstance(classOf[FileIOInterface])
    val res = fileIo.save(field)
    res
  }

  def load(): Boolean = {
    val injector = Guice.createInjector(new Module)
    val fileIo: FileIOInterface = injector.getInstance(classOf[FileIOInterface])
    val newField = fileIo.load

    if (newField == null)
      print(newField)
      print("new field is null")
      return false
    field = newField
    notifyObservers(Event.Restart)
    true
  }

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
      notifyObservers(
        Event.Attack
      ) // to do: also notify the observers about the state change in the field
      true
    } else {
      println(
        "No attack strategy set. Please set an attack strategy before attacking. Maybe skip drawing a card"
      )
      false
    }

  def drawCard(): Boolean =
    actStrategy = DrawStrategy // to do: hardgecodede strategy
    if (actStrategy == DrawStrategy) {
      field = undoManager.doStep(field, DrawCommand(field))
      //field = actStrategy.performAction(field)
      notifyObservers(
        Event.Draw
      ) // to do: also notify the observers about the state change in the field
      true
    } else {
      println(
        "No action strategy set. Please set an action strategy before drawing a card."
      )
      false
    }

  def restart(): Unit =
    field =
      StartingGame.prepare(field.getPlayer1.getName, field.getPlayer2.getName)
    notifyObservers(Event.Restart)

  def newGame(): Unit =
    field =
      StartingGame.prepare(field.getPlayer1.getName, field.getPlayer2.getName)
    notifyObservers(Event.StartingGame)

  def newStartingGame(
      optionStringPlayer1: Option[String],
      optionStringPlayer2: Option[String]
  ): Unit =
    val stringPlayer1: String = optionStringPlayer1.getOrElse("Default")
    val stringPlayer2: String = optionStringPlayer2.getOrElse("Default")

    field = StartingGame.prepare(stringPlayer1, stringPlayer2)
    notifyObservers(Event.NewGame)

  def playCard(): Boolean =
    // to do: hier nachher karte index übergeben
    actStrategy = PlayStrategy // to do: hardcoded strategy
    if (actStrategy == PlayStrategy) {
      field =
        actStrategy.performAction(field) // to do: chosen card index übergeben
      notifyObservers(
        Event.PlayCard
      ) // Notify the observers about the state change in the field
    } else {
      println(
        "No attack strategy set. Please set an attack strategy before attacking."
      )
      false
    }
    true

  def printHelp(): Unit =
    print("""
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

  def redo: Unit =
    field = undoManager.redoStep(field)
    notifyObservers(Event.Move)

  def undo: Unit =
    field = undoManager.undoStep(field)
    notifyObservers(Event.Move)

  def newRound(newRound: Int): Boolean = {
    actStrategy = NextStrategy // to do: hardgecodete strategy

    if (actStrategy == NextStrategy) {
      field = actStrategy.performAction(field)
    } else {
      println(
        "No next strategy set. Please set an next strategy before switching to the next player."
      )
      false
    }
    actStrategy = DrawStrategy
    notifyObservers(
      Event.Next
    ) //  to do: also notify the observers about the state change in the field
    true
  }
}
