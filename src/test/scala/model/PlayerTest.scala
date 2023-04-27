package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class PlayerTest extends AnyWordSpec {
  "A Player" when {
    "new" should {
      val player = Player(
        "Your Name",
        false,
        Hand(List.fill(5)(Card.emptyCard)),
        FightField(List.fill(5)(Card.emptyCard))
      )
      "have a name" in {
        player.name should be("Your Name")
      }
      "have a nice String representation" in {
        player.toString should be("Your Name")
      }
    }
  }
}
