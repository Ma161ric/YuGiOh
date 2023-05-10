package main.scala.de.htwg.se.yuGiOh
package controller

import util.*
import model.*
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class ControllerTest extends AnyWordSpec {
  "A Controller" should {
    val player1 = Player(
      "Player1",
      Hand(List.fill(5)(Card.emptyCard)),
      FightField(List.fill(5)(Card.emptyCard))
    )
    val player2 = Player(
      "Player2",
      Hand(List.fill(5)(Card.emptyCard)),
      FightField(List.fill(5)(Card.emptyCard))
    )
    val field = Field(
      6,
      1,
      40,
      // Hand(List.fill(5)(Card.emptyCard)),
      // FightField(List.fill(5)(Card.emptyCard)),
      player1,
      player2
    )
    val controller = Controller(field)
    "have a to stringmethod" in {
      controller.toString should be(field.toString)
    }
    "notify its observers on change" in {
      class TestObserver(controller: Controller) extends Observer:
        controller.add(this)
        var bing = false
        def update = bing = true
      val testObserver = TestObserver(controller)
      testObserver.bing should be(false)
      controller.setHandPlayer(Hand(List.fill(5)(Card.emptyCard)))
      testObserver.bing should be(true)
    }
  }
}
