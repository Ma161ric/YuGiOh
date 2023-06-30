package de.htwg.se.yuGiOh

import de.htwg.se.yuGiOh.aview.Tui
import de.htwg.se.yuGiOh.aview.gui.Gui

import de.htwg.se.yuGiOh.controller.controllerComponent.ControllerInterface
import de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl._
import de.htwg.se.yuGiOh.model.fieldComponent.FieldInterface

import scala.io.StdIn.readLine
import scala.collection.mutable.ListBuffer
import scala.util.Random
import com.google.inject.{AbstractModule, Guice, Inject}
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._

@main def run: Unit =

  println("Welcome to YuGiOh!")

  val injector = Guice.createInjector(new Module)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val gui = Gui(controller)
  val tui = new Tui(controller)

  tui.run()
