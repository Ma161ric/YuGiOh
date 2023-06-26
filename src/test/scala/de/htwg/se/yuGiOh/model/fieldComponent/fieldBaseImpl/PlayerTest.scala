package test.scala.de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

import main.scala.de.htwg.se.yuGiOh.model.fieldComponent._
import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class PlayerSpec extends AnyWordSpec {

  "A Player" should {
    val card1 = Card(CardName("Roter"), CardLastName("Drache"), 2000, 1500)
    val card2 =
      Card(CardName("Schwarzer"), CardLastName("Magier"), 1800, 2000)
    val card3 = Card(CardName("Blauer"), CardLastName("Hexer"), 1600, 1800)
    val hand = Hand(List(card1, card2)) // Create a test hand
    val fightField = FightField(List(card1, card2, card3))

    val player = Player("Player 1", hand, fightField, 1000)

    "have a name" in {
      player.getName should be("Player 1")
    }

    "have a hand" in {
      player.getHand should be(hand)
    }

    "have a fight field" in {
      player.getFightField should be(fightField)
    }

    "have LP (life points)" in {
      player.getLp should be(1000)
    }

    // Add more tests for other player actions and methods

    "serialize to XML" in {
      val xml =
        <player>
      <name>Player 1</name>
      <hand>{hand.toXml()}</hand>
      <fightField>{fightField.toXml()}</fightField>
      <lp>1000</lp>
    </player>
      player.toXml().toString should be(xml.toString)
    }
  }
}
