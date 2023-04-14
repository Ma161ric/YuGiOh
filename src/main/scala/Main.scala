package main.scala
import scala.io.StdIn.readLine

import model.Field

@main def run: Unit =
  println("Welcome to my game!")
  println("Please enter your names: ")
  print("Player 1 Name: ")
  val firstName = readLine()

  print("Player 2 Name: ")
  val lastName = readLine()

  while (firstName == lastName) {
    println("Player 1 and Player 2 cannot have the same name!")
    print("Player 2 Name: ")
    val lastName = readLine()
  }
  println()

  print("Size of game: ")
  val size = readLine().toInt // muss integer sein

  var field = new Field(size)
  println(field.toString)
