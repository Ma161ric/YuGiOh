package de.htwg.se.yuGiOh.aview.gui

import de.htwg.se.yuGiOh.controller.controllerComponent.ControllerInterface
import de.htwg.se.yuGiOh.controller.controllerComponent.controllerBaseImpl._
import de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl._
import de.htwg.se.yuGiOh.model.fieldComponent.{
  FieldInterface,
  PlayerInterface,
  CardInterface
}
import de.htwg.se.yuGiOh.util.{Event, Observer}

import javax.swing.ImageIcon
import javax.swing.BorderFactory
import javax.swing.border.{CompoundBorder, EmptyBorder, LineBorder}
import scala.swing.Dialog._
import scala.swing.Dialog
import scala.swing._
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

class Gui(controller: ControllerInterface) extends Frame with Observer {
  controller.add(this)

  title = "Yu-Gi-Oh"
  preferredSize = new Dimension(770, 561)
  minimumSize = new Dimension(770, 561)
  maximumSize = new Dimension(770, 561)
  resizable = false

  private val actionsBarSize = new Dimension(770, 45)
  private val buttonSize = new Dimension(70, 30)
  private val cardButtonSize = new Dimension(92,22)
  private val cardSize = new Dimension(92, 122)
  private val labelSize = new Dimension(130,25)
  private val deckAndRoundSize = new Dimension(130, 52)
  private val emptySideBarSize = new Dimension(25, 405)
  private val emptyPanelSize = new Dimension(130, 124)
  private val fieldSize = new Dimension(600, 135)
  private val sideBarSize = new Dimension(132, 405)
  private val statsSize = new Dimension(770, 20)

  private val nameLabel = new Label("Welcome to Yu-Gi-Oh!")

  private var highlightHandCardsEnabled: Boolean = false
  private var highlightFightCardsEnabled: Boolean = false
  private var highlightAttackableCardsEnabled: Boolean = false

  private val menuImage = ImageIcon("src/main/resources/Logo.png")
  private val cardImage: ImageIcon = new ImageIcon(
    "src/main/resources/Card.png"
  )
  private val pyramidIcon = ImageIcon("src/main/resources/Pyramid.png")
  private val backgroundImage = ImageIcon("src/main/resources/SandstoneBackground3.png")

  private val backgroundBrown = new Color(166, 140, 106)
  private val barBrown = new Color(147, 123, 97)
  private val borderColor = new Color(72, 47, 24)
  private val cardBrown = new Color(175, 144, 106)
  private val darkBrown = new Color(105, 82, 59)
  private val darkerBrown = new Color(73, 58, 42)
  private val highlightColor = new Color(215, 199, 178)
  private val lightBrown = new Color(196, 171, 137)
  private val mediumBrown = new Color(187, 161, 124)

  private val brownBorder = Swing.LineBorder(borderColor)
  private val highlightBorder = Swing.LineBorder(highlightColor)

  menuBar = new MenuBar {
    background = barBrown
    opaque = true
    contents += new Menu("") {
      icon = menuImage
      borderPainted = false
      contents += new MenuItem(Action("Restart") {
        controller.restart()
        highlightHandCardsEnabled = false
      })
      contents += new MenuItem(Action("New Game") {
        controller.newGame()
        highlightHandCardsEnabled = false
      })
      contents += new Menu("Edit") {
        contents += new MenuItem(Action("Undo") {
          controller.undo
        })
        contents += new MenuItem(Action("Redo") {
          controller.redo
        })
      }
      contents += new MenuItem(Action("Save") {
        if (controller.save())
          Dialog.showMessage(
            null,
            "Game saved successfully!",
            title = "Save"
          )
        else
          Dialog.showMessage(
            null,
            "Game could not be saved!",
            title = "Error"
          )
      })
      contents += new MenuItem(Action("Load") {
        if (!controller.load())
          Dialog.showMessage(
            null,
            "Game could not be loaded!",
            title = "Error"
          )
      })
      contents += new MenuItem(Action("Help") {
        controller.printHelp()
      })
      contents += new MenuItem(Action("Quit") {
        controller.quit()
      })
    }
  }

