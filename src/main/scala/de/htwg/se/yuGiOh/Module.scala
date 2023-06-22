package de.htwg.se.yuGiOh

import com.google.inject.name.Names
import com.google.inject.{AbstractModule, PrivateModule}
import net.codingwell.scalaguice.{ScalaModule, ScalaPrivateModule}

import de.htwg.se.yuGiOh.model.fieldComponent.{FieldInterface,PlayerInterface}
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.{Field, Player}
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.StartingGame
import de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.yuGiOh.controller.controllerComponent.ControllerInterface


class Module extends AbstractModule with ScalaModule {
  private val defaultSize: Int = 6
  private val defaultRound: Int = 1

  private val defaultName: String = "Default"

  private val defaultName1: String = "DefaultPlayer"
  private val defaultName2: String = "DefaultPlayer"

  override def configure(): Unit =
    val defaultStartingGame = StartingGame.prepare(defaultName1,defaultName1)
    val defaultPlayer1 = defaultStartingGame.getPlayer1
    val defaultPlayer2 = defaultStartingGame.getPlayer2
    //bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize, defaultRound)
    //bindConstant().annotatedWith(Names.named("DefaultName")).to(defaultName)

    //bind[FieldInterface].to[Field]
    //bind[FieldInterface].toInstance(Field())

    //bind[PlayerInterface].annotatedWithName("Default").toInstance(Player("Default 2"))
    bind[PlayerInterface].annotatedWith(Names.named("DefaultPlayer1")).toInstance(Player(defaultPlayer1.getName, defaultPlayer1.getHand, defaultPlayer1.getFightField, defaultPlayer1.getLp))
    //bind[PlayerInterface].annotatedWith(Names.named("DefaultPlayer")).toInstance(Player(defaultPlayer2.getName, defaultPlayer2.getHand, defaultPlayer2.getFightField, defaultPlayer2.getLp))
    //bind[PlayerInterface].toInstance(Player(defaultPlayer2.getName, defaultPlayer2.getHand, defaultPlayer2.getFightField, defaultPlayer2.getLp))
    //bind[PlayerInterface].to[Player]

    //val defaultPlayer =

    bind[FieldInterface].toInstance(Field(defaultStartingGame.getSize, defaultStartingGame.getRound, defaultStartingGame.getDeck, defaultStartingGame.getPlayer1, defaultStartingGame.getPlayer2))

    bind[ControllerInterface].to[Controller]
}
