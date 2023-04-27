import scala.io.StdIn.readLine
import model.{Card, Field, Hand, FightField, Player}
import view.Tui

@main def run: Unit =
  val tui = new Tui

  println("Welcome to my game!")

  print("Size of game (min 4): ")
  val size = readLine().toInt // muss integer sein, sollte wert 6 haben
  val emptyHand = Hand(size, List.fill(size)(Card.emptyCard))
  val emptyFightField = FightField(size, List.fill(size)(Card.emptyCard))
  val field = Field(size, 1, 40, emptyHand, emptyFightField)

  println("Please enter your names: ")
  print("Player 1 Name: ")
  val firstName = readLine()
  val firstPlayer = Player(firstName, false, emptyHand, emptyFightField)

  print("Player 2 Name: ")
  val secondName = readLine()
  val secondPlayer = Player(secondName, false, emptyHand, emptyFightField)

  while (firstPlayer.name == secondPlayer.name) {
    println("Player 1 and Player 2 cannot have the same name!")
    print("Player 2 Name: ")
    val secondName = readLine()
    val secondPlayer2 = Player(secondName, false, emptyHand, emptyFightField)
    println()
  }



  print("Who starts first (1 or 2)? ") // input 1 or 2 or q
  val input = readLine()
  val tuiField: Unit = tui.processInputLine(size, input, field, firstPlayer, secondPlayer)

  //println(field.toString)
  //println(field.mesh(size,size, input, Card.roterDrache))







