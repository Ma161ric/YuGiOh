package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class FightFieldTest extends AnyWordSpec {
  val fightField = FightField(List.fill(5)(Card.emptyCard))
  "A fightField" should {
    "have a getSize method" in {
      fightField.getSize should be(5)
    }
  }
}
