package main.scala.de.htwg.se.yuGiOh.aview

import main.scala.de.htwg.se.yuGiOh.controller.controllerComponent.ControllerInterface
import main.scala.de.htwg.se.yuGiOh.controller.controllerComponent.*
import main.scala.de.htwg.se.yuGiOh.util.{Event, Observer}

import scala.swing.Swing
import scala.annotation.tailrec
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.io.StdIn.readLine
import scala.util.{Failure, Success, Try}
import java.io.{BufferedReader, InputStreamReader}
import java.util.concurrent.LinkedBlockingQueue

class Tui(controller: ControllerInterface) extends Observer:
  controller.add(this)

  val ERROR: Int = -1
  val EXIT: Int = 0
  val SUCCESS: Int = 1

  override def update(e: Event): Unit = e match
    case Event.Attack =>
      println("Attack!")
    case Event.GameOver =>
      println("Game over!")
    // to do:
    // if (controller.player1Won) println("Spieler 1 hat das Spiel gewonnen!")
    // else if (controller.player2Won) println("Spieler 1 hat das Spiel gewonnen!")
    case Event.Quit => sys.exit
    case _          =>

  def run(): Unit =
    println(controller.toString)
    controller.printHelp()
    inputLoop()

  private def inputLoop(): Unit =
    val inputQueue = new LinkedBlockingQueue[String]()

    // for gui docker
    /*Future {
      while (true) {
        val input = readLine()
        inputQueue.put(input)
      }
    }

    Swing.onEDT {
      while (true) {
        val input = inputQueue.take()
        processInputLine(Try(input)) match {
          case ERROR =>
            controller.printHelp()
          case EXIT =>
            print("bye\n")
            System.exit(0)
          case SUCCESS =>
            print("\n\n")
        }
      }
    }*/

    processInputLine(readLineTry()) match {
      case ERROR => controller.printHelp()
      case EXIT =>
        print("bye\n")
        System.exit(0)
      case SUCCESS => print("\n\n")
    }
    // remember: comment out processInputLine if using docker gui!"
    inputLoop()

  def readLineTry(): Try[String] = Try(readLine())

  def stringLength(input: Try[String]): Option[Int] =
    input.toOption.map(_.length)

  def processInputLine(input: Try[String]): Int =
    val inputString = input.getOrElse("")
    val inputStrings: Try[Array[String]] = input.map(_.split(" "))
    val inputStringIndex0Option: Option[String] =
      inputStrings.toOption.flatMap(_.headOption)
    val inputIndex1Option: Option[String] =
      inputStrings.toOption.flatMap(_.lift(1))
    val inputIndex2Option: Option[String] =
      inputStrings.toOption.flatMap(_.lift(2))
    val inputStringIndex0: String = inputStringIndex0Option.getOrElse("")
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
        if (!controller.drawCard()) {
          println("already drew a card")
          return ERROR
        }
        // to do: state updaten welcher spieler dran ist
        // und dann einfach nur sagen das der spieler zieht also ist dann klar wer ziehen muss
        SUCCESS
      case Success("play" | "p") =>
        println("play card")
        SUCCESS
      case Success("attack" | "a") =>
        if (inputLength.exists(_ >= 3)) {
          val opponentsCard = inputIndex1String
          val playersCard = inputIndex2String
          println(s"Attack with $playersCard on $opponentsCard")
          if (controller.attack(1, 2)) { // to do: only index is given
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
      case Success("next" | "n") =>
        println("next player")
        val nextRound: Int = controller.getField.getRound + 1
        if (controller.newRound(nextRound)) {
          println("already drew a card")
          return ERROR
        }
        // to do: state updaten welcher spieler dran ist
        // und dann einfach nur sagen das der spieler zieht also ist dann klar wer ziehen muss
        SUCCESS
      case Success("save" | "s") =>
        print("Save Game!\n")
        controller.save()
        SUCCESS
      case Success("load" | "l") =>
        print("Load Game!\n")
        controller.load()
        SUCCESS
      case Success(_) =>
        print("no input!\n")
        controller.printHelp()
        SUCCESS
      case Failure(_) =>
        print("input error!\n")
        ERROR
    }
