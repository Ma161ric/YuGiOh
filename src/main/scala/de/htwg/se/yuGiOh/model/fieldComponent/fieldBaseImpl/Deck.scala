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

  /*private def generateDeck(): List[Card] = {
    val cardNames = List(
      CardName.roter,
      CardName.schwarzer,
      CardName.blauer,
      CardName.weisser,
      CardName.boeser,
      CardName.guter,
      CardName.emptyName
    )
    val cardLastNames = List(
      CardLastName.Drache,
      CardLastName.Magier,
      CardLastName.Hexer,
      CardLastName.Gnom,
      CardLastName.Krieger,
      CardLastName.Reiter,
      CardLastName.emptyLastName
    )

    val deckBuffer = ListBuffer[Card]()
    for {
      firstName <- cardNames
      lastName <- cardLastNames
    } {
      val atk = Random.nextInt(2701) + 300
      val defe = Random.nextInt(2701) + 300
      deckBuffer += Card(firstName, lastName, atk, defe, "deck")
    }
    deckBuffer.toList
  } */

  def startingHand(size: Int): (Hand, Deck) =
    val emptyHandList: List[Card] = List.fill(size - 3)(
      Card(CardName.EmptyName, CardLastName.EmptyLastName, 0, 0, "")
    )
    val startingHandList: List[Card] = Random.shuffle(deck).take(3)
    val startingHand: Hand = Hand(startingHandList ++ emptyHandList)
    val updatedDeck: List[Card] = deck.filterNot(startingHandList.contains)
    (startingHand, Deck(updatedDeck))
    // to do: update deck and remove the three cards that have been drawn
    // -> i think that the to do has been done because it works in the game but i dont remember anything
