package view

import model.Field

class Tui {
  def processInputLine(input: String, field: Field): Unit = {
    input match
      case "q" =>
        println("End Game!")
      case "1" => 
        println("Player 1 starts first!")
      case "2" =>
        println("Player 2 starts first!")
  }
}
