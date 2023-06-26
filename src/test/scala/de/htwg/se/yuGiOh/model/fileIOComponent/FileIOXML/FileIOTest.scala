package test.scala.de.htwg.se.yuGiOh.model.fileIOComponent.FileIOXML

import main.scala.de.htwg.se.yuGiOh.model.fileIOComponent.FileIOXML.FileIO
import main.scala.de.htwg.se.yuGiOh.model.fieldComponent._
import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class FileIOTest extends AnyWordSpec {
  "A FileIO" should {
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

    "save and load a field" in {
      fileIO.save(field) should be(true)

      val loadedField = fileIO.load
    }

    "load a field from an existing XML file" in {
      val loadedField = fileIO.load
      loadedField should not be null
    }

  }
}
