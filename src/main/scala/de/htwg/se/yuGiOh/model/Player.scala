package de.htwg.se.yuGiOh.model

case class Player(name: String, hand: Hand, fightField: FightField):
  override def toString: String = name

  def getHand: Hand = hand

  def getFightField: FightField = fightField
  def getLp = "1000"
  // def getHand = hand
  // def changeName(playerName: String) = playerName
