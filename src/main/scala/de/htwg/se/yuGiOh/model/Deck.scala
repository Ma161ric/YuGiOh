package de.htwg.se.yuGiOh.model

import de.htwg.se.yuGiOh.model.Card
import de.htwg.se.yuGiOh.model.Hand

import scala.util.Random

case class Deck(deck: List[Card]):
  def getDeck: List[Card] = deck

  def startingHand(size: Int): Hand =
    val emptyHandList: List[Card] = List.fill(size - 3)(Card(CardName.emptyName, CardLastName.emptyLastName,0,0,""))
    val startingHandList: List[Card] = Random.shuffle(deck).take(3)
    return Hand(emptyHandList ++ startingHandList)
    //val updatedDeck: List[Card] = deck.remove(randomThreeCards)
    //to do: update deck and remove the three cards that have been drawn
