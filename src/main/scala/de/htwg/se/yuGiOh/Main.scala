package de.htwg.se.yuGiOh

import de.htwg.se.yuGiOh.controller.Controller

import scala.io.StdIn.readLine
import de.htwg.se.yuGiOh.model.{Card, CardLastName, CardName, Deck, Field, FightField, Hand, Player}
import de.htwg.se.yuGiOh.aview.Tui

import scala.util.Random

@main def run: Unit =

  val rnd = new Random
  var input = ""

  println("Welcome to my game!")

  print("Size of game (min 4): ")
  input = readLine()
  while (input.toInt < 4 || input.toInt > 10) {
    print("Size of game (min 4): ")
    input = readLine()
  }
  val size = input.toInt
  // size muss integer sein, sollte wert 6 haben
  val emptyCard: Card = Card(CardName.emptyName, CardLastName.emptyLastName,0,0,"")
  val deck: Deck = Deck(emptyCard.deck)
  val startingHandPlayer1 = deck.startingHand(size)
  val startingHandPlayer2 = deck.startingHand(size)
  val emptyFightField = FightField(List.fill(size)(Card(CardName.emptyName, CardLastName.emptyLastName,0,0,"")))

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
  val firstPlayer = Player(firstName, startingHandPlayer1, emptyFightField)
  val secondPlayer = Player(secondName, startingHandPlayer2, emptyFightField)

  val field =
    Field(size, 1, 40, firstPlayer, secondPlayer)
  val controller = Controller(field)
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
