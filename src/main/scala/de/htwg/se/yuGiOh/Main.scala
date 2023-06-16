package de.htwg.se.yuGiOh

import scala.collection.mutable.ListBuffer
import scala.io.StdIn.readLine
import scala.util.Random

import aview.Tui
import aview.gui.Gui
import controller.Controller
import model.{Card, CardLastName, CardName, Field, FightField, Hand, Player, Deck}

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
  val emptyCard: Card = Card(CardName.emptyName, CardLastName.emptyLastName,0,0,"")
  //val emptyHand = Hand(List.fill(size)(Card.emptyCard))
  val emptyFightField = FightField(List.fill(size)(emptyCard))

  def generateDeck(): List[Card] = {
    val cardNames = List(
      CardName.roter,
      CardName.schwarzer,
      CardName.blauer,
      CardName.weisser,
      CardName.boeser,
      CardName.guter,
    )
    val cardLastNames = List(
      CardLastName.Drache,
      CardLastName.Magier,
      CardLastName.Hexer,
      CardLastName.Gnom,
      CardLastName.Krieger,
      CardLastName.Reiter,
    )

    val deckBuffer = ListBuffer[Card]()
    for {
      firstName <- cardNames
      lastName <- cardLastNames
    } {
      val atk = Random.nextInt(2701) + 300
      val defe = Random.nextInt(2701) + 300
      deckBuffer += Card(firstName, lastName, atk, defe, "deck")
    }
    deckBuffer.toList
  }
  val deck: Deck = Deck(generateDeck())
  val (startingHandPlayer1, updatedDeck1) = deck.startingHand(size)
  val (startingHandPlayer2, updatedDeck2) = updatedDeck1.startingHand(size)


  /*println("Please enter your names: ")
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
  //val firstPlayer = Player(firstName, startingHandPlayer1, emptyFightField)
  //val secondPlayer = Player(secondName, startingHandPlayer2, emptyFightField)
  val firstPlayer = Player("1", startingHandPlayer1, emptyFightField)
  val secondPlayer = Player("2", startingHandPlayer2, emptyFightField)
  val field =
    Field(size, 1, deck, firstPlayer, secondPlayer)
  val controller = Controller(field)


  val gui = Gui(controller)
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
