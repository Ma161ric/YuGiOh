package main.scala.de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl

import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.FieldInterface

trait AttackStrategy {
  def attack(field: FieldInterface, opponentsCard: Int, playersCard: Int): Unit
}

object AttackStrategyAttAtt extends AttackStrategy {
  override def attack(
      field: FieldInterface,
      opponentsCard: Int,
      playersCard: Int
  ): Unit = {

    val diff = opponentsCard - playersCard
    val opponentLP = field.getPlayer2.getLp
    val updatedLP = opponentLP.toInt - diff

    // to do: Update opponent's life points with the new value (e.g., setLifePoints(opponentsCard, updatedLP))
    println(s"attack vs attack: Opponent's life points reduced by $diff")
  }
}

object AttackStrategyAttDef extends AttackStrategy {
  override def attack(
      field: FieldInterface,
      opponentsCard: Int,
      playersCard: Int
  ): Unit = {
    // to do: Destroy the opponent's card (e.g., field.destroyCard(opponentsCard))
    val diff = opponentsCard - playersCard
    val opponentLP = field.getPlayer2.getLp
    val updatedLP = opponentLP.toInt - diff

    // to do: Update opponent's life points with the new value (e.g., field.player2.setLp(updatedLP))
    println(s"attack vs def: Opponent's life points reduced by $diff")
  }
}

object AttackStrategyDefAtt extends AttackStrategy {
  override def attack(
      field: FieldInterface,
      opponentsCard: Int,
      playersCard: Int
  ): Unit = {
    // to do: Destroy the player's card (e.g., field.destroyCard(playersCard))
    val diff = playersCard - opponentsCard
    val opponentLP = field.getPlayer2.getLp
    val updatedLP = opponentLP.toInt - diff

    // to do: Update opponent's life points with the new value (e.g., field.player2.setLp(updatedLP))
    println(s"def vs attack: Opponent's life points reduced by $diff")
  }
}

object AttackStrategyDefDef extends AttackStrategy {
  override def attack(
      field: FieldInterface,
      opponentsCard: Int,
      playersCard: Int
  ): Unit = {
    println("def vs def: No action taken")
  }
}
