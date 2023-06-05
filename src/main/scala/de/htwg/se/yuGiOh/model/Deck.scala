package de.htwg.se.yuGiOh.model

import de.htwg.se.yuGiOh.model.Card
import de.htwg.se.yuGiOh.model.Hand

import scala.util.Random

case class Deck(deck: List[Card]):
  def getDeck: List[Card] = deck

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

  def startingHand(size: Int): Hand =
    val emptyHandList: List[Card] = List.fill(size - 3)(Card(CardName.emptyName, CardLastName.emptyLastName,0,0,""))
    val startingHandList: List[Card] = Random.shuffle(deck).take(3)
    return Hand(emptyHandList ++ startingHandList)
    //val updatedDeck: List[Card] = deck.remove(randomThreeCards)
    //to do: update deck and remove the three cards that have been drawn
