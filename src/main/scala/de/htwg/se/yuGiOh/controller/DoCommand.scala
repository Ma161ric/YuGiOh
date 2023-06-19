package de.htwg.se.yuGiOh.controller

import de.htwg.se.yuGiOh.model.{Card, CardLastName, CardName, Deck, Field, FightField, Hand, Player}
import de.htwg.se.yuGiOh.util.UndoManager
import de.htwg.se.yuGiOh.util.Command
import de.htwg.se.yuGiOh.util.Move


class DoCommand(move: Move, var field: Field, chosenCard: Card = Card(CardName.emptyName, CardLastName.emptyLastName, 0, 0, " ")) extends Command[Field]:

  val controller: Controller = Controller(field)
  override def doStep(field: Field): Field =
    move.value match {
      case "draw" =>
        val (firstCard, updatedDeck) = field.getDeck.deck match {
          case Nil => /* case failure*/ throw new NoSuchElementException("Deck is empty")
          case head :: tail => (head, Deck(tail))
        }
        var player1: Player = field.getPlayer1
        var player2: Player = field.getPlayer2

         if (field.getRound % 2 == 0) {
          var hand = player2.getHand
          val updatedHand = hand.copy(hand = {
            var replacementDone = false
            hand.hand.map { card =>
              if (!replacementDone && card.getFirstName == " ") {
                replacementDone = true
                firstCard
              } else {
                card
              }
            }
          })
          player2 = player2.copy(hand = updatedHand)
        } else {
           player1 = field.getPlayer1
           var hand = player1.getHand
           val updatedHand = hand.copy(hand = {
             var replacementDone = false
             hand.hand.map { card =>
               if (!replacementDone && card.getFirstName == " ") {
                 replacementDone = true
                 firstCard
               } else {
                 card
               }
             }
           })
           player1 = player1.copy(hand = updatedHand)
         }
        /*case success*/ field.copy(deck = updatedDeck, player1 = player1, player2 = player2) //->Option und so immer nutzen bei zB: prüfung ob karte leer ist, wenn das deck leer ist, man nicht mehr  ziehen kann weil hand voll ist
      case "playCard" =>
        /*val (firstCard, updatedDeck) = field.getDeck.deck match {
          case Nil => throw new NoSuchElementException("Deck is empty")
          case head :: tail => (head, Deck(tail))
        }*/

        var player1: Player = field.getPlayer1
        var player2: Player = field.getPlayer2

        if (field.getRound % 2 == 0) {
          var fightField: FightField = player2.getFightField
          val updatedFightField = fightField.copy(fightField = {
            var replacementDone = false
            fightField.fightField.map { card =>
              if (!replacementDone && card.getFirstName == " ") {
                replacementDone = true
                chosenCard
              } else {
                card
              }
            }
          })
          print("updatedFightField"+updatedFightField)
          player2 = player2.copy(fightField = updatedFightField)
        } else {
          player1 = field.getPlayer1
          var fightField: FightField = player1.getFightField //could be val but would make it immutable
          val updatedFightField = fightField.copy(fightField = {
            var replacementDone = false
            fightField.fightField.map { card =>
              if (!replacementDone && card.getFirstName == " ") {
                replacementDone = true
                chosenCard
              } else {
                card
              }
            }
          })
          player1 = player1.copy(fightField = updatedFightField)
        }
        field.copy(player1 = player1, player2 = player2)
    }

  override def undoStep(field: Field): Field =
    move.value match {
      case "draw" =>
        val temp = this.field
        this.field = field
        temp
    }

  override def redoStep(field: Field): Field =
    move.value match {
      case "draw" =>
        val temp = this.field
        this.field = field
        temp
    }