  private def updateContent() =
    /* to do: startbildschirm
    new BorderPanel:
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
    case Event.Restart =>
      contents = updateContent()
      repaint()
    case Event.StartingGame =>
      val (player1: Option[String], player2: Option[String]) =
        getGraphicalInput()
      controller.newStartingGame(player1, player2)
    case Event.Next =>
      highlightHandCardsEnabled = false
      highlightFightCardsEnabled = false
      highlightAttackableCardsEnabled = false
      contents = updateContent()
      repaint()
    case Event.PlayCard =>
      contents = updateContent()
      repaint()
    case Event.GameOver =>
      println("Game over!")
    // to do: if (controller.player1Won) println("Spieler 1 hat das Spiel gewonnen!")
    // else if (controller.player2Won) println("Spieler 1 hat das Spiel gewonnen!")
    case Event.Quit => sys.exit

  private def playerLabel(player: PlayerInterface) = new Label {
    text = s"Player: ${player.toString}"
    background = lightBrown
    foreground = borderColor
    border = BorderFactory.createEmptyBorder(4, 5, 4, 0)
    preferredSize = labelSize
    minimumSize = labelSize
    maximumSize = labelSize
    horizontalAlignment = Alignment.Left
  }
  private def playerLpLabel(player: PlayerInterface) = new Label {
    text = s"LP: ${player.getLp}"
    border = new EmptyBorder(0, 5, 0, 0)
    background = lightBrown
    foreground = borderColor
    opaque = true
    preferredSize = labelSize
    minimumSize = labelSize
    maximumSize = labelSize
    horizontalAlignment = Alignment.Left
  }
  private val roundLabel = new Label {
    text = s"Round: ${controller.getField.getRound}"
    border = new EmptyBorder(4, 5, 4, 0)
    background = lightBrown
    foreground = borderColor
    opaque = true
    preferredSize = labelSize
    minimumSize = labelSize
    maximumSize = labelSize
    horizontalAlignment = Alignment.Left

  }
  private val deckLabel = new Label {
    text = s"Deck: ${controller.getField.getDeck.getDeckCount}"
    border = new EmptyBorder(0, 5, 0, 0)
    background = lightBrown
    foreground = borderColor
    opaque = true
    preferredSize = labelSize
    minimumSize = labelSize
    maximumSize = labelSize
    horizontalAlignment = Alignment.Left
  }

  private def sideBar(player: PlayerInterface, opponent: PlayerInterface): BoxPanel = new BoxPanel(Orientation.Vertical) {
    background = backgroundBrown
    opaque = true
    preferredSize = sideBarSize
    minimumSize = sideBarSize
    maximumSize = sideBarSize
    border = new EmptyBorder(0, 15, 0, 15)
    contents += new BoxPanel(Orientation.Vertical) {
      preferredSize = deckAndRoundSize
      minimumSize = deckAndRoundSize
      maximumSize = deckAndRoundSize
      background = lightBrown
      opaque = true
      border = brownBorder
      contents += playerLabel(opponent)
      contents += playerLpLabel(opponent)
    }
    contents += new BoxPanel(Orientation.Vertical) {
      background = backgroundBrown
      opaque = true
      preferredSize = emptyPanelSize
      minimumSize = emptyPanelSize
      maximumSize = emptyPanelSize
    }
    contents += new BoxPanel(Orientation.Vertical) {
      preferredSize = deckAndRoundSize
      minimumSize = deckAndRoundSize
      maximumSize = deckAndRoundSize
      border = brownBorder
      contents += roundLabel
      contents += deckLabel
    }
    contents += new BoxPanel(Orientation.Vertical) {
      background = backgroundBrown
      opaque = true
      preferredSize = emptyPanelSize
      minimumSize = emptyPanelSize
      maximumSize = emptyPanelSize
    }
    contents += new BoxPanel(Orientation.Vertical) {
      background = lightBrown
      opaque = true
      preferredSize = deckAndRoundSize
      minimumSize = deckAndRoundSize
      maximumSize = deckAndRoundSize
      border = brownBorder
      contents += playerLabel(player)
      contents += playerLpLabel(player)
    }
  }

  private val emptySideBar: BoxPanel = new BoxPanel(Orientation.Vertical) {
    background = backgroundBrown
    preferredSize = emptySideBarSize
    minimumSize = emptySideBarSize
    maximumSize = emptySideBarSize
  }

  private def cardPanel(card: CardInterface): BoxPanel = new BoxPanel(
    Orientation.Vertical
  ) {
    opaque = true
    preferredSize = cardSize
    minimumSize = cardSize
    maximumSize = cardSize
    border = new CompoundBorder(brownBorder, Swing.EmptyBorder(5, 5, 5, 5))
    if (card.isEmpty(card)) {
      val backgroundLabel: Label = new Label() {
        icon = cardImage
        opaque = true
      }
      background = darkerBrown
      contents += backgroundLabel
    } else {
      background = cardBrown
      contents += new BoxPanel(Orientation.Vertical) {
        background = mediumBrown
        border = new CompoundBorder(brownBorder, Swing.EmptyBorder(0, 3, 0, 0))
        opaque = true
        preferredSize = new Dimension(80, 35)
        maximumSize = new Dimension(80, 35)
        minimumSize = new Dimension(80, 35)
        contents += new Label(card.getFirstName) {foreground = borderColor}
        contents += new Label(card.getLastName) {foreground = borderColor}
      }
      contents += new Label("ATK: " + card.atkToString) {
        border = Swing.EmptyBorder(6, 3, 0, 0)
        foreground = borderColor
      }
      contents += new Label("DEF: " + card.defeToString) {
        border = Swing.EmptyBorder(0, 3, 0, 0)
        foreground = borderColor
      }
    }
  }

  private def playerHandCards(player: PlayerInterface): FlowPanel =
    new FlowPanel() {
      background = darkBrown
      preferredSize = fieldSize
      minimumSize = fieldSize
      maximumSize = fieldSize
      opaque = true

      val cardList: List[Card] = player.getHand.getCards

      cardList.zipWithIndex.foreach({ case (card, index) =>
        if (highlightHandCardsEnabled && !card.isEmpty(card)) {
          val highlightedCardPanel = cardPanel(card)
          val playButton: Button = new Button("Play") {
            minimumSize = cardButtonSize
            maximumSize = cardButtonSize
            preferredSize = cardButtonSize
            border = new CompoundBorder(highlightBorder, brownBorder)
          }
          playButton.action = Action("Play") {
            playButton.reactions += {
              case ButtonClicked(_) =>
                highlightHandCardsEnabled = false
                controller.playCard(index)
            }
          }
          val buttonWrapper: BoxPanel = new BoxPanel(Orientation.Vertical) {
            preferredSize = cardButtonSize
            background = cardBrown
            border = Swing.EmptyBorder(15, 0, 0, 0)

            contents += playButton
          }
          highlightedCardPanel.contents += buttonWrapper
          highlightedCardPanel.border = new CompoundBorder(
            highlightBorder,
            Swing.EmptyBorder(5, 5, 0, 5)
          )
          contents += highlightedCardPanel
        } else {
          contents += cardPanel(card)
        }
      })
    }

  private def playerHandField(player: PlayerInterface): BoxPanel = new BoxPanel(
    Orientation.Horizontal
  ) {
    border = brownBorder
    preferredSize = fieldSize
    minimumSize = fieldSize
    maximumSize = fieldSize

    contents += playerHandCards(player)
  }

  var chosenCardAtk: Int = 0
  var chosenCardIndex: Int = 0
  private def playerFightCards(player: PlayerInterface, isOpponentField: Boolean): FlowPanel =
    new FlowPanel() {
      background = darkBrown
      preferredSize = fieldSize
      minimumSize = fieldSize
      maximumSize = fieldSize
      opaque = true
      val cardList: List[Card] = player.getFightField.getCards
      if (!isOpponentField) {
        cardList.zipWithIndex.foreach({ case (card, index) =>
          if (highlightFightCardsEnabled && !card.isEmpty(card)) {
            val highlightedCardPanel = cardPanel(card)
            val playButton: Button = new Button("Attack") {
              minimumSize = cardButtonSize
              maximumSize = cardButtonSize
              preferredSize = cardButtonSize
              border = new CompoundBorder(highlightBorder, brownBorder)
            }
            playButton.action = Action("Attack") {
              playButton.reactions += {
                case ButtonClicked(_) =>
                  highlightFightCardsEnabled = false
                  highlightAttackableCardsEnabled = true
                  chosenCardIndex = index
                  chosenCardAtk = cardList(index).getAtk
                  update(Event.Attack)

              }
            }
            val buttonWrapper: BoxPanel = new BoxPanel(Orientation.Vertical) {
              preferredSize = cardButtonSize
              background = cardBrown
              border = Swing.EmptyBorder(15, 0, 0, 0)

              contents += playButton
            }

            highlightedCardPanel.contents += buttonWrapper
            highlightedCardPanel.border = new CompoundBorder(
              highlightBorder,
              Swing.EmptyBorder(5, 5, 0, 5)
            )
            contents += highlightedCardPanel
          } else {
            contents += cardPanel(card)
          }
        })
      } else {
        cardList.zipWithIndex.foreach({ case (card, index) =>
          if (highlightAttackableCardsEnabled && !card.isEmpty(card) && card.getDefe <= chosenCardAtk) {
            val highlightedCardPanel = cardPanel(card)
            val playButton: Button = new Button("Victim") {
              minimumSize = cardButtonSize
              maximumSize = cardButtonSize
              preferredSize = cardButtonSize
              border = new CompoundBorder(highlightBorder, brownBorder)
            }
            playButton.action = Action("Victim") {
              playButton.reactions += {
                case ButtonClicked(_) =>
                  highlightAttackableCardsEnabled = false
                  controller.attack(chosenCardIndex, index)
              }
            }
            val buttonWrapper: BoxPanel = new BoxPanel(Orientation.Vertical) {
              preferredSize = cardButtonSize
              background = cardBrown
              border = Swing.EmptyBorder(15, 0, 0, 0)

              contents += playButton
            }

            highlightedCardPanel.contents += buttonWrapper
            highlightedCardPanel.border = new CompoundBorder(
              highlightBorder,
              Swing.EmptyBorder(5, 5, 0, 5)
            )
            contents += highlightedCardPanel
          } else {
            contents += cardPanel(card)
          }
        })
      }
    }


  private def playerFightField(player: PlayerInterface, isOpponentField: Boolean): BoxPanel =
    new BoxPanel(Orientation.Horizontal) {
      border = brownBorder
      preferredSize = fieldSize
      minimumSize = fieldSize
      maximumSize = fieldSize

      contents += playerFightCards(player, isOpponentField)
    }

  private def playerStats(player: PlayerInterface): BoxPanel = new BoxPanel(
    Orientation.Horizontal
  ) {
    background = backgroundBrown
    opaque = true
    preferredSize = statsSize
    minimumSize = statsSize
    maximumSize = statsSize
  }

  private def playField: BorderPanel = new BorderPanel {
    val currentRound: Int = controller.getField.getRound
    val player1: PlayerInterface = controller.getField.getPlayer1
    val player2: PlayerInterface = controller.getField.getPlayer2
    opaque = true
    background = backgroundBrown
    //preferredSize = new Dimension(backgroundImage.getWidth, backgroundImage.getHeight)
    border = brownBorder

    if (currentRound % 2 == 0) {
      layout(playerStats(player1)) = BorderPanel.Position.North
      layout(new BorderPanel {
        layout(sideBar(player2, player1)) = BorderPanel.Position.West
        layout(new BoxPanel(Orientation.Vertical) {
          contents += playerFightField(player1, true)
          contents += playerFightField(player2, false)
          contents += playerHandField(player2)
        }) = BorderPanel.Position.Center
        layout(emptySideBar) = BorderPanel.Position.East
      }) = BorderPanel.Position.Center
      layout(playerStats(player2)) = BorderPanel.Position.South
    } else {
      layout(playerStats(player2)) = BorderPanel.Position.North
      layout(new BorderPanel {
        layout(sideBar(player1, player2)) = BorderPanel.Position.West
        layout(new BoxPanel(Orientation.Vertical) {
          contents += playerFightField(player2, true)
          contents += playerFightField(player1, false)
          contents += playerHandField(player1)
        }) = BorderPanel.Position.Center
        layout(emptySideBar) = BorderPanel.Position.East
      }) = BorderPanel.Position.Center
      layout(playerStats(player1)) = BorderPanel.Position.South
    }
  }

  private def actionsBar: GridPanel =
    new GridPanel(1, 4):
      background = barBrown
      opaque = true
      preferredSize = actionsBarSize
      contents += Button("Attack") {
        highlightFightCardsEnabled = true
        update(Event.Attack)
      }
      contents += Button("Play Card") {
        highlightHandCardsEnabled = true
        update(Event.PlayCard)
      }
      contents += Button("Draw") {
        controller.drawCard()
      }
      contents += Button("Next") {
        newRound()
      }
      border = Swing.EmptyBorder(10, 10, 10, 10)

  private def newRound(): Unit =
    val newRound: Int = controller.getField.getRound + 1
    highlightFightCardsEnabled = false
    highlightAttackableCardsEnabled = false
    highlightHandCardsEnabled = false
    controller.newRound(newRound)
    roundLabel.text = s"Round: ${controller.getField.getRound}"

  private def init(): Unit = {
    val (player1: Option[String], player2: Option[String]) = getGraphicalInput()
    controller.newStartingGame(player1, player2)
  }
  private def getGraphicalInput(): (Option[String], Option[String]) = {
    val playerName1 = showInput(
      null,
      "Enter Name Player 1",
      "Input Dialog",
      Dialog.Message.Question,
      pyramidIcon,
      Seq.empty[String],
      "Default"
    )
    val playerName2 = showInput(
      null,
      "Enter Name Player 2",
      "Input Dialog",
      Dialog.Message.Question,
      pyramidIcon,
      Seq.empty[String],
      "Default"
    )
    (
      Option(playerName1.getOrElse("Default")),
      Option(playerName2.getOrElse("Default"))
    )
  }

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
    //  to do: style menubar
  }

  /* to do: Startbildschirm
  private val startButton = new Button("Start YuGiOh") {
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
  // setMenuLookAndFeel()  to do: Startbildschirm
  contents = updateContent()

  listenTo()
  /* to do: Startbildschirm
  reactions += {
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

  init()
}
