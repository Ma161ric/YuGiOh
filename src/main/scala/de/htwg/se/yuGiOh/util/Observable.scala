package main.scala.de.htwg.se.yuGiOh.util

trait Observer:
  def update(e: Event): Unit

trait Observable:
  var subscribers: Vector[Observer] = Vector()
  def add(s: Observer): Unit = subscribers = subscribers :+ s
  def remove(s: Observer): Unit = subscribers =
    subscribers.filterNot(o => o == s)
  def notifyObservers(e: Event): Unit = subscribers.foreach(o => o.update(e))

enum Event:
  case Attack
  case ChangeCardPosition
  case Draw
  case GameOver
  case Move
  case NewGame
  case Next
  case PlayCard
  case Quit
  case StartingGame
  case Restart
