package de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.yuGiOh.model.fieldComponent.FieldInterface
import de.htwg.se.yuGiOh.util.Command

class AttackCommand(field: FieldInterface, playersCardIndex: Int, opponentsCardindex: Int) extends Command[FieldInterface] {
  override def doStep(field: FieldInterface): FieldInterface = {
    if (field.getRound % 2 == 0) {
      val playerCard = field.getPlayer2.getFightField.getCards(playersCardIndex)
      val opponentCard = field.getPlayer1.getFightField.getCards(opponentsCardindex)
      val difference = { playerCard.getAtk - opponentCard.getDefe }
      val currentLp = field.getPlayer1.getLp

      val updatedOpponentFightField = field.getPlayer1.getFightField.copy(fightField = field.getPlayer1.getFightField.getCards.updated(opponentsCardindex, opponentCard.createEmptyCard()))
      val updatedOpponent = field.getPlayer1.copy(lp = currentLp - difference, fightField = updatedOpponentFightField)
      val updatedField = field.copy(player1 = updatedOpponent)
      updatedField
    } else {
      val playerCard = field.getPlayer1.getFightField.getCards(playersCardIndex)
      val opponentCard = field.getPlayer2.getFightField.getCards(opponentsCardindex)
      val difference = {
        playerCard.getAtk - opponentCard.getDefe
      }
      val currentLp = field.getPlayer1.getLp

      val updatedOpponentFightField = field.getPlayer2.getFightField.copy(fightField = field.getPlayer2.getFightField.getCards.updated(opponentsCardindex, opponentCard.createEmptyCard()))
      val updatedOpponent = field.getPlayer2.copy(lp = currentLp - difference, fightField = updatedOpponentFightField)
      val updatedField = field.copy(player2 = updatedOpponent)
      updatedField
    }
  }

  override def undoStep(field: FieldInterface): FieldInterface =
    val temp = this.field // to do
    //this.field = field
    temp

  override def redoStep(field: FieldInterface): FieldInterface =
    val temp = this.field
    //this.field = field
    temp
}
