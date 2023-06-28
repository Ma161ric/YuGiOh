package test.scala.de.htwg.se.yuGiOh.model.fileIOComponent.FileIOJSON

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

import de.htwg.se.yuGiOh.model.fileIOComponent.FileIOJSON.FileIO
import de.htwg.se.yuGiOh.model.fieldComponent._
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._
import de.htwg.se.yuGiOh.model.fileIOComponent.FileIOJSON.FileIO

class FileIOSpec extends AnyWordSpec {
  "A FileIO" when {
    val fileIO = new FileIO()
    val card1 = Card(CardName.Roter, CardLastName.Drache, 2000, 1500)
    val card2 = Card(CardName.Schwarzer, CardLastName.Magier, 1800, 2000)
    val card3 = Card(CardName.Blauer, CardLastName.Hexer, 1600, 1800)
    val card4 = Card(CardName.Weisser, CardLastName.Gnom, 1500, 1600)
    val hand1 =
      Hand(List(Card(CardName.Roter, CardLastName.Drache, 2000, 1500)))
    val fightfield1 = FightField(
      List(Card(CardName.Schwarzer, CardLastName.Magier, 1800, 2000))
    )
    val hand2 =
      Hand(List(Card(CardName.Blauer, CardLastName.Hexer, 1600, 1800)))
    val fightfield2 =
      FightField(List(Card(CardName.Weisser, CardLastName.Gnom, 1500, 1600)))
    val player1 = Player("name1", hand1, fightfield1, 1000)
    val player2 = Player("name2", hand2, fightfield2, 1000)
    val deck =
      Deck(List(Card(CardName.Boeser, CardLastName.Reiter, 1400, 1500)))
    val field = Field(5, 1, deck, player1, player2)
    "saving a field" should {

      "create the necessary directories" in {
        fileIO.save(field)
        val directoryExists = new java.io.File("JSON").exists()
        directoryExists should be(true)
      }

      "save the field as JSON" in {
        val json = fileIO.loadJsonFromFile("JSON/game.json")
        json should not be null
      }
      "convert the field to JSON" in {

        val json = fileIO.saveField(field)
        field should not be null
      }
    }

    "loading a field" should {
      val fileIO = new FileIO()

      "load a field from JSON" in {
        val field = fileIO.load
        field should not be null
        // Add assertions to verify the loaded field's state
      }

      "convert JSON to a field" in {
        val json = fileIO.loadJsonFromFile("JSON/game.json")
        val field = fileIO.getFieldFromJson(json)
        field should not be null
        // Add assertions to verify the loaded field's state
      }
    }
  }
}
