package de.htwg.se.yuGiOh.aview.gui


import de.htwg.se.yuGiOh.controller.controllerComponent.ControllerInterface
import de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl._
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._
import de.htwg.se.yuGiOh.model.fieldComponent.{FieldInterface, PlayerInterfaceOld}
import de.htwg.se.yuGiOh.util.{Event, Observer}

import javax.swing.ImageIcon
import javax.swing.BorderFactory
import javax.swing.border.{CompoundBorder, EmptyBorder, LineBorder}
import javax.swing.SwingUtilities
import javax.swing.UIManager
import java.awt.Color
import scala.swing.{BoxPanel, *}
import scala.swing.event.*
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.File
import scala.swing
import scala.swing.Panel

class Gui(controller: ControllerInterface) extends Frame with Observer{
  controller.add(this)

  title = "Yu-Gi-Oh"
  preferredSize = new Dimension(800, 638)
  resizable = false

  private val actionsBarSize = new Dimension(800, 45)
  private val buttonSize = new Dimension(70, 30)
  private val cardSize = new Dimension(92, 122)
  private val deckAndRoundSize = new Dimension(90, 49)
  private val fieldSize = new Dimension(600, 135)
  private val sideBarSize = new Dimension(100, 405)
  private val statsSize = new Dimension(800, 57)
  
  //private val nameLabel = new Label("Welcome to Yu-Gi-Oh!")

  private var highlightHandCardsEnabled: Boolean = false

  private val menuImage = ImageIcon("src/main/resources/Logo.png")
  private val cardImage: ImageIcon = new ImageIcon("src/main/resources/Card.png")

  private val barBrown = new Color(147, 123, 97)
  private val borderColor = new Color(66, 44, 23)
  private val darkBrown = new Color(93, 72, 50)
  private val darkerBrown = new Color(73, 58, 42)
  private val highlightColor = new Color(221, 209, 193)
  private val lightBrown = new Color(196, 171, 137)
  private val mediumBrown = new Color(177, 151, 116)

  private val brownBorder = Swing.LineBorder(borderColor)
  private val highlightBorder = Swing.LineBorder(highlightColor)

  //private val roundPlayer2: Boolean = round % 2 == 0

  menuBar = new MenuBar {
    background = barBrown
    opaque = true
    contents += new Menu("") {
      icon = menuImage
      borderPainted = false
      contents += new MenuItem(Action("New Game") {
        controller.newGame()
        highlightHandCardsEnabled = false
      })
      contents += new Menu("Edit") {
        contents += new MenuItem(Action("Undo") {
          controller.undo
          //val updatedField = controller.field
          //println(controller.getField)
          //updateContent()
        })
        contents += new MenuItem(Action("Redo") {
          controller.redo
        })
      }
      contents += new MenuItem(Action("Help") {
        controller.printHelp()
      })
      contents += new MenuItem(Action("Quit") {
        controller.quit()
      })
    }
  }

  private def updateContent() =
    /*new BorderPanel:
      add(mainPanel, BorderPanel.Position.Center)
      mainPanel.revalidate()
      repaint()*/
    new BorderPanel:
      add(playField, BorderPanel.Position.North)
      add(actionsBar, BorderPanel.Position.South)

  override def update(event: Event): Unit = event match
    case Event.Attack =>
      contents = updateContent()
      repaint()
    case Event.ChangeCardPosition =>
      contents = updateContent()
      repaint()
    case Event.Draw =>
      contents = updateContent()
      repaint()
    case Event.Move =>
      contents = updateContent()
      repaint()
    case Event.NewGame =>
      contents = updateContent()
      repaint()
    case Event.Next =>
      highlightHandCardsEnabled = false
      contents = updateContent()
      repaint()
    case Event.PlayCard =>
      contents = updateContent()
      repaint()
    case Event.GameOver =>
      println("Game over!")
    //if (controller.player1Won) println("Spieler 1 hat das Spiel gewonnen!")
    //else if (controller.player2Won) println("Spieler 1 hat das Spiel gewonnen!")
    case Event.Quit => sys.exit

  private def playerLabel(player: PlayerInterfaceOld) = new Label {
    text = s"Player: ${player.toString}"
    background = lightBrown
    border =  BorderFactory.createEmptyBorder(5, 10, 5, 10) // Empty border with 5 pixels padding
    opaque = true
  }
  private def playerLpLabel(player: PlayerInterfaceOld) = new Label {
    text = s"LP: ${player.getLp}"
    border = new EmptyBorder(5, 10, 5, 10)
    background = lightBrown
    opaque = true
  }
  private val roundLabel = new Label {
    text = s"Round: ${controller.getField.getRound}"
    border = new EmptyBorder(5, 2, 0, 34)
    background = lightBrown
    opaque = true
  }
  private val deckLabel = new Label {
    text = s"Deck: ${controller.getField.getDeck.getDeckCount}"
    border = new EmptyBorder(5, 2, 5, 38)
    background = lightBrown
    opaque = true
  }

