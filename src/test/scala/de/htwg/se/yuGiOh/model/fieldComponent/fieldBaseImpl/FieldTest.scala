package test.scala.de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.*

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class FieldTest extends AnyWordSpec {

  "A Field" should {
    // Write tests for the methods of the Field class
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

    "return the current player" in {
      field.getCurrentPlayer() should be(player1)
    }

    "switch to the next player" in {
      field.nextPlayer()
      field.getCurrentPlayer() should be(player2)
    }

    "return the size of the field" in {
      field.getSize should be(5)
    }

    // Add more tests for the other methods

    "return a formatted string representation of the field" in {
      val expected =
        "+-----------------------------------------------------------------+" + sys
          .props("line.separator") +
          "| Player: name2                  LP: 1000                         |" + sys
            .props("line.separator") +
          "+----------+----------+----------+----------+----------+----------+" + sys
            .props("line.separator") +
          "|          |Weisser   |" + sys.props("line.separator") +
          "|          |Gnom      |" + sys.props("line.separator") +
          "|          |atk: 1500 |" + sys.props("line.separator") +
          "|          |def: 1600 |" + sys.props("line.separator") +
          "|          |pos:      |" + sys.props("line.separator") +
          "|Round: 1  +----------+----------+----------+----------+----------+" + sys
            .props("line.separator") +
          "|Deck: 1   +----------+----------+----------+----------+----------+" + sys
            .props("line.separator") +
          "|          |Schwarzer |" + sys.props("line.separator") +
          "|          |Magier    |" + sys.props("line.separator") +
          "|          |atk: 1800 |" + sys.props("line.separator") +
          "|          |def: 2000 |" + sys.props("line.separator") +
          "|          |pos:      |" + sys.props("line.separator") +
          "|          +----------+----------+----------+----------+----------+" + sys
            .props("line.separator") +
          "|          |Roter     |" + sys.props("line.separator") +
          "|          |Drache    |" + sys.props("line.separator") +
          "|          |atk: 2000 |" + sys.props("line.separator") +
          "|          |def: 1500 |" + sys.props("line.separator") +
          "|          |pos:      |" + sys.props("line.separator") +
          "+----------+----------+----------+----------+----------+----------+" + sys
            .props("line.separator") +
          "| Player: name1                  LP: 1000                         |" + sys
            .props("line.separator") +
          "+-----------------------------------------------------------------+" + sys
            .props("line.separator")

      field.toString should be(expected)
    }

    "serialize to XML" in {
      val xml =
        <game>
      <field>
        <size>5</size>
        <round>1</round>
      </field>
      <deck>{field.deck.toXml()}</deck>
      <players>
        {field.player1.toXml()}
        {field.player2.toXml()}
      </players>
    </game>
      field.toXml().toString should be(xml.toString)
    }
  }
}
