package de.htwg.se.yuGiOh

import com.google.inject.name.Names
import com.google.inject.{AbstractModule, PrivateModule}
import net.codingwell.scalaguice.{ScalaModule, ScalaPrivateModule}
import de.htwg.se.yuGiOh.model.fieldComponent.{
  FieldInterface,
  PlayerInterface
}
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.{
  Field,
  Player
}
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl.StartingGame
import de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.yuGiOh.controller.controllerComponent.ControllerInterface
import de.htwg.se.yuGiOh.model.fileIOComponent._

class Module extends AbstractModule with ScalaModule {
  private val defaultName: String = "Default"

  override def configure(): Unit =
    val defaultStartingGame = StartingGame.prepare(defaultName, defaultName)
    val defaultPlayer1 = defaultStartingGame.getPlayer1
    val defaultPlayer2 = defaultStartingGame.getPlayer2

    bind[PlayerInterface]
      .annotatedWith(Names.named("DefaultPlayer1"))
      .toInstance(
        Player(
          defaultPlayer1.getName,
          defaultPlayer1.getHand,
          defaultPlayer1.getFightField,
          defaultPlayer1.getLp
        )
      )
    bind[PlayerInterface]
      .annotatedWith(Names.named("DefaultPlayer2"))
      .toInstance(
        Player(
          defaultPlayer2.getName,
          defaultPlayer2.getHand,
          defaultPlayer2.getFightField,
          defaultPlayer2.getLp
        )
      )

    bind[FieldInterface].toInstance(
      Field(
        defaultStartingGame.getSize,
        defaultStartingGame.getRound,
        defaultStartingGame.getDeck,
        defaultStartingGame.getPlayer1,
        defaultStartingGame.getPlayer2
      )
    )

    bind[ControllerInterface].to[Controller]

    bind[FileIOInterface].to[FileIOJSON.FileIO]
    // bind[FileIOInterface].to[FileIOXML.FileIO]
}
