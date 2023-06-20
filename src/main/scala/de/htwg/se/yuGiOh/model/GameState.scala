package de.htwg.se.yuGiOh.model

trait GameState {
  def startTurn(): Unit
  def endTurn(): Unit
  def playCard(card: Card): Unit
}

class PlayerTurnState extends GameState {
  override def startTurn(): Unit = {
    // Implementierung für den Start des Spielers
  }

  override def endTurn(): Unit = {
    // Implementierung für das Beenden des Spielers
  }

  override def playCard(card: Card): Unit = {
    // Implementierung für das Spielen einer Karte während des Spielerzugs
  }
}

class OpponentTurnState extends GameState {
  override def startTurn(): Unit = {
    // Implementierung für den Start des Gegners
  }

  override def endTurn(): Unit = {
    // Implementierung für das Beenden des Gegners
  }

  override def playCard(card: Card): Unit = {
    // Implementierung für das Spielen einer Karte während des Gegnerzugs
  }
}
