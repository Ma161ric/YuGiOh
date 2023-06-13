package de.htwg.se.yuGiOh
package model

import scala.util.Random
import scala.collection.mutable.ListBuffer


enum CardName(firstName: String):
  override def toString: String = firstName

  case roter extends CardName("Roter")
  case schwarzer extends CardName("Schwarzer")
  case blauer extends CardName("Blauer")
  case weisser extends CardName("Weisser")
  case boeser extends CardName("Schlimmer")
  case guter extends CardName("Guter")
  case emptyName extends CardName("No")

enum CardLastName(lastName: String):
  override def toString: String = lastName

  case Drache extends CardLastName("Drache")
  case Magier extends CardLastName("Magier")
  case Hexer extends CardLastName("Hexer")
  case Gnom extends CardLastName("Gnom")
  case Reiter extends CardLastName("Reiter")
  case Krieger extends CardLastName("Krieger")
  case emptyLastName extends CardLastName("Card")

case class Card(
    firstName: CardName,
    lastName: CardLastName,
    atk: Int,
    defe: Int,
    position: String
):
  override def toString: String = firstName.toString + lastName.toString

  def atkToString: String = atk.toString
  def defeToString: String = defe.toString
  def getFirstName: String = firstName.toString
  def getLastName: String = lastName.toString
  def getAtk: Int = atk
  def getDefe: Int = defe
  def getPosition: String = position

  private val cardNames = List(
    CardName.roter,
    CardName.schwarzer,
    CardName.blauer,
    CardName.weisser,
    CardName.boeser,
    CardName.guter,
  )
  private val cardLastNames = List(
    CardLastName.Drache,
    CardLastName.Magier,
    CardLastName.Hexer,
    CardLastName.Gnom,
    CardLastName.Krieger,
    CardLastName.Reiter,
  )

  private def deckNamesList(): List[(CardName, CardLastName)] =
    cardNames.flatMap { cardName =>
      cardLastNames.map(cardLastName => (cardName, cardLastName))
    }

  /**
  val deck: List[Card] = deckNamesList().map { case (firstName, lastName) =>
    val atk = Random.nextInt(2701) + 300 // Generates a random number between 300 and 3000
    val defe = Random.nextInt(2701) + 300 // Generates a random number between 300 and 3000
    Card(firstName, lastName, atk, defe, "deck")
  }

  val deck: List[Card] = generateDeck()


  def generateDeck(): List[Card] = {
    val deckBuffer = ListBuffer[Card]()
    for {
      firstName <- cardNames
      lastName <- cardLastNames
    } {
      val atk = Random.nextInt(2701) + 300
      val defe = Random.nextInt(2701) + 300
      deckBuffer += Card(firstName, lastName, atk, defe, "deck")
    }
    deckBuffer.toList
  }
  **/