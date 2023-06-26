package main.scala.de.htwg.se.yuGiOh.model.fileIOComponent.FileIOJSON

import java.io.{File, PrintWriter}
import play.api.libs.json._

import com.google.inject.name.{Named, Names}
import com.google.inject.{AbstractModule, Guice, Inject, Injector, Provides}
import com.google.inject.Key
import scala.io.Source
import scala.util.{Using, Try}
import play.api.libs.json._
import net.codingwell.scalaguice.InjectorExtensions._

import main.scala.de.htwg.se.yuGiOh.model.fileIOComponent._
import main.scala.de.htwg.se.yuGiOh.model.fieldComponent._
import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._

class FileIO extends FileIOInterface {

  private def createDirectory(path: String): Unit = {
    val directory = new File(path)
    if (!directory.exists()) {
      directory.mkdir()
    }
  }

  override def save(field: FieldInterface): Boolean = {
    import java.io._
    createDirectory("JSON")
    saveField(field)
    true
  }

  def saveField(field: FieldInterface): Unit = {
    val deckJson = Json.obj("deck" -> field.getDeck.getDeck.map(cardToJson))
    val player1Json = Json.obj(
      "name" -> field.getPlayer1.getName,
      "hand" -> field.getPlayer1.getHand.getCards.map(cardToJson),
      "fightfield" -> field.getPlayer1.getFightField.getCards.map(cardToJson),
      "lp" -> field.getPlayer1.getLp
    )
    val player2Json = Json.obj(
      "name" -> field.getPlayer2.getName,
      "hand" -> field.getPlayer2.getHand.getCards.map(cardToJson),
      "fightfield" -> field.getPlayer2.getFightField.getCards.map(cardToJson),
      "lp" -> field.getPlayer2.getLp
    )
    val json = Json.obj(
      "size" -> field.getSize,
      "round" -> field.getRound,
      "deck" -> deckJson,
      "player1" -> player1Json,
      "player2" -> player2Json
    )
    saveJsonToFile(json, "JSON/game.json")
  }

  def saveJsonToFile(json: JsValue, fileName: String): Unit = {
    val file = new File(fileName)
    val writer = new PrintWriter(file)
    writer.write(Json.prettyPrint(json))
    writer.close()
  }

  def cardToJson(card: Card): JsObject = {
    Json.obj(
      "firstName" -> card.firstName.toString,
      "lastName" -> card.lastName.toString,
      "atk" -> card.atk,
      "defe" -> card.defe,
      "position" -> card.position
    )
  }

  override def load: FieldInterface = {
    val json = loadJsonFromFile("JSON/game.json")
    getFieldFromJson(json)
  }

  def loadJsonFromFile(fileName: String): JsValue = {
    val file = new File(fileName)
    val inputStream = new java.io.FileInputStream(file)
    val content = Json.parse(inputStream)
    content
  }

  def getFieldFromJson(json: JsValue): Field = {
    val size = (json \ "size").as[Int]
    val round = (json \ "round").as[Int]
    val deck = getDeckFromJson((json \ "deck").get)
    val player1 = getPlayerFromJson((json \ "player1").get)
    val player2 = getPlayerFromJson((json \ "player2").get)
    Field(size, round, deck, player1, player2)
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
    Card(CardName(firstName), CardLastName(lastName), atk, defe, position)
  }
}
