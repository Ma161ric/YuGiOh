package de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.yuGiOh.util._
import de.htwg.se.yuGiOh.model.fieldComponent.{
  FieldInterface,
  PlayerInterface
}
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.{
  Card,
  CardName,
  CardLastName
}

class PlayCardCommand(
    field: FieldInterface,
    cardIndex: Int
) extends Command[FieldInterface]:
  override def doStep(field: FieldInterface): FieldInterface =
    val player = {
      if (field.getRound % 2 == 0) {
        val player2 = field.getPlayer2
        player2
      } else {
        val player1 = field.getPlayer1
        player1
      }
    }
    val chosenCard = player.getHand.getCards(cardIndex)

    val updatedFightField = {
      val fightField = player.getFightField
      val fightFieldCards = fightField.fightField
      val firstCardIndex = fightFieldCards.indexWhere(_.getFirstName == " ")
      if (firstCardIndex >= 0)
        fightField.copy(fightField = fightFieldCards.updated(firstCardIndex, chosenCard))
      else
        fightField
    }
    val updatedHand = {
      val hand = player.getHand
      val handCards = hand.hand
      val firstCardIndex = handCards.indexWhere(_.getFirstName == chosenCard.getFirstName)
      if (firstCardIndex >= 0)
        hand.copy(hand = handCards.updated(firstCardIndex, chosenCard.createEmptyCard()))
      else
        hand
    }
    val updatedPlayer = player.copy(fightField = updatedFightField, hand = updatedHand)
    val updatedField = {
      if (field.getRound % 2 == 0) {
        field.copy(
          player2 = updatedPlayer,
        )
      } else {
        field.copy(
          player1 = updatedPlayer,
        )
      }
    }
    updatedField

  override def undoStep(field: FieldInterface): FieldInterface =
    val temp = this.field // to do
    //this.field = field
    temp

  override def redoStep(field: FieldInterface): FieldInterface =
    val temp = this.field
    //this.field = field
    temp
