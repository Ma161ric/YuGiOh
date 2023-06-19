package de.htwg.se.yuGiOh.aview.gui


import de.htwg.se.yuGiOh.controller.Controller
import de.htwg.se.yuGiOh.model.{Card, CardLastName, CardName, Deck, FightField, Hand, Player}
import de.htwg.se.yuGiOh.util.{Event, Observer}

import javax.swing.ImageIcon
import javax.swing.BorderFactory
import javax.swing.border.EmptyBorder
import javax.swing.border.CompoundBorder
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




class Gui(controller: Controller) extends Frame with Observer{
  controller.add(this)

  title = "Yu-Gi-Oh"
  preferredSize = new Dimension(800, 640)
  resizable = false

  private val actionsBarSize = new Dimension(800, 45)
  private val buttonSize = new Dimension(70, 30)
  private val cardSize = new Dimension(92, 122)
  private val deckAndRoundSize = new Dimension(90, 49)
  private val fieldSize = new Dimension(600, 136)
  private val sideBarSize = new Dimension(100, 400)
  private val statsSize = new Dimension(600, 57)
  
  private val nameLabel = new Label("Welcome to Yu-Gi-Oh!")
  //val emptyCard: Card = Card.emptyCard
  private val deck: Deck = controller.field.deck
  private val player1: Player = controller.field.getPlayer1
  private val player2: Player = controller.field.getPlayer2
  private var player1Hand: Hand = player1.getHand
  private var player2Hand: Hand = player2.getHand
  private var player1FightField: FightField = player1.getFightField
  private var player2FightField: FightField = player2.getFightField
  private val cardLabels: List[Label] = List.fill(6)(new Label("Test"))
  //val padding = 20

  private val menuImage = ImageIcon("src/main/resources/Logo.png")
  private val cardImage: ImageIcon = new ImageIcon("src/main/resources/Card.png")

  private val barBrown = new Color(147, 123, 97)
  private val borderColor = new Color(66, 44, 23)
  private val darkBrown = new Color(93, 72, 50)
  private val darkerBrown = new Color(73, 58, 42)
  private val highlightColor = new Color(221, 209, 193)
  private val lightBrown = new Color(196, 171, 137)
  private val mediumBrown = new Color(177, 151, 116)

  //val lightBrown = new Color(153, 102, 51)
  //val mediumBrown = new Color(206, 161, 116)
  //val mediumBrown = new Color(178, 147, 91)
  //val mediumBrown = new Color(170, 130, 83)
  //val darkBrown = new Color(192, 64, 0)

  private val brownBorder = BorderFactory.createLineBorder(borderColor)
  private val highlightBorder = BorderFactory.createLineBorder(borderColor)

  //private val roundPlayer2: Boolean = round % 2 == 0

