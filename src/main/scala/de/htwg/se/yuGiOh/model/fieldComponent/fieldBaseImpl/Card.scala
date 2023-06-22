package de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

import de.htwg.se.yuGiOh.model.fieldComponent.CardInterface

import scala.util.{Random, Try}
import scala.collection.mutable.ListBuffer
import com.google.inject.Inject

enum CardName(firstName: String):
  override def toString: String = firstName

  case roter extends CardName("Roter")
  case schwarzer extends CardName("Schwarzer")
  case blauer extends CardName("Blauer")
  case weisser extends CardName("Weisser")
  case boeser extends CardName("Fieser")
  case guter extends CardName("Guter")
  case emptyName extends CardName(" ")

enum CardLastName(lastName: String):
  override def toString: String = lastName

  case Drache extends CardLastName("Drache")
  case Magier extends CardLastName("Magier")
  case Hexer extends CardLastName("Hexer")
  case Gnom extends CardLastName("Gnom")
  case Reiter extends CardLastName("Reiter")
  case Krieger extends CardLastName("Krieger")
  case emptyLastName extends CardLastName(" ")

case class Card @Inject() (
    firstName: CardName,
    lastName: CardLastName,
    atk: Int,
    defe: Int,
    position: String = " "
) extends CardInterface:
  override def toString: String = firstName.toString + lastName.toString

  def atkToString: String = atk.toString
  def defeToString: String = defe.toString
  def getFirstName: String = firstName.toString
  def getLastName: String = lastName.toString
  def getAtk: Int = atk
  def getDefe: Int = defe
  def getPosition: String = position

  def isEmpty(card: Card): Boolean =
    Try(card.firstName.toString).toOption.exists(firstName =>
      firstName.trim.isEmpty
    )

