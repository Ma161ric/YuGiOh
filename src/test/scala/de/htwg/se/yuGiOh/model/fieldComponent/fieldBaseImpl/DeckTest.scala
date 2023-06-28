package de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

import de.htwg.se.yuGiOh.model.fieldComponent._
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.*

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class DeckTest extends AnyWordSpec {

  "A Deck" should {
    val card1 = Card(CardName.Roter, CardLastName.Drache, 2000, 1500)
    val card2 = Card(CardName.Schwarzer, CardLastName.Magier, 1800, 2000)
    val card3 = Card(CardName.Blauer, CardLastName.Hexer, 1600, 1800)
    val card4 = Card(CardName.Weisser, CardLastName.Gnom, 1500, 1600)
    val card5 = Card(CardName.Boeser, CardLastName.Reiter, 1400, 1500)
    val deck = Deck(List(card1, card2, card3, card4, card5))

    "contain a list of cards" in {
      deck.getDeck should be(List(card1, card2, card3, card4, card5))
    }

    "return the number of cards in the deck" in {
      deck.getDeckCount should be(5)
    }

    "create a deck iterator" in {
      val iterator = deck.createIterator
      iterator.hasNext should be(true)
      iterator.next() should be(card1)
      iterator.next() should be(card2)
      iterator.next() should be(card3)
      iterator.next() should be(card4)
      iterator.next() should be(card5)
      iterator.hasNext should be(false)
    }

    /* "generate a starting hand and updated deck" in {
      val (startingHand, updatedDeck) = deck.startingHand(4)
      startingHand.getCards should have size 4
      startingHand.getCards should contain allOf (card1, card2, card3)
      updatedDeck.getDeck should have size 2
      updatedDeck.getDeck should contain allOf (card4, card5)
    } */
  }
}

class DeckIteratorSpec extends AnyWordSpec {

  "A DeckIterator" should {
    val card1 = Card(CardName.Roter, CardLastName.Drache, 2000, 1500)
    val card2 = Card(CardName.Schwarzer, CardLastName.Magier, 1800, 2000)
    val card3 = Card(CardName.Blauer, CardLastName.Hexer, 1600, 1800)
    val deck = Deck(List(card1, card2, card3))
    val iterator = DeckIterator(deck)

    "have a next card" in {
      iterator.hasNext should be(true)
    }

    "return the next card in the deck" in {
      iterator.next() should be(card1)
      iterator.next() should be(card2)
      iterator.next() should be(card3)
    }

    "not have a next card after reaching the end of the deck" in {
      iterator.hasNext should be(false)
    }
  }
}
