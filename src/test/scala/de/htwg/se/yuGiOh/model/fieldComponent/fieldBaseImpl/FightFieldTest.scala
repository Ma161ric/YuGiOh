package de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

import de.htwg.se.yuGiOh.model.fieldComponent._
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.*

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class FightFieldTest extends AnyWordSpec {

  "A FightField" should {
    val card1 = Card(CardName.Roter, CardLastName.Drache, 2000, 1500)
    val card2 = Card(CardName.Schwarzer, CardLastName.Magier, 1800, 2000)
    val card3 = Card(CardName.Blauer, CardLastName.Hexer, 1600, 1800)
    val fightField = FightField(List(card1, card2, card3))

    "contain a list of cards" in {
      fightField.getCards should be(List(card1, card2, card3))
    }

    "return the size of the fight field" in {
      fightField.getSize should be(3)
    }

    "return the formatted string representation of the first names of the cards" in {
      val expected =
        "|          |Roter     |Schwarzer |Blauer    |" +
          sys.props("line.separator")
      fightField.cardsFirstName(10, fightField.getCards) should be(expected)
    }

    // Add more tests for the other methods

    "serialize to XML" in {
      val xml =
        <fightField>
      {card1.toXml()}{card2.toXml()}{card3.toXml()}
    </fightField>
      fightField.toXml().toString should be(xml.toString)
    }
  }
}
