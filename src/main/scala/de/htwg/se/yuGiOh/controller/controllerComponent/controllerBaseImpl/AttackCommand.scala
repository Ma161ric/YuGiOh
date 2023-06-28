package de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.yuGiOh.model.fieldComponent.FieldInterface
import de.htwg.se.yuGiOh.util.Command

class AttackCommand(var field: FieldInterface) extends Command[FieldInterface] {
  override def doStep(field: FieldInterface): FieldInterface = {
    // to do
    field
  }

  override def undoStep(field: FieldInterface): FieldInterface =
    val temp = this.field // to do
    this.field = field
    temp

  override def redoStep(field: FieldInterface): FieldInterface =
    val temp = this.field
    this.field = field
    temp
}
