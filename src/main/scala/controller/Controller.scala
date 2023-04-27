package controller

import model.{Field, FightField, Hand}
import util.Observable

case class Controller(var field: Field) extends Observable {
  override def toString = field.toString;

  def setNamePlayer1(name: String) =
    field.playerName(10, name)
    notifyObservers

  def setNamePlayer2(name: String) =
    field.playerName(10, name)
    notifyObservers

  def setLpPlayer1(lp: String) =
    field.playerLp(10, lp)
    notifyObservers

  def setLpPlayer2(lp: String) =
    field.playerLp(10, lp)
    notifyObservers

  def setHandPlayer(hand: Hand) =
    field.playerHandRow(10, hand.getSize, hand)
    //hand.playerHandRow(10, hand.getSize, hand.getCards)
    notifyObservers

  def setFightFieldPlayer1(fightField: FightField) =
    fightField.playerRow(10, fightField.getSize, fightField.getCards, 40)
    notifyObservers

  def setFightFieldPlayer2(fightField: FightField) =
    fightField.playerRow(10, fightField.getSize, fightField.getCards, 40)
    notifyObservers
}