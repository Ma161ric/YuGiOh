package main.scala.de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl

import main.scala.de.htwg.se.yuGiOh.util._
import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.{
  FieldInterface,
  PlayerInterface
}
import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.{
  Card,
  CardName,
  CardLastName
}

class PlayCardCommand(
    var field: FieldInterface,
    chosenCard: Card = Card(CardName.Weisser, CardLastName.Drache, 2000, 3000)
) extends Command[FieldInterface]:
  override def doStep(field: FieldInterface): FieldInterface =
    var player1: PlayerInterface = field.getPlayer1
    var player2: PlayerInterface = field.getPlayer2

    if (field.getRound % 2 == 0) {
      var fightField = player2.getFightField
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
      print("updatedFightField" + updatedFightField)
      player2 = player2.copy(fightField = updatedFightField)
    } else {
      player1 = field.getPlayer1
      var fightField = player1.getFightField
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

  override def undoStep(field: FieldInterface): FieldInterface =
    val temp = this.field // to do
    this.field = field
    temp

  override def redoStep(field: FieldInterface): FieldInterface =
    val temp = this.field
    this.field = field
    temp
