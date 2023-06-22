package de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

trait GameState {
  def startTurn(): Unit
  def endTurn(): Unit
  def playCard(): Unit
}

class PlayerTurnState extends GameState {
  override def startTurn(): Unit = {
    // Implementierung für den Start des Spielers
  }

  override def endTurn(): Unit = {
    // Implementierung für das Beenden des Spielers
  }

  override def playCard(): Unit = {
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

  override def playCard(): Unit = {
    // Implementierung für das Spielen einer Karte während des Gegnerzugs
  }
}
