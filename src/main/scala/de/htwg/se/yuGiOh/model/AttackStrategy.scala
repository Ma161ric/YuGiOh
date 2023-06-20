package de.htwg.se.yuGiOh
package model

trait AttackStrategy {
  def attack(field: Field, opponentsCard: Int, playersCard: Int): Unit
}

// evtl sollten hier nur die indizes übergeben werden mit dem field
object AttackStrategyAttAtt extends AttackStrategy {
  override def attack(
      field: Field,
      opponentsCard: Int,
      playersCard: Int
  ): Unit = {

    val diff = opponentsCard - playersCard
    val opponentLP = field.player2.getLp
    val updatedLP = opponentLP.toInt - diff

    // Update opponent's life points with the new value (e.g., setLifePoints(opponentsCard, updatedLP))
    println(s"attack vs attack: Opponent's life points reduced by $diff")
  }
}

object AttackStrategyAttDef extends AttackStrategy {
  override def attack(
      field: Field,
      opponentsCard: Int,
      playersCard: Int
  ): Unit = {
    // Destroy the opponent's card (e.g., field.destroyCard(opponentsCard))
    val diff = opponentsCard - playersCard
    val opponentLP = field.player2.getLp
    val updatedLP = opponentLP.toInt - diff

    // Update opponent's life points with the new value (e.g., field.player2.setLp(updatedLP))
    println(s"attack vs def: Opponent's life points reduced by $diff")
  }
}

object AttackStrategyDefAtt extends AttackStrategy {
  override def attack(
      field: Field,
      opponentsCard: Int,
      playersCard: Int
  ): Unit = {
    // Destroy the player's card (e.g., field.destroyCard(playersCard))
    val diff = playersCard - opponentsCard
    val opponentLP = field.player2.getLp
    val updatedLP = opponentLP.toInt - diff

    // Update opponent's life points with the new value (e.g., field.player2.setLp(updatedLP))
    println(s"def vs attack: Opponent's life points reduced by $diff")
  }
}

object AttackStrategyDefDef extends AttackStrategy {
  override def attack(
      field: Field,
      opponentsCard: Int,
      playersCard: Int
  ): Unit = {
    // No action required, both cards are in defense position
    println("def vs def: No action taken")
  }
}
