package model

case class Player(name: String, turn: Boolean, hand: Hand, fightField: FightField):
  override def toString:String = name
  def getTurn = turn
  
  def getHand = hand
  
  def getFightField = fightField
  def getLp = "1000"
  def turnTrue(): Boolean = return true
  //def getHand = hand
  //def changeName(playerName: String) = playerName
