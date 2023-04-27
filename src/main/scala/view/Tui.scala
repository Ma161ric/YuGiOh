package view

import controller.Controller
import model.{Card, Field, FightField, Hand, Player}
import util.Observer

class Tui (controller: Controller) extends Observer:
  controller.add(this)
  override def update = println(controller.field.toString)

  def fillList[A](element: A, n: Int): List[A] =
    List.fill(n)(element)
  def emptyHand(size: Int): Unit =
    val emptyHand = Hand(fillList(Card.emptyCard, size))
    controller.setHandPlayer(emptyHand)

  def emptyFightField(size: Int): Unit =
    val emptyFightField = FightField(fillList(Card.emptyCard, size))
    controller.setFightFieldPlayer1(emptyFightField)
    controller.setFightFieldPlayer2(emptyFightField)

  def setPlayerStats(player1: Player, player2: Player): Unit =
    controller.setNamePlayer1(player1.toString)
    controller.setLpPlayer1(player1.getLp)
    controller.setNamePlayer2(player2.toString)
    controller.setLpPlayer2(player2.getLp)

  def run(size: Int, player1: Player, player2: Player) =
    //setPlayerStats(player1, player2)
    //emptyFightField(size)
    //emptyHand(size)
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