  private def sideBar(): BoxPanel = new BoxPanel(Orientation.Vertical) {
    background = mediumBrown
    opaque = true
    preferredSize = sideBarSize
    minimumSize = sideBarSize
    maximumSize = sideBarSize
    border = new EmptyBorder(0, 5, 360, 0)
    contents += new BoxPanel(Orientation.Vertical) {
      preferredSize = deckAndRoundSize
      minimumSize = deckAndRoundSize
      maximumSize = deckAndRoundSize
      border = brownBorder
      contents += roundLabel
      contents += deckLabel
    }
  }

  private val emptySideBar: BoxPanel = new BoxPanel(Orientation.Vertical) {
    background = mediumBrown
    opaque = true
    preferredSize = sideBarSize
    minimumSize = sideBarSize
    maximumSize = sideBarSize
    border = new EmptyBorder(205, 8, 205, 0)
  }

  private def cardPanel(card: Card): BoxPanel = new BoxPanel(Orientation.Vertical) {
    opaque = true
    preferredSize = cardSize
    minimumSize = cardSize
    maximumSize = cardSize
    border = new CompoundBorder(
      brownBorder,
      Swing.EmptyBorder(5, 5, 5, 5)
    )
    if (card.isEmpty(card)) {
      val backgroundLabel: Label = new Label() {
        icon = cardImage
        opaque = true
      }
      background = darkerBrown
      contents += backgroundLabel
    } else {
      background = mediumBrown
      contents += new BoxPanel(Orientation.Vertical) {
        background = lightBrown
        border = new CompoundBorder(
          brownBorder,
          Swing.EmptyBorder(0, 3, 0, 0)
        )
        opaque = true
        preferredSize = new Dimension(80, 35)
        maximumSize = new Dimension(80, 35)
        minimumSize = new Dimension(80, 35)
        contents += new Label(card.getFirstName)
        contents += new Label(card.getLastName)
      }
      contents += new Label("ATK: " + card.atkToString) {
        border = Swing.EmptyBorder(6, 3, 0, 0)
      }
      contents += new Label("DEF: " + card.defeToString) {
        border = Swing.EmptyBorder(0, 3, 0, 0)
      }
    }
  }

  private def playerHandCards(player: PlayerInterfaceOld): FlowPanel = new FlowPanel() {
    background = darkBrown
    preferredSize = fieldSize
    minimumSize = fieldSize
    maximumSize = fieldSize
    opaque = true

    val cardList: List[Card] = player.getHand.getCards

    cardList.foreach({ card =>
      if (highlightHandCardsEnabled && !card.isEmpty(card)) {
        val highlightedCardPanel = cardPanel(card) //but with different border
        highlightedCardPanel.border = new CompoundBorder(
          highlightBorder,
          Swing.EmptyBorder(5, 5, 5, 5)
        )
        contents += highlightedCardPanel
      } else {
        contents += cardPanel(card)
      }
    })
  }

  private def playerHandField(player: PlayerInterfaceOld): BoxPanel = new BoxPanel(Orientation.Horizontal) {
      border = brownBorder
      preferredSize = fieldSize
      minimumSize = fieldSize
      maximumSize = fieldSize

      contents += playerHandCards(player)
    }

  private def playerFightCards(player: PlayerInterfaceOld): FlowPanel = new FlowPanel() {
    background = darkBrown
    preferredSize = fieldSize
    minimumSize = fieldSize
    maximumSize = fieldSize
    opaque = true
    val cardList: List[Card] = player.getFightField.getCards

    cardList.foreach({ card =>
      contents += cardPanel(card)
    })
  }

  private def playerFightField(player: PlayerInterfaceOld): BoxPanel = new BoxPanel(Orientation.Horizontal) {
    border = brownBorder
    preferredSize = fieldSize
    minimumSize = fieldSize
    maximumSize = fieldSize

    contents += playerFightCards(player)
  }

  private def playerStats(player: PlayerInterfaceOld): BoxPanel = new BoxPanel(Orientation.Horizontal) {
    background = mediumBrown
    opaque = true
    border = new EmptyBorder(0, 20, 0, 20)
    preferredSize = statsSize
    minimumSize = statsSize
    maximumSize = statsSize
    contents += new BoxPanel(Orientation.NoOrientation) {
      border = brownBorder
      contents += playerLabel(player)
    }
    contents += Swing.HGlue
    contents += new BoxPanel(Orientation.NoOrientation) {
      border = brownBorder
      contents += playerLpLabel(player)
    }
  }

