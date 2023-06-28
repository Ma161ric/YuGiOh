package de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.yuGiOh.model.fieldComponent.{
  FieldInterface,
  PlayerInterface
}
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.Deck
import de.htwg.se.yuGiOh.util.Command

class DrawCommand(field: FieldInterface) extends Command[FieldInterface]:
  override def doStep(field: FieldInterface): FieldInterface =
    val (firstCard, updatedDeck) = field.getDeck.deck match {
      case Nil          => throw new NoSuchElementException("Deck is empty")
      case head :: tail => (head, Deck(tail))
    }
    val player = {
      if (field.getRound % 2 == 0) {
        val player2 = field.getPlayer2
        player2
      } else {
        val player1 = field.getPlayer1
        player1
      }
    }
    val updatedHand = {
      val hand = player.getHand
      val handCards = hand.hand
      val firstCardIndex = handCards.indexWhere(_.getFirstName == " ")
      if (firstCardIndex >= 0)
        hand.copy(hand = handCards.updated(firstCardIndex, firstCard))
      else
        hand
    }
    val updatedPlayer = player.copy(hand = updatedHand)
    val updatedField = {
      if (field.getRound % 2 == 0) {
        field.copy(
          deck = updatedDeck,
          player2 = updatedPlayer,
        )
      } else {
        field.copy(
          deck = updatedDeck,
          player1 = updatedPlayer,
        )
      }
    }
    updatedField
  // to do: Option und so immer nutzen bei zB: prüfung ob karte leer ist, wenn das deck leer ist, man nicht mehr  ziehen kann weil hand voll ist

  override def undoStep(field: FieldInterface): FieldInterface =
    val deck = field.getDeck
    val player = {
      if (field.getRound % 2 == 0) {
        val player2 = field.getPlayer2
        player2
      } else {
        val player1 = field.getPlayer1
        player1
      }
    }
    val (updatedHand, cardToUndo) = {
      val hand = player.getHand
      val reversedHandCards = hand.hand.reverse
      val firstCardIndex = reversedHandCards.indexWhere(_.getFirstName != " ")

      val cardToUndo = reversedHandCards(firstCardIndex)
      val emptyCard = cardToUndo.createEmptyCard()
      (hand.copy(hand = reversedHandCards.updated(firstCardIndex, emptyCard).reverse), cardToUndo)
    }
    val updatedDeck = deck.copy(deck = cardToUndo :: field.getDeck.getDeck)
    val updatedPlayer = player.copy(hand = updatedHand)
    val updatedField = {
      if (field.getRound % 2 == 0) {
        field.copy(
          deck = updatedDeck,
          player2 = updatedPlayer,
        )
      } else {
        field.copy(
          deck = updatedDeck,
          player1 = updatedPlayer,
        )
      }
    }
    updatedField

  override def redoStep(field: FieldInterface): FieldInterface =
    val (firstCard, updatedDeck) = field.getDeck.deck match {
      case Nil => throw new NoSuchElementException("Deck is empty")
      case head :: tail => (head, Deck(tail))
    }
    val player = {
      if (field.getRound % 2 == 0) {
        val player2 = field.getPlayer2
        player2
      } else {
        val player1 = field.getPlayer1
        player1
      }
    }
    val updatedHand = {
      val hand = player.getHand
      val handCards = hand.hand
      val firstCardIndex = handCards.indexWhere(_.getFirstName == " ")
      if (firstCardIndex >= 0)
        hand.copy(hand = handCards.updated(firstCardIndex, firstCard))
      else
        hand
    }
    val updatedPlayer = player.copy(hand = updatedHand)
    val updatedField = {
      if (field.getRound % 2 == 0) {
        field.copy(
          deck = updatedDeck,
          player2 = updatedPlayer,
        )
      } else {
        field.copy(
          deck = updatedDeck,
          player1 = updatedPlayer,
        )
      }
    }
    updatedField
