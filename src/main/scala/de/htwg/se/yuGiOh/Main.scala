package de.htwg.se.yuGiOh

import scala.io.StdIn.readLine
import scala.collection.mutable.ListBuffer
import scala.util.Random
import com.google.inject.{AbstractModule, Guice, Inject}
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._

import aview.Tui
import aview.gui.Gui

import de.htwg.se.yuGiOh.controller.controllerComponent.ControllerInterface
import de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl._
import de.htwg.se.yuGiOh.model.fieldComponent.FieldInterface
//import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._

@main def run: Unit =

  //val rnd = new Random
  //var input = ""

  println("Welcome to my game!")

  /*print("Size of game (min 4): ")
  input = readLine()
  while (input.toInt < 4 || input.toInt > 10) {
    print("Size of game (min 4): ")
    input = readLine()
  } to do: fürs erste auskommentiert damits nicht zu kompliziert wird

  val size = input.toInt
  val size = 6

  println("Please enter your names: ")
  print("Player 1 Name: ")
  val firstName = readLine()

  print("Player 2 Name: ")
  input = readLine()

  while (firstName == input) {
    println("Player 1 and Player 2 cannot have the same name!")
    print("Player 2 Name: ")
    input = readLine()
  }
  val secondName = input*/
  // to do: playername input should be try option. dont forget this sarah!

  //val field = StartingGame.prepare(firstName, secondName)


  val injector = Guice.createInjector(new Module)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])

  //val controller = Controller(field)
  val gui = Gui(controller)
  val tui = new Tui(controller)

  tui.run()

  // print("Who starts first (1 or 2)? ") // input 1 or 2 or q
  // input = readLine()
  // val goFirst = rnd.nextInt(2) + 1
  // val tuiField: Unit =
  //   tui.processInputLine(
  //     size,
  //     goFirst.toString(),
  //     field,
  //     firstPlayer,
  //     secondPlayer
  //   )

