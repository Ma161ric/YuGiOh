package de.htwg.se.yuGiOh.aview

import de.htwg.se.yuGiOh.controller.GameController
import de.htwg.se.yuGiOh.util.Observer

import scala.io.StdIn.readLine

class Tui(controller: GameController) extends Observer:
  controller.add(this)
  def run(): Unit =
    println(controller.toString)
    printhelp()
    inputLoop()

  val ERROR: Int = -1
  val EXIT: Int = 0
  val SUCCESS: Int = 1

  private def inputLoop(): Unit =
    processInputLine(readLine) match {
      case ERROR => printhelp()
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
        return EXIT
      case "help" | "h" =>
        printhelp()
        return ERROR
      case "new" | "n" =>
        println("new game")
        run()
        return SUCCESS
      case "draw" | "d" =>
        println("draw card")
        if (!controller.drawCard()) {
          println("already drawed a card")
          return ERROR
        }
        // hier einfach state updaten welcher spieler dran ist
        // und dann einfach nur sagen das der spieler zieht also ist dann klar wer ziehen muss
        return SUCCESS
      case "play" | "p" =>
        println("play card")
        return SUCCESS
      case "attack" | "a" =>
        if (in.length >= 3) {
          val opponentsCard = in(1).toInt
          // sollen wir hier das als index machen?
          val playersCard = in(2).toInt
          // hier auch index, also nur eine zahl übergeben
          println(s"Attack with $playersCard on $opponentsCard")
          if (controller.attack(playersCard, opponentsCard)) {
            println("attack successful")
            return SUCCESS
          } else {
            println("attack failed")
            return ERROR
          }
        } else {
          println(
            "Invalid attack command. Provide both the card to attack and the card used to attack."
          )
          return ERROR
        }
      case "next" =>
        println("next player")
        if (controller.nextPlayer()) {
          println("already drawed a card")
          return ERROR
        }
        // hier einfach state updaten welcher spieler dran ist
        // und dann einfach nur sagen das der spieler zieht also ist dann klar wer ziehen muss
        return SUCCESS

      case _ =>
        printhelp()
        return ERROR

  override def update(): Unit = println(controller.toString)

  /*def fillList[A](element: A, n: Int): List[A] =
    List.fill(n)(element)*/

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

  private def printhelp(): Unit =
    print("""
      Befehlsuebersicht:
      - help | h                  : this help comment
      - exit | q                  : leaves the game
      - new  | n                  : creates new game
      - attack | a                : attack with card from player
      - draw | d                  : draw one card from deck to hand 
      - play | p                  : places card from player hand to fight field
      """ + "\n")
