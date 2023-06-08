package de.htwg.se.yuGiOh
package aview

import controller.Controller
import util.{Observer, Event}

import scala.io.StdIn.readLine

class Tui(controller: Controller) extends Observer:
  controller.add(this)

  override def update(e: Event): Unit = e match
    case Event.Attack =>
      println(controller.field.toString)
    case Event.GameOver =>
      println("Game over!")
      //if (controller.player1Won) println("Spieler 1 hat das Spiel gewonnen!")
      //else if (controller.player2Won) println("Spieler 1 hat das Spiel gewonnen!")
    case Event.Quit => sys.exit

  def run() =
    println(controller.field.toString)
    controller.printhelp()
    inputLoop()

  val ERROR = -1
  val EXIT = 0
  val SUCCESS = 1

  private def inputLoop(): Unit =
    processInputLine(readLine) match {
      case ERROR => controller.printhelp()
      case EXIT =>
        print("bye\n")
        System.exit(0)
      case SUCCESS => print("\n\n")
    }
    inputLoop()

  def processInputLine(input: String): Int =
    if (input.isEmpty)
      print("no input!\n")
      return ERROR
    val in = input.split(" ")
    in(0) match
      case "exit" | "q" =>
        println("end game!")
        EXIT
      case "help" | "h" =>
        controller.printhelp()
        ERROR
      // case "new" | "n" =>
      //   println("new game")
      //   run()
      case "draw" | "d" =>
        println("draw card")
        SUCCESS
      case "play" | "p" =>
        println("play card")
        SUCCESS
      case "attack" | "a" =>
        println("attack")
        SUCCESS
      case _ =>
        controller.printhelp()
        ERROR

  //override def update(): Unit =  println(controller.field.toString)

  /*def fillList[A](element: A, n: Int): List[A] =
    List.fill(n)(element)*/

  /*def setInitialHand(size: Int): Unit =
    // ziehe die ersten drei karten vom deck
    val hand = Hand(List.fill(6)(Card.emptyCard))*/

  /*def emptyHand(size: Int): Unit =
    val emptyHand = Hand(List.fill(size)(Card.emptyCard))
    controller.setHandPlayer(emptyHand)

  def emptyFightField(size: Int): Unit =
    val emptyFightField = FightField(List.fill(size)(Card.emptyCard))
    controller.setFightFieldPlayer1(emptyFightField)
    controller.setFightFieldPlayer2(emptyFightField)

  def chooseCardToPlayPlayer1(hand: Hand, fightField: FightField): Unit =
    println(
      "Choose which Card to Play: \n" +
        "1: " + hand.getCard(1) +
        "2: " + hand.getCard(2) +
        "3: " + hand.getCard(3) +
        "4: " + hand.getCard(4) +
        "5: " + hand.getCard(5) +
        "6: " + hand.getCard(6)
    )
    val input = readLine
    val chars = input.toCharArray
    chars(0) match
      case '1' => {
        val updatedFightField = fightField.addCard(hand.getCard(1))
        controller.setFightFieldPlayer1(updatedFightField)
      }
      case '2' => {
        val updatedFightField = fightField.addCard(hand.getCard(2))
        controller.setFightFieldPlayer1(updatedFightField)
      }
      case '3' => {
        val updatedFightField = fightField.addCard(hand.getCard(3))
        controller.setFightFieldPlayer1(updatedFightField)
      }
      case '4' => {
        val updatedFightField = fightField.addCard(hand.getCard(4))
        controller.setFightFieldPlayer1(updatedFightField)
      }
      case '5' => {
        val updatedFightField = fightField.addCard(hand.getCard(5))
        controller.setFightFieldPlayer1(updatedFightField)
      }
      case '6' => {
        val updatedFightField = fightField.addCard(hand.getCard(6))
        controller.setFightFieldPlayer1(updatedFightField)
      }
      case _ => {
        val updatedFightField = fightField.addCard(hand.getCard(1))
        controller.setFightFieldPlayer1(updatedFightField)
      }

  def chooseCardToPlayPlayer2(hand: Hand, fightField: FightField): Unit =
    println(
      "Choose which Card to Play: \n" +
        "1: " + hand.getCard(1) +
        "2: " + hand.getCard(2) +
        "3: " + hand.getCard(3) +
        "4: " + hand.getCard(4) +
        "5: " + hand.getCard(5) +
        "6: " + hand.getCard(6)
    )
    val input = readLine
    val chars = input.toCharArray
    chars(0) match
      case '1' => {
        val updatedFightField = fightField.addCard(hand.getCard(1))
        controller.setFightFieldPlayer2(updatedFightField)
      }
      case '2' => {
        val updatedFightField = fightField.addCard(hand.getCard(2))
        controller.setFightFieldPlayer2(updatedFightField)
      }
      case '3' => {
        val updatedFightField = fightField.addCard(hand.getCard(3))
        controller.setFightFieldPlayer2(updatedFightField)
      }
      case '4' => {
        val updatedFightField = fightField.addCard(hand.getCard(4))
        controller.setFightFieldPlayer2(updatedFightField)
      }
      case '5' => {
        val updatedFightField = fightField.addCard(hand.getCard(5))
        controller.setFightFieldPlayer2(updatedFightField)
      }
      case '6' => {
        val updatedFightField = fightField.addCard(hand.getCard(6))
        controller.setFightFieldPlayer2(updatedFightField)
      }
      case _ => {
        // player doesn't want to or cant play a card
      }

  def setPlayerStats(player1: Player, player2: Player): Unit =
    controller.setNamePlayer1(player1.toString)
    controller.setLpPlayer1(player1.getLp)
    controller.setNamePlayer2(player2.toString)
    controller.setLpPlayer2(player2.getLp)*/


