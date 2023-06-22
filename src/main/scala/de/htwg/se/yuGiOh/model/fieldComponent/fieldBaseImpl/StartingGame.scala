package de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

import scala.collection.mutable.ListBuffer
import scala.util.Random
import net.codingwell.scalaguice.InjectorExtensions._
import net.codingwell.scalaguice.InjectorExtensions.ScalaInjector
import com.google.inject.{Inject, Guice}
import com.google.inject.name.Names

import de.htwg.se.yuGiOh.Module
import de.htwg.se.yuGiOh.model.fieldComponent.StartingGameInterface

object StartingGame extends StartingGameInterface {

  @Inject
  val injector = Guice.createInjector(new Module)
  var defaultPlayer1 = injector.instance[Player](Names.named("DefaultPlayer")) // to do: needs to be defaultplayer1
  var defaultPlayer2 = injector.instance[Player](Names.named("DefaultPlayer2"))


  def prepare(player1Name: String = " ", player2Name: String = " "): Field =
    val emptyCard: Card = Card(CardName.emptyName, CardLastName.emptyLastName, 0, 0, "")
    val emptyFightField = FightField(List.fill(6)(emptyCard))
    val deck: Deck = Deck(generateDeck())
    val (startingHandPlayer1, updatedDeck1) = deck.startingHand(6)
    val (startingHandPlayer2, updatedDeck2) = updatedDeck1.startingHand(6)

    val defPlayer1: Player = defaultPlayer1
    val defPlayer2: Player = defaultPlayer2
    val newPlayer1: Player = Player(player1Name, startingHandPlayer1, emptyFightField)
    val newPlayer2: Player = Player(player2Name, startingHandPlayer2, emptyFightField)

    if (player1Name == " " && player2Name == " ") {
      val field: Field = Field(6, 1, deck, defPlayer1, defPlayer2)

      field
    } else {
      val field: Field = Field(6, 1, deck, newPlayer1, newPlayer2)

      field
    }


  private def generateDeck(): List[Card] = {
    val cardNames = List(
      CardName.roter,
      CardName.schwarzer,
      CardName.blauer,
      CardName.weisser,
      CardName.boeser,
      CardName.guter,
    )
    val cardLastNames = List(
      CardLastName.Drache,
      CardLastName.Magier,
      CardLastName.Hexer,
      CardLastName.Gnom,
      CardLastName.Krieger,
      CardLastName.Reiter,
    )

    val deckBuffer = ListBuffer[Card]()
    for {
      firstName <- cardNames
      lastName <- cardLastNames
    } {
      val atk = (Random.nextInt(27) * 100 + 300) + Random.nextInt(2) * 100
      val defe = (Random.nextInt(27) * 100 + 300) + Random.nextInt(2) * 100
      deckBuffer += Card(firstName, lastName, atk, defe, "deck")
    }
    deckBuffer.toList
  }
}
