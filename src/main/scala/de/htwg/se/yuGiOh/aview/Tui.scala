package de.htwg.se.yuGiOh
package aview

import controller.GameController
import util.{Event, Observer}

import scala.annotation.tailrec
import scala.io.StdIn.readLine
import scala.util.{Failure, Success, Try}

class Tui(controller: GameController) extends Observer:
  controller.add(this)

  val ERROR: Int = -1
  val EXIT: Int = 0
  val SUCCESS: Int = 1

  override def update(e: Event): Unit = e match
    case Event.Attack =>
      println("Attack!")
      //println(controller.field.toString)
    case Event.GameOver =>
      println("Game over!")
      //if (controller.player1Won) println("Spieler 1 hat das Spiel gewonnen!")
      //else if (controller.player2Won) println("Spieler 1 hat das Spiel gewonnen!")
    case Event.Quit => sys.exit
    case _ =>

  def run(): Unit =
    println(controller.toString) // remember: was controller.field.tostring
    controller.printhelp()
    inputLoop()

  private def inputLoop(): Unit =
    processInputLine(readLineTry()) match {
      case ERROR => controller.printHelp()
      case EXIT =>
        print("bye\n")
        System.exit(0)
      case SUCCESS => print("\n\n")
    }
    inputLoop()

  def readLineTry(): Try[String] = Try(readLine())

  def stringLength(input: Try[String]): Option[Int] = input.toOption.map(_.length)

  def processInputLine(input: Try[String]): Int =
    /*if (input.isEmpty)
          print("no input!\n")
          return ERROR*/ //to do
    val inputStrings: Try[Array[String]] = input.map(_.split(" "))
    val inputStringIndex0Option: Option[String] = inputStrings.toOption.flatMap(_.headOption)
    val inputIndex1Option: Option[String] = inputStrings.toOption.flatMap(_.lift(1))
    val inputIndex2Option: Option[String] = inputStrings.toOption.flatMap(_.lift(2))
    //val inputStringIndex0: String = inputStringIndex0Option.getOrElse("")
    val inputIndex1String: String = inputIndex1Option.getOrElse("")
    val inputIndex2String: String = inputIndex2Option.getOrElse("")

    val inputLength: Option[Int] = stringLength(input)

    input match {
      case Success("exit" | "q") =>
        println("end game!")
        EXIT
      case Success("help" | "h") =>
        controller.printHelp()
        ERROR
      case Success("new" | "n") =>
        println("new game!")
        run()
        SUCCESS
      case Success("draw" | "d") =>
        println("draw card")
        //to do: joa mal gucken das es wieder läuft
        //if (!controller.drawCard()) {
        //  println("already drew a card")
        //  return ERROR
        //}
        // hier einfach state updaten welcher spieler dran ist
        // und dann einfach nur sagen das der spieler zieht also ist dann klar wer ziehen muss
        SUCCESS
      case Success("play" | "p") =>
        println("play card")
        SUCCESS
      case Success("attack" | "a") =>
        if (inputLength.exists(_ >= 3)) {
          val opponentsCard = inputIndex1String
          // sollen wir hier das als index machen?
          //to do: klingt gut! wenn die zeit reicht machen wir das noch, es ist auf der to do liste
          val playersCard = inputIndex2String
          // hier auch index, also nur eine zahl übergeben
          println(s"Attack with $playersCard on $opponentsCard")
          //to do: wieder zum laufen kriegen dass controll.attack richtig funkt hier
          if (controller.attack(playersCard, opponentsCard)) {
            println("attack successful")
            SUCCESS
          } else {
            println("attack failed")
            ERROR
          }
        } else {
          println(
            "Invalid attack command. Provide both the card to attack and the card used to attack."
          )
          ERROR
        }
      case "next" =>
        println("next player")
        if (controller.nextPlayer()) {
          println("already drew a card")
          return ERROR
        }
        //to do
        // hier einfach state updaten welcher spieler dran ist
        // und dann einfach nur sagen das der spieler zieht also ist dann klar wer ziehen muss
        SUCCESS
      case Success(_) =>
        print("no input!\n")
        controller.printHelp()
        SUCCESS
      case Failure(_) =>
        print("input error!\n")
        ERROR
    }

  /*
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
