package de.htwg.se.yuGiOh.model.fileIOComponent.FileIOXML

import java.io.{File, FileWriter, PrintWriter}

import scala.util.{Using, Try}
import scala.xml.{Elem, XML, Node, NodeSeq, PrettyPrinter}

import de.htwg.se.yuGiOh.model.fileIOComponent._
import de.htwg.se.yuGiOh.model.fieldComponent.{
  FieldInterface,
  PlayerInterface
}
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._

class FileIO extends FileIOInterface {

  private def createDirectory(path: String): Unit =
    val directory = new File(path)
    if (!directory.exists()) {
      directory.mkdir()
    }

  override def save(field: FieldInterface): Boolean =
    createDirectory("XML")
    val pw = new PrintWriter(new File("XML/game_data.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(fieldToXml(field))
    val res = Try(pw.write(xml))
    pw.close()
    res.isSuccess

  override def load: FieldInterface = {
    val xml = loadFromFile("XML/game_data.xml")
    xmlToField(xml)
  }

  private def loadFromFile(filePath: String): Elem = {
    scala.xml.XML.loadFile(filePath)
  }

  private def fieldToXml(field: FieldInterface): Elem = {
    <game>
      <field>
        <size>{field.getSize}</size>
        <round>{field.getRound}</round>
      </field>
      <deck>{deckToXml(field.getDeck)}</deck>
      <players>
        {playerToXml(field.getPlayer1)}
        {playerToXml(field.getPlayer2)}
      </players>
    </game>
  }

  private def deckToXml(deck: Deck): Node = {
    <deck>
        {
      deck.getDeck.map { card =>
        cardToXml(card)
      }
    }
        </deck>
  }

  private def playerToXml(player: PlayerInterface): Elem = {
    <player>
      <name>{player.getName}</name>
      <hand>{handToXml(player.getHand)}</hand>
      <fightfield>{fightFieldToXml(player.getFightField)}</fightfield>
      <lp>{player.getLp}</lp>
    </player>
  }

  private def handToXml(hand: Hand): Elem = {
    <cards>
      {hand.getCards.map(cardToXml)}
    </cards>
  }

  private def fightFieldToXml(fightField: FightField): Elem = {
    <cards>
      {fightField.getCards.map(cardToXml)}
    </cards>
  }

  private def cardToXml(card: Card): Elem = {
    <card>
      <firstName>{card.firstName}</firstName>
      <lastName>{card.lastName}</lastName>
      <atk>{card.atk}</atk>
      <defe>{card.defe}</defe>
      <position>{card.position}</position>
    </card>
  }

  private def xmlToField(xml: Elem): Field = {
    val size = (xml \ "field" \ "size").text.toInt
    val round = (xml \ "field" \ "round").text.toInt
    val deck = getDeckFromXml(xml \ "deck")
    val playerElements = xml \ "players" \ "player"
    val player1 = getPlayerFromXml(playerElements(0))
    val player2 = getPlayerFromXml(playerElements(1))
    Field(size, round, deck, player1, player2)
  }

  private def getDeckFromXml(deckXml: NodeSeq): Deck = {
    val cards = (deckXml \ "cards" \ "card").map { cardXml =>
      val firstName = (cardXml \ "firstName").text
      val lastName = (cardXml \ "lastName").text
      val atk = (cardXml \ "atk").text.toInt
      val defe = (cardXml \ "defe").text.toInt
      val position = (cardXml \ "position").text
      Card(CardName(firstName), CardLastName(lastName), atk, defe, position)
    }.toList
    Deck(cards)
  }

  private def getPlayerFromXml(playerXml: NodeSeq): Player = {
    val name = (playerXml \ "name").text
    val hand = getHandFromXml(playerXml \ "hand")
    val fightField = getFightFieldFromXml(playerXml \ "fightfield")
    val lp = (playerXml \ "lp").text.toInt
    Player(name, hand, fightField, lp)
  }

  private def getHandFromXml(handXml: NodeSeq): Hand = {
    val cards = (handXml \ "cards" \ "card").map { cardXml =>
      val firstName = (cardXml \ "firstName").text
      val lastName = (cardXml \ "lastName").text
      val atk = (cardXml \ "atk").text.toInt
      val defe = (cardXml \ "defe").text.toInt
      val position = (cardXml \ "position").text
      Card(CardName(firstName), CardLastName(lastName), atk, defe, position)
    }.toList
    Hand(cards)
  }

  private def getFightFieldFromXml(fightFieldXml: NodeSeq): FightField = {
    val cards = (fightFieldXml \ "cards" \ "card").map { cardXml =>
      val firstName = (cardXml \ "firstName").text
      val lastName = (cardXml \ "lastName").text
      val atk = (cardXml \ "atk").text.toInt
      val defe = (cardXml \ "defe").text.toInt
      val position = (cardXml \ "position").text
      Card(CardName(firstName), CardLastName(lastName), atk, defe, position)
    }.toList
    FightField(cards)
  }
}
