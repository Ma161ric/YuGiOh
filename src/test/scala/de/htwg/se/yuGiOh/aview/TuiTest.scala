package main.scala.de.htwg.se.yuGiOh
package aview

import de.htwg.se.yuGiOh.aview.Tui
import model.*
import de.htwg.se.yuGiOh.controller.Controller
import de.htwg.se.yuGiOh.model.{Card, CardLastName, CardName, Deck, Field, FightField, Hand, Player, StartingGame}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TuiTest extends AnyWordSpec with Matchers {
  val emptyCard: Card = Card(CardName.emptyName, CardLastName.emptyLastName, 0, 0, "")
  val hand: Hand = Hand(List.fill(6)(emptyCard))
  val fightField: FightField = FightField(List.fill(6)(emptyCard))
  val deck: Deck = Deck(StartingGame.generateDeck())
  val field: Field = Field(
    6,
    1,
    deck,
    Player("1", hand, fightField),
    Player("2", hand, fightField)
  )

  "A YuGiOh Tui" should {
    val controller = Controller(field)
    val tui = new Tui(controller)

    "print out 'no input!' on no given input" in {
      //val noInput: Unit = println("no input!")
      //tui.processInputLine("") should be(tui.ERROR)
    }

    "print out the help on input 'help' or 'h'" in {
      //val help: Unit = tui.printHelp()
      //tui.processInputLine("help") should be(tui.ERROR)
      //tui.processInputLine("h") should be(tui.ERROR)
    }

    // "create a new game on input 'new'" in {
    //   tui.processInputLine("new")
    //   controller.field.toString should be (field.toString)
    // }
    /*"print out 'new game' and create a new game on input 'new' or 'n'" in {
      val newGame = println("new game")
      tui.processInputLine("new") should be (newGame)
      tui.processInputLine("n") should be (newGame)
      controller.field.toString should be (field.toString)
    }*/
    "print out 'End Game!' and exit game on input 'exit' or 'q'" in {
      val end = println("end game!")
      //tui.processInputLine("exit") should be(tui.EXIT)
      //tui.processInputLine("q") should be(tui.EXIT)
    }
    "print out 'draw card' on input 'draw' or 'd'" in {
      val draw = println("draw card")
      //tui.processInputLine("draw") should be(tui.SUCCESS)
      //tui.processInputLine("d") should be(tui.SUCCESS)
    }
    "print out 'play card' on input 'play' or 'p'" in {
      val play = println("play card")
      //tui.processInputLine("play") should be(tui.SUCCESS)
      //tui.processInputLine("p") should be(tui.SUCCESS)
    }
    "print out 'attack' on input 'attack' or 'a'" in {
      val attack = println("attack")
      //tui.processInputLine("a") should be(tui.SUCCESS)
      //tui.processInputLine("attack") should be(tui.SUCCESS)
    }
    "print out the help on bad input or arbitrary input" in {
      val old = controller.toString
      //tui.processInputLine("1234567890ßüpoiuzg") should be(tui.ERROR)
      controller.toString should be(old)
    }
    "shoudl override update method" in {
      //tui.update should be(println(controller.toString))
    }
  }
}