  menuBar = new MenuBar {
    background = barBrown
    opaque = true
    contents += new Menu("") {
      icon = menuImage
      borderPainted = false
      contents += new MenuItem(Action("New Game") {
        controller.newGame()
        updateContent()
      })
      contents += new Menu("Edit") {
        contents += new MenuItem(Action("Undo") {
          controller.undo
          val updatedField = controller.field
          println(controller.field)
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

  override def update(event: Event): Unit = event match
    case Event.Attack =>
      contents = updateContent()
      //revalidate()
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
      println(contents)
      repaint()
    case Event.Next =>
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

  //private val cardLayout = new CardLayout

  private def playerLabel(player: Player) = new Label {
    text = s"Player: ${player.toString}"
    background = lightBrown
    border =  BorderFactory.createEmptyBorder(5, 10, 5, 10) // Empty border with 5 pixels padding
    opaque = true
  }
  private def playerLpLabel(player: Player) = new Label {
    text = s"LP: ${player.getLp}"
    border = new EmptyBorder(5, 10, 5, 10)
    background = lightBrown
    opaque = true
  }
  private val roundLabel = new Label {
    text = s"Round: ${controller.field.getRound}"
    border = new EmptyBorder(5, 2, 0, 34)
    background = lightBrown
    opaque = true
  }
  private val deckLabel = new Label {
    text = s"Deck: ${controller.field.getDeck.getDeckCount}"
    border = new EmptyBorder(5, 2, 5, 38)
    background = lightBrown
    opaque = true
  }


  /*val card: BoxPanel = new BoxPanel(Orientation.Vertical) {
    border = BorderFactory. createLineBorder(borderColor, 1)
    preferredSize = new Dimension(50, 100)

    contents += new Label(s"First Name: ${emptyCard.getFirstName}")
    contents += new Label(s"Last Name ${emptyCard.getLastName}")
    contents += new Label(s"atk: ${emptyCard.getAtk}")
    contents += new Label(s"def: ${emptyCard.getDefe}")
  }*/

  private def playerHandCards(player: Player): FlowPanel = new FlowPanel() {
    background = darkBrown
    opaque = true
    player.getHand.getCards.foreach({ card =>
      val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
        border = new CompoundBorder(
          brownBorder,
          Swing.EmptyBorder(5, 5, 5, 5)
        )
        opaque = true
        preferredSize = cardSize
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
      //contents += Swing.HStrut(10)
      //contents += new Separator(Orientation.Vertical)
      contents += cardPanel
    })
  }



  private val player1HandCards: FlowPanel = new FlowPanel() {
    background = darkBrown
    //preferredSize = fieldSize
    //minimumSize = fieldSize
    //maximumSize = fieldSize
    opaque = true
    val player1Hand: Hand = controller.field.getPlayer1.getHand
    player1Hand.getCards.foreach({ card =>
      val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
        border = new CompoundBorder(
          brownBorder,
          Swing.EmptyBorder(5, 5, 5, 5)
        )
        opaque = true
        preferredSize = cardSize
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
            border = Swing.EmptyBorder(40, 3, 0, 0)
          }
          contents += new Label("DEF: " + card.defeToString) {
            border = Swing.EmptyBorder(0, 3, 0, 0)
          }
        }
      }
      //contents += Swing.HStrut(10)
      //contents += new Separator(Orientation.Vertical)
      contents += cardPanel
    })
  }

  val player2HandCards: FlowPanel = new FlowPanel() {
    background = darkBrown
    opaque = true
    preferredSize = fieldSize
    player2Hand.getCards.foreach({ card =>
      val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
        border = new CompoundBorder(
          brownBorder,
          Swing.EmptyBorder(5, 5, 5, 5)
        )
        opaque = true
        preferredSize = cardSize
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
            border = Swing.EmptyBorder(40, 3, 0, 0)
          }
          contents += new Label("DEF: " + card.defeToString) {
            border = Swing.EmptyBorder(0, 3, 0, 0)
          }
        }
      }
      //contents += Swing.HStrut(10)
      //contents += new Separator(Orientation.Vertical)
      contents += cardPanel
    })
  }

  val player1HandFields: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    border = brownBorder
    preferredSize = fieldSize
    val player1: Player = controller.field.getPlayer1
    contents += player1HandCards // or maybe .field.controller.getplayer1
  }

  val player2HandFields: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    border = brownBorder
    preferredSize = fieldSize

    contents += player2HandCards
  }

  def playerFightCards(player: Player): FlowPanel = new FlowPanel() {
    background = darkBrown
    opaque = true
    //preferredSize = new Dimension(300,100)
    player.getFightField.getCards.foreach({ card =>
      val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
        border = new CompoundBorder(
          brownBorder,
          Swing.EmptyBorder(5, 5, 5, 5)
        )
        opaque = true
        preferredSize = cardSize
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
            border = Swing.EmptyBorder(40, 3, 0, 0)
          }
          contents += new Label("DEF: " + card.defeToString) {
            border = Swing.EmptyBorder(0, 3, 0, 0)
          }
        }
      }
      //contents += Swing.HStrut(10)
      //contents += new Separator(Orientation.Vertical)
      contents += cardPanel
    })
  }

  private val player2FightCards: FlowPanel = new FlowPanel() {
    background = darkBrown
    opaque = true
    //preferredSize = new Dimension(300,100)
    player2FightField.getCards.foreach({ card =>
      val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
        border = new CompoundBorder(
          brownBorder,
          Swing.EmptyBorder(5, 5, 5, 5)
        )
        opaque = true
        preferredSize = cardSize
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
            border = Swing.EmptyBorder(40, 3, 0, 0)
          }
          contents += new Label("DEF: " + card.defeToString) {
            border = Swing.EmptyBorder(0, 3, 0, 0)
          }
        }
      }
      //contents += Swing.HStrut(1)
      //contents += new Separator(Orientation.Vertical)
      contents += cardPanel
    })
  }

  val player1FightCards: FlowPanel = new FlowPanel() {
    background = darkBrown
    opaque = true
    preferredSize = fieldSize
    player1FightField.getCards.foreach({ card =>
      val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
        border = new CompoundBorder(
          brownBorder,
          Swing.EmptyBorder(5, 5, 5, 5)
        )
        opaque = true
        preferredSize = cardSize
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
            border = Swing.EmptyBorder(40, 3, 0, 0)
          }
          contents += new Label("DEF: " + card.defeToString) {
            border = Swing.EmptyBorder(0, 3, 0, 0)
          }
        }
      }
      //contents += Swing.HStrut(10)
      //contents += new Separator(Orientation.Vertical)
      contents += cardPanel
    })
  }

  val player1FightFields: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    border = brownBorder
    preferredSize = fieldSize
    minimumSize = fieldSize
    maximumSize = fieldSize

    contents += player1FightCards
  }

  val player2FightFields: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    border = brownBorder
    preferredSize = fieldSize
    minimumSize = fieldSize
    maximumSize = fieldSize

    contents += player2FightCards
  }

  private def playerStats(player: Player): BoxPanel = new BoxPanel(Orientation.Horizontal) {
    background = mediumBrown
    opaque = true
    border = new EmptyBorder(0, 20, 0, 20)
    preferredSize = statsSize
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

  private val sideBar: BoxPanel = new BoxPanel(Orientation.Vertical) {
    background = mediumBrown
    opaque = true
    preferredSize = sideBarSize
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
    border = new EmptyBorder(205, 8, 205, 0)
  }

  private val playField: BoxPanel = new BoxPanel(Orientation.Vertical) {
    border = brownBorder
    contents += playerStats(player2)
    contents += new BoxPanel(Orientation.Horizontal) {
      contents += sideBar
      contents += new BoxPanel(Orientation.Vertical) {
        contents += player2FightFields
        contents += player1FightFields
        contents += player1HandFields
      }
      contents += emptySideBar
    }
    contents += playerStats(player1)
  }

  private def updateLayoutForRound(round: Int): Unit = {
    playField.contents.clear()
    if (round % 2 == 0) {
      playField.contents += playerStats(player1)
      playField.contents += new BoxPanel(Orientation.Horizontal) {
        contents += sideBar
        contents += new BoxPanel(Orientation.Vertical) {
          contents += player1FightFields
          contents += player2FightFields
          contents += player2HandFields
        }
        contents += emptySideBar
      }
      playField.contents += playerStats(player2)
    } else {
      playField.contents += playerStats(player2)
      playField.contents += new BoxPanel(Orientation.Horizontal) {
        contents += sideBar
        contents += new BoxPanel(Orientation.Vertical) {
          contents += player2FightFields
          contents += player1FightFields
          contents += player1HandFields
        }
        contents += emptySideBar
      }
      playField.contents += playerStats(player1)
    }

    //possibly also a general revalidate needed
    playField.revalidate()
    repaint()
  }


  private def updateContent() =
    /*new BorderPanel:
      add(mainPanel, BorderPanel.Position.Center)
      mainPanel.revalidate() //newest change
      repaint()*/
    new BorderPanel:
      add(playField, BorderPanel.Position.North)
      add(actionsBar, BorderPanel.Position.South)
      //revalidate() //newest change
      //repaint()

  private def highlightHandCards(playerHandCards: FlowPanel): Unit =
    //playerHandCards.contents.clear()
    playerHandCards.contents.foreach({
      case cardPanel: BoxPanel =>
        val firstName = cardPanel.contents.collectFirst { case label: Label => label.text }.getOrElse("")
        if (firstName.nonEmpty) {
          println("Non empty")
          cardPanel.border = BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(highlightColor,1),
            Swing.EmptyBorder(5, 5, 5, 5)
          )
        }
        println(cardPanel.border)
        cardPanel.revalidate()
        cardPanel.repaint()
    })
    //playerHandCards.revalidate()
    repaint()
  private def newRound(): Unit =
    val currentRound: Int  = controller.field.getRound
    controller.roundIncrement(currentRound)
    roundLabel.text = s"Round: ${currentRound}"
    updateLayoutForRound(currentRound)

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
    //doesnt work, there need to be customised menu working with 2dgraphics
  }

  private def actionsBar: GridPanel =
    new GridPanel(1, 4):
      background = barBrown
      opaque = true
      preferredSize = actionsBarSize
      contents += Button("Attack") {
        background = barBrown
        /*SwingUtilities.invokeLater(new Runnable() {
          def run(): Unit = {
            val iterator = playerHandCards(player1).contents.iterator
            while (iterator.hasNext) {
              iterator.next() match {
                case panel: BoxPanel =>
                  val firstName = panel.contents.collectFirst { case label: Label => label.text }.getOrElse("")
                  if (firstName != "No") {
                    panel.background = java.awt.Color.YELLOW
                    panel.opaque = true
                    panel.revalidate() // Revalidate the card panel
                  }
                case _ => // Handle other components if needed
              }
            }
          }
        })*/
        if (controller.field.getRound % 2 == 0) {
          highlightHandCards(player2HandCards)
          //highlightCardsToAttack(player1FightCards) still needs to be written
        } else {
          println(player1.getHand)
          highlightHandCards(player1HandCards)
          //highlightCardsToAttack(player2FightCards) still needs to be written
        }
        //playerHandCards(player1).contents.foreach(c)
        //playField.revalidate()
        //repaint()
        //wenn karte 2 atk oder def kleiner als von karte 1 ist, dann meldung: attack not possible, your monster is too weak
        controller.attack()
        //newRound()
      }
      contents += Button("Play Card") {
        if (controller.field.getRound % 2 == 0) {
          highlightHandCards(player2HandCards)
          //to do: player should be able to click on the cards he wants to play
          //choose card to play and pass chosen card as argument for playCard function
          //momentan nur default card playable als bsp um zu prüfung obs geht
          val defaultCard: Card = new Card(CardName.weisser, CardLastName.Drache, 2000, 3000)
          //println(defaultCard)
          controller.playCard(defaultCard, "playCard")
          player2FightCards.contents.clear()
          player2FightField = controller.field.getPlayer2.getFightField
          println(player2FightField.getCards)

          player2FightField.getCards.foreach({ card =>
            val cardPanel = new BoxPanel(Orientation.Vertical) {
              border = new CompoundBorder(
                brownBorder,
                Swing.EmptyBorder(5, 5, 5, 5)
              )
              opaque = true
              preferredSize = cardSize
              if (card.isEmpty(card)) {
                val backgroundLabel: Label = new Label() {
                  println("emptyCard")
                  icon = cardImage
                  opaque = true
                }
                background = darkerBrown
                contents += backgroundLabel
              } else {
                println("weisser drache")
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
                  border = Swing.EmptyBorder(40, 3, 0, 0)
                }
                contents += new Label("DEF: " + card.defeToString) {
                  border = Swing.EmptyBorder(0, 3, 0, 0)
                }
              }
            }
            player2FightCards.contents += cardPanel
            //println(cardPanel)
            //repaint()
          })
          deckLabel.text = s"Deck: ${controller.field.getDeck.getDeckCount}"
          //player2HandCards.revalidate()
          //repaint()
        } else {
          highlightHandCards(player1HandCards)
          //choose card to play and pass chosen card as argument for playCard function
          //momentan nur erste card playable als bsp um zu prüfung obs geht
          val defaultCard: Card = new Card(CardName.weisser, CardLastName.Drache, 2000, 3000)
          controller.playCard(defaultCard, "playCard")
          player1FightCards.contents.clear()
          player1FightField = controller.field.getPlayer1.getFightField

          player1FightField.getCards.foreach({ card =>
            val cardPanel = new BoxPanel(Orientation.Vertical) {
              border = new CompoundBorder(
                brownBorder,
                Swing.EmptyBorder(5, 5, 5, 5)
              )
              opaque = true
              preferredSize = cardSize
              if (card.isEmpty(card)) {
                val backgroundLabel: Label = new Label() {
                  println("emptyCard")
                  icon = cardImage
                  opaque = true
                }
                background = darkerBrown
                contents += backgroundLabel
              } else {
                println("weisser drache")
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
                  border = Swing.EmptyBorder(40, 3, 0, 0)
                }
                contents += new Label("DEF: " + card.defeToString) {
                  border = Swing.EmptyBorder(0, 3, 0, 0)
                }
              }
            }
            player1FightCards.contents += cardPanel

          })
          deckLabel.text = s"Deck: ${controller.field.getDeck.getDeckCount}"
        }
        //playField.revalidate()
        //repaint()
        //newRound()
      }
      contents += Button("Draw") {
        if (controller.field.getRound % 2 == 0) {
          controller.drawCard("draw")
          player2HandCards.contents.clear()
          player2Hand = controller.field.getPlayer2.getHand
          player2Hand.getCards.foreach({ card =>
            val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
              border = new CompoundBorder(
                brownBorder,
                Swing.EmptyBorder(5, 5, 5, 5)
              )
              opaque = true
              preferredSize = cardSize
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
                  border = Swing.EmptyBorder(40, 3, 0, 0)
                }
                contents += new Label("DEF: " + card.defeToString) {
                  border = Swing.EmptyBorder(0, 3, 0, 0)
                }
              }
            }
            player2HandCards.contents += cardPanel
          })
          deckLabel.text = s"Deck: ${controller.field.getDeck.getDeckCount}"
          //player2HandCards.revalidate()
          //repaint()
        } else {
          controller.drawCard("draw")
          player1Hand = controller.field.getPlayer1.getHand
          player1HandCards.contents.clear()
          player1Hand.getCards.foreach({ card =>
            val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
              border = new CompoundBorder(
                brownBorder,
                Swing.EmptyBorder(5, 5, 5, 5)
              )
              opaque = true
              preferredSize = cardSize
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
                  border = Swing.EmptyBorder(40, 3, 0, 0)
                }
                contents += new Label("DEF: " + card.defeToString) {
                  border = Swing.EmptyBorder(0, 3, 0, 0)
                }
              }
            }
            player1HandCards.contents += cardPanel
          })
          deckLabel.text = s"Deck: ${controller.field.getDeck.getDeckCount}"
          //player1HandCards.revalidate()
          //repaint()
        }
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



  /*preferredSize = new Dimension(800, 600)
  resizable = false

  val padding = 20
  val cardSize = 100
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
}


}*/

