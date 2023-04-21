package model

case class Card(name: String, atk: Int, dev: Int):
  
  override def toString:String = name