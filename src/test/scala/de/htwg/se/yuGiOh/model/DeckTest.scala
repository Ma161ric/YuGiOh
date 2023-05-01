package main.scala.de.htwg.se.yuGiOh
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class DeckTest extends AnyWordSpec {
  "A Deck" when {
    "empty new" should {
      val deck = Deck(List(Card.emptyCard))
      "have no Cards" in {
        deck.getDeck should be(List(Card.emptyCard))
      }
    }
  }
}
