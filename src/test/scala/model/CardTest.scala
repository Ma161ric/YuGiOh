package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class CardTest extends AnyWordSpec {
  "A Card" when {
    "new" should {
      val card = Card.emptyCard
      val cardNames = List(CardName.roter, CardName.schwarzer,
        CardName.blauer
      ,
      CardName
      .weisser
      ,
      CardName
      .boeser
      ,
      CardName
      .guter
      ,
      CardName
      .emptyName)
      val cardLastNames = List(
         CardLastName.Drache , CardLastName.Magier
      ,
       CardLastName
      .Hexer
      ,
       CardLastName
      .Gnom
      ,
       CardLastName
      .Krieger
      ,
       CardLastName
      .Reiter
      ,
       CardLastName
      .emptyLastName

      )
      "have a name" in {
        card.getFirstName should be("No")
        card.getLastName should be("Card")
        card.getAtk should be(0)
        card.getDefe should be(0)
        card.getPosition should be(" ")
      }
      "have a nice String representation" in {
        card.toString should be("NoCard")
      }
    }
  }
}
