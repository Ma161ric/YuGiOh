package de.htwg.se.yuGiOh.controller

import de.htwg.se.yuGiOh.model.{Deck, Field, Player}
import de.htwg.se.yuGiOh.util.UndoManager
import de.htwg.se.yuGiOh.util.Command
import de.htwg.se.yuGiOh.util.Move


class DoCommand(move: Move, var field: Field) extends Command[Field]:

  val controller: Controller = Controller(field)
  override def doStep(field: Field): Field =
    move.value match {
      case "draw" =>
        val (firstCard, updatedDeck) = field.getDeck.deck match {
          case Nil => throw new NoSuchElementException("Deck is empty")
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
        field.copy(deck = updatedDeck, player1 = player1, player2 = player2)
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