package main.scala.de.htwg.se.yuGiOh
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class HandTest extends AnyWordSpec {
  "A Hand" when {
    "new" should {
      val list = List(Card.emptyCard)
      list.appendedAll(List(Card.emptyCard))
      list.appendedAll(List(Card.emptyCard))
      list.appendedAll(List(Card.emptyCard))
      list.appendedAll(List(Card.emptyCard))
      list.appendedAll(List(Card.emptyCard))
      list.appendedAll(List(Card.emptyCard))
      val cellNum = list.size
      println(cellNum)

      val hand = Hand(list)
      "have a list" in {
        hand.getCards should be(List(Card.emptyCard))
      }
      "have a getCard function" in {
        hand.getCard(1) should be(Card.emptyCard)
      }
      "have a get Size function" in {
        hand.getSize should be(1)
      }
      "have a emptyCell" in {
        hand.emptyCell(6) should be("|      ")
      }
      // "have a innerBar" in {
      //   hand.innerBar(4, 4) should be("|    +----+----+----+----+")
      // }
      // "have cardsFirstName" in {
      //   hand.cardsFirstName(6, 6, list) should be(
      //     hand.emptyCell(6) + "|No        |No        |No        |No        |"
      //   )
      // }
      // "have cardsLastName" in {
      //   hand.cardsLastName(6, 6, list) should be(
      //     hand.emptyCell(6) + "|Card      |Card      |Card      |Card      |"
      //   )
      // }
      // "have cardsAtk" in {
      //   hand.cardsAtk(6, 6, list) should be(
      //     "|          |atk: 0    |atk: 0    |atk: 0    |atk: 0    |"
      //   )
      // }
      // "have cardsDefe" in {
      //   hand.cardsDefe(6, 6, list) should be(
      //     "|          |def: 0    |def: 0    |def: 0    |def: 0    |"
      //   )
      // }
      // "have cardsPosition" in {
      //   hand.cardsPosition(6, 6, list) should be(
      //     "|          |pos: hand |pos: hand |pos: hand |pos: hand |"
      //   )
      // }
      // "have PlayerHandRow" in {
      //   hand.playerHandRow(6, hand.getSize, list) should be(
      //     "|      +----+----+----+----+----+" + sys.props("line.separator") +
      //       "|      |No        |No        |No        |No        |No        |" + sys
      //         .props("line.separator") +
      //       "|      |Card      |Card      |Card      |Card      |Card      |" + sys
      //         .props("line.separator") +
      //       "|      |atk: 0    |atk: 0    |atk: 0    |atk: 0    |atk: 0    |" + sys
      //         .props("line.separator") +
      //       "|      |def: 0    |def: 0    |def: 0    |def: 0    |def: 0    |" + sys
      //         .props("line.separator") +
      //       "|      |pos: hand |pos: hand |pos: hand |pos: hand |pos: hand |" + sys
      //         .props("line.separator")
      //   )
      // }
    }
  }
}
