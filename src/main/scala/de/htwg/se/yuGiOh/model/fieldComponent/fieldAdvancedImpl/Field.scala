package main.scala.de.htwg.se.yuGiOh.model.fieldComponent.fieldAdvancedImpl

import com.google.inject.Inject
import com.google.inject.name.Named

import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.{
  Player,
  Deck,
  Field as BaseField
}
import main.scala.de.htwg.se.yuGiOh.model.fieldComponent.FieldInterface

class Field @Inject() (
    @Named("DefaultSize") size: Int,
    round: Int,
    deck: Deck,
    player1: Player,
    player2: Player
) extends BaseField(size, round, deck, player1, player2) {}
