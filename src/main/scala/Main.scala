import scala.io.StdIn.readLine
import model.{Field, Player}
import view.Tui

@main def run: Unit =
  val tui = new Tui

  println("Welcome to my game!")
  println("Please enter your names: ")
  print("Player 1 Name: ")
  val firstName = readLine()
  val firstPlayer = Player(firstName)

  print("Player 2 Name: ")
  val secondName = readLine()
  val secondPlayer = Player(secondName)

  while (firstPlayer.name == secondPlayer.name) {
    println("Player 1 and Player 2 cannot have the same name!")
    print("Player 2 Name: ")
    val secondName = readLine()
    val secondPlayer = Player(secondName)
  }
  println()

  print("Size of game: ")
  val size = readLine().toInt // muss integer sein, sollte wert 6 haben

  val field = Field(size)
  //val field = tui.processInputLine(size, field)

  println(field.toString)
  println(field.mesh(size,size))
  println(field.innerBar(1,1))
  println(field.innerBar(1,2))
  println(field.innerBar(1,1))