  private def playField: BoxPanel = new BoxPanel(Orientation.Vertical) {
    val currentRound: Int = controller.getField.getRound
    val player1: PlayerInterfaceOld = controller.getField.getPlayer1
    val player2: PlayerInterfaceOld = controller.getField.getPlayer2

    border = brownBorder

    if (currentRound % 2 == 0) {
      contents += playerStats(player1)
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += sideBar()
        contents += new BoxPanel(Orientation.Vertical) {
          contents += playerFightField(player1)
          contents += playerFightField(player2)
          contents += playerHandField(player2)
        }
        contents += emptySideBar
      }
      contents += playerStats(player2)
    } else {
      contents += playerStats(player2)
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += sideBar()
        contents += new BoxPanel(Orientation.Vertical) {
          contents += playerFightField(player2)
          contents += playerFightField(player1)
          contents += playerHandField(player1)
        }
        contents += emptySideBar
      }
      contents += playerStats(player1)
    }
  }

  private def newRound(): Unit =
    val newRound: Int = controller.getField.getRound + 1
    controller.roundIncrement(newRound)
    roundLabel.text = s"Round: ${controller.getField.getRound}"
  //weird: if the upper line is gone nothing works anymore, seems suspicious
  //solved: because roundlabel is a val and not a def, to do: change that

  private def setButtonLookAndFeel(): Unit = {
    val lookAndFeel = UIManager.getLookAndFeelDefaults()
    lookAndFeel.put("Button.background", lightBrown)
    lookAndFeel.put("Button.foreground", borderColor)
    lookAndFeel.put("Button.border", brownBorder)
    lookAndFeel.put("Button.preferredSize", buttonSize)
    UIManager.put("Button.select", highlightColor)
  }

  def setMenuLookAndFeel(): Unit = {
    val lookAndFeel = UIManager.getLookAndFeelDefaults()
    lookAndFeel.put("MenuItem.background", barBrown)
    lookAndFeel.put("MenuItem.foreground", borderColor)
    //doesnt work, it needs to be customised menu working with 2dgraphics
  }

  private def actionsBar: GridPanel =
    new GridPanel(1, 4):
      background = barBrown
      opaque = true
      preferredSize = actionsBarSize
      contents += Button("Attack") {
        background = barBrown
        highlightHandCardsEnabled = true
        controller.attack(1,2)
        //to do: attack function machen
        //to do: attack parameter sind hardcoded noch
        //newRound()
      }
      contents += Button("Play Card") {
        highlightHandCardsEnabled = true
        //to do: player should be able to click on the cards he wants to play
        //choose card to play and pass chosen card as argument for playCard function
        //momentan nur default card playable als bsp um zu prüfung obs geht
        //val defaultCard: Card = Card(CardName.weisser, CardLastName.Drache, 2000, 3000)

        controller.playCard()

        deckLabel.text = s"Deck: ${controller.getField.getDeck.getDeckCount}"
        //newRound()
      }
      contents += Button("Draw") {
          controller.drawCard()
          deckLabel.text = s"Deck: ${controller.getField.getDeck.getDeckCount}"
      }
      contents += Button("Next") {
        newRound()
      }
      border = Swing.EmptyBorder(10, 10, 10, 10)

  /*private val startButton = new Button("Start YuGiOh") {
    background = mediumBrown
  }
  private val startPanel = new BoxPanel(Orientation.NoOrientation) {contents += startButton}
  private val mainPanel = new BoxPanel(Orientation.Vertical) { //maybe vertical?
    contents += startPanel
    contents += playField
    contents += actionsBar

    startPanel.visible = true
    playField.visible = false
    actionsBar.visible = false
    startPanel.visible = false
    playField.visible = true
    actionsBar.visible = true
  }
  startButton.reactions += {
    startPanel.visible = false
    playField.visible = true
    actionsBar.visible = true
    actionsBar.revalidate()
    actionsBar.repaint()
    playField.revalidate()
    playField.repaint()
  }*/

  setButtonLookAndFeel()
  //setMenuLookAndFeel() geht noch nicht lol
  contents = updateContent()

  listenTo()
  /*reactions += {
    case ButtonClicked("startButton") =>
      startPanel.visible = false
      playField.visible = true
      actionsBar.visible = true
      mainPanel.revalidate()
      mainPanel.repaint()
      println("Game started!")
  }
  */

  centerOnScreen
  pack
  open()
}