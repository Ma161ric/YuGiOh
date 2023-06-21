package de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.yuGiOh.model.fieldComponent.FieldInterface
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.Deck
import de.htwg.se.yuGiOh.util.Command
import de.htwg.se.yuGiOh.model.playerComponent._

class DrawCommand (var field: FieldInterface) extends Command[FieldInterface]:
  //val controller: Controller = Controller(field)

  override def doStep(field: FieldInterface): FieldInterface =
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

  override def undoStep(field: FieldInterface): FieldInterface =
    val temp = this.field //to do: ausführliches undo und redo
    this.field = field
    temp

  override def redoStep(field: FieldInterface): FieldInterface =
    val temp = this.field
    this.field = field
    temp