package model

case class Player(name: String):
//case class Player(name: String, turn: Boolean, lifePoints: Int, hand: Array[Card]):
 
  override def toString:String = name

