package de.htwg.se.yuGiOh.model.fileIOComponent.FileIOJSON

import java.io.{File, FileWriter, PrintWriter}
import play.api.libs.json._

import scala.io.Source
import scala.util.Using
import de.htwg.se.yuGiOh.model._

import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._

import com.google.inject.name.{Named, Names}
import com.google.inject.{AbstractModule, Guice, Inject, Injector, Provides}
import com.google.inject.Key

import de.htwg.se.yuGiOh.model.fileIOComponent._
import de.htwg.se.yuGiOh.model.fieldComponent._
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._
import de.htwg.se.yuGiOh.model.playerComponent._

class FileIO extends FileIOInterface {

  private def createDirectory(path: String): Unit = {
    val directory = new File(path)
    if (!directory.exists()) {
      directory.mkdir()
    }
  }

  override def save(
      field: FieldInterface,
      playStrategy: PlayerInterface
  ): Unit = {
    createDirectory("JSON")
    saveField(field)
    saveDeck(field.getDeck)
    savePlayer(field.getPlayer1, "player1.json")
    savePlayer(field.getPlayer2, "player2.json")
  }

  def saveField(field: FieldInterface): Unit = {
    val json = Json.obj(
      "size" -> field.getSize,
      "round" -> field.getRound
    )
    saveJsonToFile(json, "field.json")
  }

  def saveDeck(deck: Deck): Unit = {
    val json = Json.obj("deck" -> deck.getDeck.map(cardToJson))
    saveJsonToFile(json, "deck.json")
  }

  def savePlayer(player: PlayerInterface, fileName: String): Unit = {
    val json = Json.obj(
      "name" -> player.getName,
      "hand" -> player.getHand.getCards.map(cardToJson),
      "fightfield" -> player.getFightField.getCards.map(cardToJson),
      "lp" -> player.getLp
    )
    saveJsonToFile(json, fileName)
  }

  def saveJsonToFile(json: JsValue, fileName: String): Unit = {
    val file = new File(fileName)
    val writer = new PrintWriter(file)
    writer.write(Json.prettyPrint(json))
    writer.close()
  }

  def cardToJson(card: Card): JsObject = {
    Json.obj(
      "firstName" -> card.firstName,
      "lastName" -> card.lastName,
      "atk" -> card.atk,
      "defe" -> card.defe,
      "position" -> card.position,
      "isEmpty" -> card.isEmpty
    )
  }

  override def load: (FieldInterface /* , PlayerInterface */ ) = {
    val field = loadField()
    // val playerStrategy = loadPlayerStrategy()
    (field /* , playerStrategy */ )
  }

  def loadField(): Field = {
    val fieldJson = loadJsonFromFile("field.json")
    val deckJson = loadJsonFromFile("deck.json")
    val player1Json = loadJsonFromFile("player1.json")
    val player2Json = loadJsonFromFile("player2.json")

    val size = (fieldJson \ "size").as[Int]
    val round = (fieldJson \ "round").as[Int]
    val deck = getDeckFromJson(deckJson)
    val player1 = getPlayerFromJson(player1Json)
    val player2 = getPlayerFromJson(player2Json)

    Field(size, round, deck, player1, player2)
  }

  def loadJsonFromFile(fileName: String): JsValue = {
    val file = new File(fileName)
    val content = Json.parse(file)
    content
  }

  def getDeckFromJson(json: JsValue): Deck = {
    val cards = (json \ "deck").as[Seq[JsValue]].map(jsonToCard).toList
    Deck(cards)
  }

  def getPlayerFromJson(json: JsValue): Player = {
    val name = (json \ "name").as[String]
    val hand = getHandFromJson(json)
    val fightField = getFightFieldFromJson(json)
    val lp = (json \ "lp").as[Int]
    Player(name, hand, fightField, lp)
  }

  def getHandFromJson(json: JsValue): Hand = {
    val cards = (json \ "hand").as[Seq[JsValue]].map(jsonToCard).toList
    Hand(cards)
  }

  def getFightFieldFromJson(json: JsValue): FightField = {
    val cards = (json \ "fightfield").as[Seq[JsValue]].map(jsonToCard).toList
    FightField(cards)
  }

  def jsonToCard(json: JsValue): Card = {
    val firstName = (json \ "firstName").as[String]
    val lastName = (json \ "lastName").as[String]
    val atk = (json \ "atk").as[Int]
    val defe = (json \ "defe").as[Int]
    val position = (json \ "position").as[String]
    val isEmpty = (json \ "isEmpty").as[Boolean]
    Card(CardName(firstName), CardLastName(lastName), atk, defe, position)
  }
}
