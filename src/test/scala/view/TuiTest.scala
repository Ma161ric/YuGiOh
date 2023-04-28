package view

import model.{Card, Field, FightField, Hand, Player}
import controller.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class TuiTest extends AnyWordSpec with Matchers{
  def fillList[A](element: A, n: Int): List[A] =
    List.fill(n)(element)

  val hand: Hand = Hand(fillList(Card.emptyCard, 6))
  val fightField: FightField = FightField(fillList(Card.emptyCard, 6))
  val field: Field = Field(6, 1, 40, Player("1", false, hand, fightField), Player("2", false, hand, fightField))

  "A YuGiOh Tui" should {
    val controller = Controller(field)
    val tui = new Tui(controller)
    "print out the help on input 'help' or 'p'" in {
      val help: Unit = tui.printhelp()
      tui.processInputLine("help") should be (help)
    }
    "create a new game on input 'new'" in {
      tui.processInputLine("new")
      controller.field.toString should be (field.toString)
    }
    "print out 'End Game!' and exit game on input 'exit' or 'q'" in  {
      val end = println("end game!")
      tui.processInputLine("exit") should be (end)
      tui.processInputLine("q") should be (end)
    }
    "print out 'draw card' on input 'draw' or 'd'" in  {
      val draw = println("draw card")
      tui.processInputLine("draw") should be (draw)
      tui.processInputLine("d") should be (draw)
    }
    "print out 'play card' on input 'play' or 'p'" in  {
      val play = println("play card")
      tui.processInputLine("play") should be (play)
      tui.processInputLine("p") should be (play)
    }
    "print out 'attack' on input 'attack' or 'a'" in  {
      val attack = println("attack")
      tui.processInputLine("a") should be (attack)
      tui.processInputLine("attack") should be (attack)
    }
  }

}
