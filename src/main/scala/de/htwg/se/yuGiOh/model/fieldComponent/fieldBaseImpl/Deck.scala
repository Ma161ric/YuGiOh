package de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

import de.htwg.se.yuGiOh.model.fieldComponent.DeckInterface

import scala.util.Random
import scala.collection.Iterator

case class DeckIterator(deck: Deck) extends Iterator[Card] {
  private var currentIndex = 0

  override def hasNext: Boolean = currentIndex < deck.getDeck.size

  override def next(): Card = {
    val card = deck.getDeck(currentIndex)
    currentIndex += 1
    card
  }
}

case class Deck(deck: List[Card]) extends DeckInterface:
  def getDeck: List[Card] = deck
  def getDeckCount: Int = deck.length

  def createIterator: DeckIterator = DeckIterator(this)

  def startingHand(size: Int): (Hand, Deck) =
    val emptyHandList: List[Card] = List.fill(size - 3)(
      Card(CardName.EmptyName, CardLastName.EmptyLastName, 0, 0, "")
    )
    val startingHandList: List[Card] = Random.shuffle(deck).take(3)
    val startingHand: Hand = Hand(startingHandList ++ emptyHandList)
    val updatedDeck: List[Card] = deck.filterNot(startingHandList.contains)
    (startingHand, Deck(updatedDeck))

  def toXml(): scala.xml.Elem = {
    <deck>
    {
      { for (card <- deck) yield card.toXml() }
    }
  </deck>
  }
