package view

import model.{Card, Field, Player}

class Tui {
  def processInputLine(size: Int, input: String, field: Field, player1: Player, player2: Player): Unit = {
    input match
      case "q" =>
        println("End Game!")
      case "1" =>
        player1.turnTrue()
        println("Player " + player1.toString + " starts first!")
        println(field.mesh(10, size, Card.roterDrache, player1, player2))
      case "2" =>
        player2.turnTrue()
        println("Player " + player2.toString + " starts first!")
        println(field.mesh(10, size, Card.roterDrache, player2, player1))
  }
}
