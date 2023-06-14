package de.htwg.se.yuGiOh
package controller

import model.{Card, Deck, Field, FightField, Hand}
import util.Observable
import util.Event


case class Controller(var field: Field) extends Observable {
  override def toString: String = field.toString

  /*def attack(opponentsCard: String, playersCard: String): Unit =
    1*/


  def drawCard(hand: Hand, deck: Deck): (Deck, Hand) =
    val (firstCard, updatedDeck) = deck.deck match {
      case Nil => throw new NoSuchElementException("Deck is empty")
      case head :: tail => (head, Deck(tail))
    }

    val updatedHand = hand.copy(hand = {
      var replacementDone = false
      hand.hand.map { card =>
        if (!replacementDone && card.getFirstName == "No") {
          replacementDone = true
          firstCard
        } else {
          card
        }
      }
    })
    field = field.copy(deck = updatedDeck)
    notifyObservers(Event.Draw)
    (updatedDeck, updatedHand)

  def skip(): Unit = {
    val currentRound = field.getRound
    field = field.copy(round = currentRound + 1)
    notifyObservers(Event.Skip)
  }

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

  // def countRound(fightField: FightField, round: Int) =
  //   val newRound = round + 1
  //   fightField.innerRoundBar(10, fightField.getSize, newRound)
  //   notifyObservers

  // def setFightFieldPlayer1(fightField: FightField) =
  //   fightField.playerRow(10, fightField.getSize, fightField.getCards, 40)
  //   notifyObservers

  // def setFightFieldPlayer2(fightField: FightField) =
  //   fightField.playerRow(10, fightField.getSize, fightField.getCards, 40)
  //   notifyObservers*/
  def printhelp(): Unit =
    print(
      """
      Befehlsuebersicht:
      - help | h                  : this help comment
      - exit | q                  : leaves the game
      - new  | n                  : creates new game
      - attack | a                : attack with card from player
      - draw | d                  : draw one card from deck to hand
      - play | p                  : places card from player hand to fight field
      """ + "\n")

  def quit(): Unit =
    System.exit(0)
}
