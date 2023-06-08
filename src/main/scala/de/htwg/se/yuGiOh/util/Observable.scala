package de.htwg.se.yuGiOh
package util

trait Observer:
  def update(e: Event): Unit

trait Observable:
  var subscribers: Vector[Observer] = Vector()
  def add(s: Observer) = subscribers = subscribers :+ s
  def remove(s: Observer) = subscribers = subscribers.filterNot(o => o == s)
  def notifyObservers = subscribers.foreach(o => o.update)

enum Event:
  case Attack
  case Draw
  case GameOver
  case Quit
  case Skip
  case PlayCard