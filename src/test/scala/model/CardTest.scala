package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class CardTest extends AnyWordSpec{
  "A Card" when {
    "new" should {
      val atk = 300
      val defe = 400
      val card = Card.emptyCard
      "have a name" in {
        card.getFirstName should be("No")
        card.getAtk should be(0)
        card.getDefe should be(0)
      }
      "have a nice String representation" in {
        card.toString should be("Card Name")
      }
    }
  }
}
