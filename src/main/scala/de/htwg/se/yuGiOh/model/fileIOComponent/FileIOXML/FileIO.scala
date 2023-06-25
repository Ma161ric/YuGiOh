package de.htwg.se.yuGiOh.model.fileIOComponent.FileIOXML

import java.io.{File, FileWriter, PrintWriter}

import scala.util.{Using, Try}
import scala.xml.{Elem, XML, Node}

import de.htwg.se.yuGiOh.model.fileIOComponent._
import de.htwg.se.yuGiOh.model.fieldComponent.{FieldInterface, PlayerInterface}
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.{
  Card,
  CardName,
  CardLastName,
  FightField
}
import java.io.{File, FileWriter, PrintWriter}
import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface {

  private def createDirectory(path: String): Unit =
    val directory = new File(path)
    if (!directory.exists()) {
      directory.mkdir()
    }

  override def save(field: FieldInterface): Boolean =
    import java.io._
    createDirectory("XML")
    val pw = new PrintWriter(new File("field.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(fieldToXml(field))
    val res = Try(pw.write(xml))
    saveField(field) // to do
    saveDeck(field.getDeck)
    savePlayer(field.getPlayer1, "player1.xml")
    savePlayer(field.getPlayer2, "player2.xml")
    pw.close()
    res.isSuccess

  def saveField(field: FieldInterface): Unit = {
    scala.xml.XML.save("field.xml", fieldToXml(field))
  }

  def saveDeck(deck: Deck): Unit = {
    scala.xml.XML.save("deck.xml", deckToXml(deck))
  }

  def savePlayer(player: PlayerInterface, fileName: String): Unit = {
    saveHand(player.getHand, fileName) // noch checken
    scala.xml.XML.save(fileName, playerToXml(player))
  }

  def saveHand(hand: Hand, fileName: String): Unit = {
    scala.xml.XML.save(fileName, handToXml(hand))
  }

  def saveFightField(fightField: FightField, fileName: String): Unit = {
    scala.xml.XML.save(fileName, fightFieldToXml(fightField))
  }

  def fieldToXml(field: FieldInterface): Node = {
    <field>
      <size>{field.getSize}</size>
      <round>{field.getRound}</round>
    </field>
  }

  def deckToXml(deck: Deck): Node = {
    <deck>
        {
      deck.getDeck.map { card =>
        cardToXml(card)
      }
    }
        </deck>
  }

  def playerToXml(player: PlayerInterface): Elem = {
    <player>
      <name>{player.getName}</name>
      <hand>{handToXml(player.getHand)}</hand>
      <fightfield>{fightFieldToXml(player.getFightField)}</fightfield>
      <lp>{player.getLp}</lp>
    </player>
  }

  def handToXml(hand: Hand): Elem = {
    <hand>
        {
      hand.getCards.map { card =>
        cardToXml(card)
      }
    }
        </hand>
  }

  def fightFieldToXml(fightField: FightField): Elem = {
    <fightfield>
        {
      fightField.getCards.map { card =>
        cardToXml(card)
      }
    }
        </fightfield>
  }

  def cardToXml(card: Card): Elem = {
    <card>
        <firstName>{card.firstName}</firstName>
        <lastName>{card.lastName}</lastName>
        <atk>{card.atk}</atk>
        <defe>{card.defe}</defe>
        <position>{card.position}</position>
    </card>
  }

  override def load: (FieldInterface) = {
    val res = Try(scala.xml.XML.loadFile("field.xml"))
    if (res.isFailure) {
      println("Could not load file")
      return (null)
    }
    val field = loadField()
    (field)
  }

  def loadField(): Field = {
    val fieldFile = scala.xml.XML.loadFile("field.xml")
    println("load field sucess!")
    val deckFile = scala.xml.XML.loadFile("deck.xml")
    val player1File = scala.xml.XML.loadFile("player1.xml")
    val player2File = scala.xml.XML.loadFile("player2.xml")
    val size = (fieldFile \ "size").text.toInt
    val round = (fieldFile \ "round").text.toInt
    val deck = getDeckFromFile(deckFile)
    val player1 = getPlayerFromFile(player1File)
    val player2 = getPlayerFromFile(player2File)
    Field(size, round, deck, player1, player2)
  }

  def getDeckFromFile(file: Node): Deck = {
    val cards = (file \ "card").map { card =>
      val firstName = (card \ "firstName").text
      val lastName = (card \ "lastName").text
      val atk = (card \ "atk").text.toInt
      val defe = (card \ "defe").text.toInt
      val position = (card \ "position").text
      Card(CardName(firstName), CardLastName(lastName), atk, defe, position)
    }.toList
    Deck(cards)
  }

  def getPlayerFromFile(file: Node): Player = {
    val name = (file \ "name").text
    val hand = getHandFromFile(file)
    val fightField = getFightFieldFromFile(file)
    val lp = (file \ "lp").text.toInt
    Player(name, hand, fightField, lp)
  }

  def getHandFromFile(file: Node): Hand = {
    val cards = (file \ "card").map { card =>
      val firstName = (card \ "firstName").text
      val lastName = (card \ "lastName").text
      val atk = (card \ "atk").text.toInt
      val defe = (card \ "defe").text.toInt
      val position = (card \ "position").text
      Card(CardName(firstName), CardLastName(lastName), atk, defe, position)
    }.toList
    Hand(cards)
  }

  def getFightFieldFromFile(file: Node): FightField = {
    val cards = (file \ "card").map { card =>
      val firstName = (card \ "firstName").text.toString
      val lastName = (card \ "lastName").text.toString
      val atk = (card \ "atk").text.toInt
      val defe = (card \ "defe").text.toInt
      val position = (card \ "position").text
      Card(CardName(firstName), CardLastName(lastName), atk, defe, position)
    }.toList
    FightField(cards)
  }

  def xmlToCard(xml: Node): Card = {
    val firstName = (xml \ "firstName").text
    val lastName = (xml \ "lastName").text
    val atk = (xml \ "atk").text.toInt
    val defe = (xml \ "defe").text.toInt
    val position = (xml \ "position").text
    Card(CardName(firstName), CardLastName(lastName), atk, defe, position)
  }

  // to do
  // def xmlToPlayerStrategy(xml: Node): Unit /* PlayerInterface */ = {}
}
