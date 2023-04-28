package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class PlayerTest extends AnyWordSpec {
  "A Player" when {
    "new" should {
      val hand = Hand(List.fill(5)(Card.emptyCard))
      val fightField = FightField(List.fill(5)(Card.emptyCard))
      val player = Player("Your Name", hand, fightField)
      "have a name" in {
        player.name should be("Your Name")
      }
      "have a hand" in {
        player.getHand should be (hand)
      }
      "have a fightField" in {
        player.getHand should be(fightField)
      }
      "have a nice String representation" in {
        player.toString should be("Your Name")
      }
    }
  }
}
