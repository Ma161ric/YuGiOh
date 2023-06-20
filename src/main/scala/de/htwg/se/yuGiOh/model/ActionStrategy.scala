package de.htwg.se.yuGiOh.model

trait ActionStrategy {
  def performAction(field: Field): Unit
}

object DrawStrategy extends ActionStrategy {
  override def performAction(field: Field): Unit = {
    println("Karte ziehen...")
    // field.deck.drawCard(player)
  }
}

// angriff
object AttStrategy extends ActionStrategy {
  override def performAction(field: Field): Unit = {
    println("Angreifen...")
    // AttStrategy.attack(field, playersCard, opponentsCard)
  }
}

object PlayStrategy extends ActionStrategy {
  override def performAction(field: Field): Unit = {
    // Implementiere die Logik für das Legen einer Karte
    println("Karte legen...")
  }
}

object NextStrategy extends ActionStrategy {
  override def performAction(field: Field): Unit = {
    println("Zug beenden...")
    field.nextPlayer()
  }
}
