package de.htwg.se.yuGiOh.controller

import de.htwg.se.yuGiOh.model.{Card, Deck, Field, FightField, Hand}
import de.htwg.se.yuGiOh.util.Observable

case class Controller(var field: Field) extends Observable {
  override def toString: String = field.toString

  def attack(opponentsCard: String, playersCard: String): Unit =
    1


  def drawCard(hand: Hand, deck: Deck): (Card, Deck) =
    val (firstCard, updatedDeck) = deck.deck match {
      case Nil => throw new NoSuchElementException("Deck is empty")
      case head :: tail => (head, Deck(tail))
    }

    val updatedHand = hand.copy(hand = hand.hand.map {
      case card if card.getFirstName == "No" || card.getLastName == "Card" =>
        firstCard
      case otherCard => otherCard
    })
    
    (updatedDeck, updatedHand)

  /*def drawStartingHand(): Unit =
    // ziehe die ersten drei karten vom deck
    val hand = Hand(List.fill(6)(Card.emptyCard))*/

  // def setNamePlayer1(name: String) =
  //   field.playerName(10, name)
  //   notifyObservers

  // def setNamePlayer2(name: String) =
  //   field.playerName(10, name)
  //   notifyObservers

  // def setLpPlayer1(lp: String) =
  //   field.playerLp(10, lp)
  //   notifyObservers

  // def setLpPlayer2(lp: String) =
  //   field.playerLp(10, lp)
  //   notifyObservers

  /*def setHandPlayer(hand: Hand) =
    hand.playerHandRow(10, hand.getSize, hand.getCards)
    notifyObservers
*/
  // def countRound(fightField: FightField, round: Int) =
  //   val newRound = round + 1
  //   fightField.innerRoundBar(10, fightField.getSize, newRound)
  //   notifyObservers

  // def setFightFieldPlayer1(fightField: FightField) =
  //   fightField.playerRow(10, fightField.getSize, fightField.getCards, 40)
  //   notifyObservers

  // def setFightFieldPlayer2(fightField: FightField) =
  //   fightField.playerRow(10, fightField.getSize, fightField.getCards, 40)
  //   notifyObservers
}
