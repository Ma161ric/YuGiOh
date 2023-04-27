package view

import controller.Controller
import model.{Card, Field, FightField, Hand, Player}
import util.Observer

import scala.io.StdIn.readLine

class Tui (controller: Controller) extends Observer:
  controller.add(this)
  override def update = println(controller.field.toString)

  def fillList[A](element: A, n: Int): List[A] =
    List.fill(n)(element)

  def setInitialHand(): Unit =
    //ziehe die ersten drei karten vom deck
    val hand = Hand(fillList(Card.emptyCard, 6))

  def emptyHand(size: Int): Unit =
    val emptyHand = Hand(fillList(Card.emptyCard, size))
    controller.setHandPlayer(emptyHand)

  def emptyFightField(size: Int): Unit =
    val emptyFightField = FightField(fillList(Card.emptyCard, size))
    controller.setFightFieldPlayer1(emptyFightField)
    controller.setFightFieldPlayer2(emptyFightField)

  def chooseCardToPlayPlayer1(hand: Hand, fightField: FightField): Unit =
    println("Choose which Card to Play: \n" +
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
    println("Choose which Card to Play: \n" +
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
        //player doesn't want to or cant play a card
      }

  def setPlayerStats(player1: Player, player2: Player): Unit =
    controller.setNamePlayer1(player1.toString)
    controller.setLpPlayer1(player1.getLp)
    controller.setNamePlayer2(player2.toString)
    controller.setLpPlayer2(player2.getLp)


  def run(size: Int, player1: Player, player2: Player) =
    println(controller.field.toString)

  def processInputLine(size: Int, input: String, field: Field, player1: Player, player2: Player): Unit = {
    input match
      case "q" =>
        println("End Game!")
      case "1" =>
        run(size, player1, player2)
        println("Player " + player1.toString + " starts first!")
      case "2" =>
        run(size, player2, player1)
        println("Player " + player2.toString + " starts first!")
  }

