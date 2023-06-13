package de.htwg.se.yuGiOh
package model

case class Player(name: String, hand: Hand, fightField: FightField, lp: Int = 1000):
  override def toString: String = name

  def getHand: Hand = hand

  def getFightField: FightField = fightField
  def getLp: Int = lp
  // def changeName(playerName: String) = playerName
