package de.htwg.se.yuGiOh.aview.gui


import de.htwg.se.yuGiOh.controller.Controller
import de.htwg.se.yuGiOh.util.{Event, Observer}

import javax.swing.ImageIcon
import javax.swing.BorderFactory
import java.awt.Color
import scala.swing.*
import scala.swing.event.*

class Gui(controller: Controller) extends Frame with Observer{
  controller.add(this)

  title = "Yu-Gi-Oh"
  preferredSize = new Dimension(900, 500)
  resizable = false


  menuBar = new MenuBar {
    contents += new Menu("Menu") {
      contents += new MenuItem(Action("New Game") {
        println(controller.field.toString)
      })
      contents += new Menu("Edit") {
        contents += new MenuItem(Action("Undo") {
          //controller.makeAndPublish(controller.undo)
        })
        contents += new MenuItem(Action("Redo") {
          //controller.makeAndPublish(controller.redo)
        })
      }
      contents += new MenuItem(Action("Help") {
        controller.printhelp()
      })
      contents += new MenuItem(Action("Quit") {
        controller.quit()
      })
    }
  }

  override def update(event: Event): Unit = event match
    case Event.Attack =>
      contents = updateContent()
    case Event.Draw =>
      contents = updateContent()
    case Event.Skip =>
      contents = updateContent()
    case Event.PlayCard =>
      contents = updateContent()
    case Event.GameOver =>
      println("Game over!")
    //if (controller.player1Won) println("Spieler 1 hat das Spiel gewonnen!")
    //else if (controller.player2Won) println("Spieler 1 hat das Spiel gewonnen!")
    case Event.Quit => sys.exit

  val nameLabel = new Label("Welcome to Yu-Gi-Oh!")
  val startButton = new Button("Start Game")
  val player1Label = new Label("Player 1: ")
  val player2Label = new Label("Player 2: ")
  val player1LpLabel = new Label("LP: ")
  val player2LpLabel = new Label("LP: ")
  val cardLabels: Array[Label] = Array.fill(6)(new Label("Test"))
  val roundLabel = new Label("Round: ")
  val deckLabel = new Label("Deck: ")
  val padding = 20
  val cardWidth = 100
  val cardHeight = 150

  /*contents = new BoxPanel(Orientation.Vertical) {
    contents += nameLabel
    contents += Swing.VStrut(10)
    contents += startButton
    border = Swing.EmptyBorder(10, 10, 10, 10)
  }

  listenTo(startButton)
  reactions += {
    case ButtonClicked(`startButton`) =>
      // Perform actions when the start button is clicked
      // Replace this with your own logic
      println("Game started!")
  }*/

  val handFields: GridPanel = new GridPanel(1, 6) {
    border = BorderFactory.createLineBorder(Color.black, 1)

    preferredSize = new Dimension(100, 100)
    val playFields: List[Label] = (1 to 6).map { fieldIndex =>
      new Label(s"Hand $fieldIndex")
    }.toList

    playFields.foreach(contents += _)
  }

  val player1FightFields: GridPanel = new GridPanel(1, 6) {
    border = BorderFactory.createLineBorder(Color.black, 1)

    preferredSize = new Dimension(100, 100)
    val playFields: List[Label] = (1 to 6).map { fieldIndex =>
      new Label(s"Card $fieldIndex")
    }.toList

    playFields.foreach(contents += _)
  }

  val player2FightFields: GridPanel = new GridPanel(1, 6) {
    border = BorderFactory.createLineBorder(Color.black, 1)

    preferredSize = new Dimension(100, 100)
    val playFields: List[Label] = (1 to 6).map { fieldIndex =>
      new Label(s"Card $fieldIndex")
    }.toList

    playFields.foreach(contents += _)
  }

  val player1Stats: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    border = BorderFactory.createLineBorder(Color.black, 1)
    preferredSize = new Dimension(700, 50)
    contents += player1Label
    contents += Swing.HGlue
    contents += player1LpLabel
  }

  val player2Stats: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    border = BorderFactory.createLineBorder(Color.black, 1)
    preferredSize = new Dimension(700, 50)
    contents += player2Label
    contents += Swing.HGlue
    contents += player2LpLabel
  }

  val playField: BoxPanel = new BoxPanel(Orientation.Vertical) {
    contents += player2Stats
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += new BoxPanel(Orientation.Vertical) {
        border = BorderFactory.createLineBorder(Color.black, 1)
        //background = Color(645, 645, 645)
        contents += roundLabel
        contents += deckLabel
      }
      contents += new BoxPanel(Orientation.Vertical) {
        contents += player2FightFields
        contents += player1FightFields
        contents += handFields
      }
    }
    contents += player1Stats
  }

  private def updateContent() =
    new BorderPanel:
      add(playField, BorderPanel.Position.North)
      add(actionsBar, BorderPanel.Position.South)

  private def actionsBar: GridPanel =
    new GridPanel(1, 4):
      contents += Button("Attack") {
        //controller.attack()
      }
      contents += Button("Play Card") {
        //controller.playCard()
      }
      contents += Button("Draw") {
        //controller.draw()
      }
      contents += Button("Skip") {
        //controller.skip()
      }
      border = Swing.EmptyBorder(10, 10, 10, 10)

  /*contents = new GridPanel(5, 1) {
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += player1Label
      contents += Swing.HGlue
      contents += player1LpLabel
    }
    contents += player1FieldLabel
    contents += player2FieldLabel
    contents += player2HandLabel
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += player2Label
      contents += Swing.HGlue
      contents += player2LpLabel
    }
  }*/

  contents = updateContent()

  listenTo()

  centerOnScreen
  pack
  open()

}



  /*preferredSize = new Dimension(800, 600)
  resizable = false

  val padding = 20
  val cardWidth = 100
  val cardHeight = 150

  val playerField = new Panel {
    override def paintComponent(g: Graphics2D): Unit = {
      super.paintComponent(g)
      g.drawLine(padding, size.height / 2, size.width - padding, size.height / 2)
      g.drawLine(padding, size.height / 2 - cardHeight, size.width - padding, size.height / 2 - cardHeight)
      g.drawLine(padding, size.height / 2 + cardHeight, size.width - padding, size.height / 2 + cardHeight)
    }
  }

  val opponentField = new Panel {
    override def paintComponent(g: Graphics2D): Unit = {
      super.paintComponent(g)
      g.drawLine(padding, padding, size.width - padding, padding)
      g.drawLine(padding, padding + cardHeight, size.width - padding, padding + cardHeight)
      g.drawLine(padding, padding + 2 * cardHeight, size.width - padding, padding + 2 * cardHeight)
    }
  }

  val centerField = new Panel {
    override def paintComponent(g: Graphics2D): Unit = {
      super.paintComponent(g)
      g.drawLine(size.width / 2, padding, size.width / 2, size.height - padding)
    }
  }
  contents = new BorderPanel {
    add(playerField, BorderPanel.Position.Center)
    add(opponentField, BorderPanel.Position.North)
    add(centerField, BorderPanel.Position.South)
  }
}


}*/

