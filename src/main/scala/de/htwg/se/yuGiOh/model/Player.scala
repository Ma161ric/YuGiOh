package de.htwg.se.yuGiOh
package model

case class Player(name: String, hand: Hand, fightField: FightField):
  override def toString: String = name

  def getHand = hand

  def getFightField = fightField
  def getLp = "1000"
  // def getHand = hand
  // def changeName(playerName: String) = playerName
