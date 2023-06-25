package de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

import de.htwg.se.yuGiOh.model.fieldComponent.CardInterface

import scala.util.{Random, Try}
import scala.collection.mutable.ListBuffer
import com.google.inject.Inject

sealed trait CardName {
  def firstName: String
  override def toString: String = firstName
}

object CardName:

  case object Roter extends CardName {
    val firstName = "Roter"
  }

  case object Schwarzer extends CardName {
    val firstName = "Schwarzer"
  }

  case object Blauer extends CardName {
    val firstName = "Blauer"
  }

  case object Weisser extends CardName {
    val firstName = "Weisser"
  }

  case object Boeser extends CardName {
    val firstName = "Fieser"
  }

  case object Guter extends CardName {
    val firstName = "Guter"
  }
  case object EmptyName extends CardName {
    val firstName = " "
  }
  def apply(name: String): CardName = new CardName {
    val firstName: String = name
  }

sealed trait CardLastName {
  def lastName: String
  override def toString: String = lastName
}

object CardLastName:
  case object Drache extends CardLastName {
    val lastName = "Drache"
  }

  case object Magier extends CardLastName {
    val lastName = "Magier"
  }

  case object Hexer extends CardLastName {
    val lastName = "Hexer"
  }

  case object Gnom extends CardLastName {
    val lastName = "Gnom"
  }

  case object Reiter extends CardLastName {
    val lastName = "Reiter"
  }

  case object Krieger extends CardLastName {
    val lastName = "Krieger"
  }

  case object EmptyLastName extends CardLastName {
    val lastName = " "
  }

  def apply(name: String): CardLastName = new CardLastName {
    val lastName: String = name
  }

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

  def toXml(): scala.xml.Elem = {
    <card>
      <firstName>{firstName}</firstName>
      <lastName>{lastName}</lastName>
      <atk>{atk}</atk>
      <defe>{defe}</defe>
      <position>{position}</position>
    </card>
  }
