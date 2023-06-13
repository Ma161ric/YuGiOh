package de.htwg.se.yuGiOh

import scala.util.Random
import scala.io.StdIn.readLine

import aview.Tui
import aview.gui.Gui
import controller.Controller
import model.{Card, Field, FightField, Hand, Player}

@main def run: Unit =

  val rnd = new Random
  var input = ""

  println("Welcome to my game!")

  /*print("Size of game (min 4): ")
  input = readLine()
  while (input.toInt < 4 || input.toInt > 10) {
    print("Size of game (min 4): ")
    input = readLine()
  }
  val size = input.toInt*/
  val size = 6
  val emptyHand = Hand(List.fill(size)(Card.emptyCard))
  val emptyFightField = FightField(List.fill(size)(Card.emptyCard))

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
  val secondName = input
  val firstPlayer = Player(firstName, emptyHand, emptyFightField)
  val secondPlayer = Player(secondName, emptyHand, emptyFightField)


  //val firstPlayer = Player("1", emptyHand, emptyFightField)
  //val secondPlayer = Player("2", emptyHand, emptyFightField)

  val field =
    Field(size, 1, 40, firstPlayer, secondPlayer)
  val controller = Controller(field)


  val gui = Gui(controller)
  //gui.open()
  val tui = new Tui(controller)

  tui.run()

  // tui.run(size, firstPlayer, secondPlayer)

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

  // println(field.toString)
  // println(field.mesh(size,size, input, Card.roterDrache))
