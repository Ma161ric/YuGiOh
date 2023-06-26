package test.scala.de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

import main.scala.de.htwg.se.yuGiOh.model.fieldComponent._
import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.*

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class HandSpec extends AnyWordSpec {

  "A Hand" should {
    // Write tests for the methods of the Hand class
    val card1 = Card(CardName("Roter"), CardLastName("Drache"), 2000, 1500)
    val card2 =
      Card(CardName("Schwarzer"), CardLastName("Magier"), 1800, 2000)
    val hand = Hand(List(card1, card2))

    "return an iterator over the cards" in {
      val iterator = hand.iterator
      iterator.next() should be(card1)
      iterator.next() should be(card2)
      iterator.hasNext should be(false)
    }

    "return the list of cards" in {
      hand.getCards should be(List(card1, card2))
    }

    "return a specific card by index" in {
      hand.getCard(1) should be(card1)
      hand.getCard(2) should be(card2)
    }

    "return the size of the hand" in {
      hand.getSize should be(2)
    }

    // Add more tests for the other methods

    "serialize to XML" in {
      val xml =
        <hand>
      {card1.toXml()}{card2.toXml()}
    </hand>
      hand.toXml().toString should be(xml.toString)
    }
  }
}
