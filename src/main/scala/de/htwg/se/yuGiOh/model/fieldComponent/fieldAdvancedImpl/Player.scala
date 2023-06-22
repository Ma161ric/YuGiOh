package de.htwg.se.yuGiOh.model.fieldComponent.fieldAdvancedImpl

import com.google.inject.Inject
import com.google.inject.name.Names
import com.google.inject.name.Named

import de.htwg.se.yuGiOh.model.fieldComponent.PlayerInterface
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.{Hand, FightField, Player as BaseField}

class Player @Inject() (@Named("DefaultPlayer") name: String, hand: Hand, fightField: FightField, lp: Int) extends BaseField(name, hand, fightField, lp) {
  //to do: baseField(name, hand, fightfield, lp) // to do: this file isnt used
}
