/* package main.scala.de.htwg.se.yuGiOh
package model

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class MoveSpec extends AnyWordSpec {
  "A Move" should {
    "have a name and Card." in {
      val move = Move()
      move.name should be("")
      move.Card should be(Card.emptyCard)
    }
    "filled Move" in {
      val move = Move("Luis", Card.blauerDrache)
      move.name should be("Luis")
      move.Card should be(Card.blauerDrache)
    }
  }
}
 */
