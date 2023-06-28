package de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

import de.htwg.se.yuGiOh.model.fieldComponent._
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class CardTest extends AnyWordSpec {

  "A Card" should {
    val card = Card(CardName("Roter"), CardLastName("Drache"), 2000, 1500)

    "have a first name" in {
      card.getFirstName should be("Roter")
    }

    "have a last name" in {
      card.getLastName should be("Drache")
    }

    "have an ATK value" in {
      card.getAtk should be(2000)
    }

    "have a DEF value" in {
      card.getDefe should be(1500)
    }

    "have a position" in {
      card.getPosition should be(" ")
    }

    "return a formatted string representation" in {
      card.toString should be("RoterDrache")
    }

    "return the ATK as a string" in {
      card.atkToString should be("2000")
    }

    "return the DEF as a string" in {
      card.defeToString should be("1500")
    }

    "check if it is empty" in {
      card.isEmpty(card) should be(false)
    }

    "serialize to XML" in {
      val xml =
        <card>
      <firstName>Roter</firstName>
      <lastName>Drache</lastName>
      <atk>2000</atk>
      <defe>1500</defe>
      <position> </position>
    </card>
      card.toXml().toString should be(xml.toString)
    }
  }
}
