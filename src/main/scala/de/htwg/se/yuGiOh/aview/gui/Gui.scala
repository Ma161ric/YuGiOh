package de.htwg.se.yuGiOh.aview.gui


import de.htwg.se.yuGiOh.controller.Controller
import de.htwg.se.yuGiOh.model.{Card, CardName, Deck, FightField, Hand, Player}
import de.htwg.se.yuGiOh.util.{Event, Observer}

import javax.swing.ImageIcon
import javax.swing.BorderFactory
import java.awt.Color
import scala.swing.{BoxPanel, *}
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
    case Event.changeCardPosition =>
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

  //val emptyCard: Card = Card.emptyCard
  val deck: Deck = controller.field.deck
  val nameLabel = new Label("Welcome to Yu-Gi-Oh!")
  val startButton = new Button("Start Game")
  val player1: Player = controller.field.getPlayer1
  val player2: Player = controller.field.getPlayer2
  val player1Label = new Label(s"Player 1: ${player1.toString}")
  val player2Label = new Label(s"Player 2: ${player2.toString}")
  val player1LpLabel = new Label(s"LP: ${player1.getLp}")
  val player2LpLabel = new Label(s"LP: ${player2.getLp}")
  var player1Hand: Hand = player1.getHand
  val player1player1HandCards: List[Card] = player1.getHand.getCards
  var player2Hand: Hand = player2.getHand
  val player1FightField = new Label(s"FightField: ${player1.getFightField}")
  val player2FightField = new Label(s"FightField: ${player2.getFightField}")
  val cardLabels: List[Label] = List.fill(6)(new Label("Test"))
  val roundLabel = new Label(s"Round: ${controller.field.getRound}")
  val deckLabel = new Label(s"Deck: ${controller.field.getDeck.getDeckCount}")
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

  /*val card: BoxPanel = new BoxPanel(Orientation.Vertical) {
    border = BorderFactory. createLineBorder(Color.black, 1)
    preferredSize = new Dimension(50, 100)

    contents += new Label(s"First Name: ${emptyCard.getFirstName}")
    contents += new Label(s"Last Name ${emptyCard.getLastName}")
    contents += new Label(s"atk: ${emptyCard.getAtk}")
    contents += new Label(s"def: ${emptyCard.getDefe}")
  }*/


  val player1HandCards: FlowPanel = new FlowPanel() {
    player1Hand.getCards.foreach({ card =>
      val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
        border = BorderFactory.createLineBorder(Color.black, 1)
        contents += new Label(card.getFirstName) {
          xAlignment = Alignment.Center
        }
        contents += new Label(card.getLastName) {
          xAlignment = Alignment.Center
        }
        contents += new Label("ATK: " + card.atkToString) {
          xAlignment = Alignment.Center
        }
        contents += new Label("DEF: " + card.defeToString) {
          xAlignment = Alignment.Center
        }
        preferredSize = new Dimension(120, 100)
      }
      //contents += Swing.HStrut(10)
      //contents += new Separator(Orientation.Vertical)
      contents += cardPanel
    })
  }

  val player2HandCards: FlowPanel = new FlowPanel() {
    player2Hand.getCards.foreach({ card =>
      val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
        border = BorderFactory.createLineBorder(Color.black, 1)
        contents += new Label(card.getFirstName) {
          xAlignment = Alignment.Center
        }
        contents += new Label(card.getLastName) {
          xAlignment = Alignment.Center
        }
        contents += new Label("ATK: " + card.atkToString) {
          xAlignment = Alignment.Center
        }
        contents += new Label("DEF: " + card.defeToString) {
          xAlignment = Alignment.Center
        }
        preferredSize = new Dimension(120, 100)
      }
      //contents += Swing.HStrut(10)
      //contents += new Separator(Orientation.Vertical)
      contents += cardPanel
    })
  }

  val player1HandFields: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    border = BorderFactory.createLineBorder(Color.black, 1)
    preferredSize = new Dimension(600, 100)

    contents += player1HandCards
  }

  val player2HandFields: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    border = BorderFactory.createLineBorder(Color.black, 1)
    preferredSize = new Dimension(600, 100)

    contents += player2HandCards
  }

  val player1FightFields: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    border = BorderFactory.createLineBorder(Color.black, 1)
    preferredSize = new Dimension(600, 100)
    player1.getFightField.getCards.foreach({ card =>
      val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
        contents += new Label(card.getFirstName) {
          xAlignment = Alignment.Center
        }
        contents += new Label(card.getLastName) {
          xAlignment = Alignment.Center
        }
        contents += new Label("ATK: " + card.atkToString) {
          xAlignment = Alignment.Center
        }
        contents += new Label("DEF: " + card.defeToString) {
          xAlignment = Alignment.Center
        }
        preferredSize = new Dimension(120, 100)
      }
      //contents += Swing.HStrut(10)
      contents += new Separator(Orientation.Vertical)
      contents += cardPanel
    })
    //contents.trimEnd(1)
    //border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
  }

  val player2FightFields: BoxPanel = new BoxPanel(Orientation.Horizontal) {
    border = BorderFactory.createLineBorder(Color.black, 1)
    preferredSize = new Dimension(600, 100)
    player2.getFightField.getCards.foreach({ card =>
      val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
        contents += new Label(card.getFirstName) {
          xAlignment = Alignment.Center
        }
        contents += new Label(card.getLastName) {
          xAlignment = Alignment.Center
        }
        contents += new Label("ATK: " + card.atkToString) {
          xAlignment = Alignment.Center
        }
        contents += new Label("DEF: " + card.defeToString) {
          xAlignment = Alignment.Center
        }
        preferredSize = new Dimension(120, 100)
      }
      //contents += Swing.HStrut(10)
      contents += new Separator(Orientation.Vertical)
      contents += cardPanel
    })
    //contents.trimEnd(1)
    //border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
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
        //background = Color(645, 645, 645)
        contents += roundLabel
        contents += deckLabel
      }
      contents += new BoxPanel(Orientation.Vertical) {
        contents += player2FightFields
        contents += player1FightFields
        contents += player1HandFields
      }
    }
    contents += player1Stats
  }

  def updateLayoutForRound(round: Int): Unit = {
    if (round % 2 == 0) {
      playField.contents.clear()

      playField.contents += player1Stats
      playField.contents += new BoxPanel(Orientation.Horizontal) {
        contents += new BoxPanel(Orientation.Vertical) {
          //background = Color(645, 645, 645)
          contents += roundLabel
          contents += deckLabel
        }
        contents += new BoxPanel(Orientation.Vertical) {
          contents += player1FightFields
          contents += player2FightFields
          contents += player2HandFields
        }
      }
      playField.contents += player2Stats
    } else {
      playField.contents.clear()

      playField.contents += player2Stats
      playField.contents += new BoxPanel(Orientation.Horizontal) {
        contents += new BoxPanel(Orientation.Vertical) {
          //background = Color(645, 645, 645)
          contents += roundLabel
          contents += deckLabel
        }
        contents += new BoxPanel(Orientation.Vertical) {
          contents += player2FightFields
          contents += player1FightFields
          contents += player1HandFields
        }
      }
      playField.contents += player1Stats
    }

    // Refresh the GUI
    playField.revalidate()
    repaint()
  }


  private def updateContent() =
    new BorderPanel:
      add(playField, BorderPanel.Position.North)
      add(actionsBar, BorderPanel.Position.South)

  private def actionsBar: GridPanel =
    new GridPanel(1, 4):
      contents += Button("Attack") {
        player1HandCards.contents.foreach({
          case cardPanel: BoxPanel =>
            val firstName = cardPanel.contents.collectFirst { case label: Label => label.text }.getOrElse("")
            if (firstName != "No") {
              cardPanel.background = java.awt.Color.YELLOW // Set the desired background color
              cardPanel.opaque = true
            }
        })
        repaint()
        //wenn karte 2 atk oder def kleiner als von karte 1 ist, dann meldung: attack not possible, your monster is too weak
        //controller.attack()
      }
      contents += Button("Play Card") {
        //controller.playCard()
      }
      contents += Button("Draw") {
        println(controller.field.getDeck)
        if (controller.field.getRound % 2 == 0) {
          val (updatedDeck, updatedHand) = controller.drawCard(player1Hand, controller.field.getDeck)
          player2Hand = updatedHand
          player2HandCards.contents.clear()
          updatedHand.getCards.foreach({ card =>
            val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
              border = BorderFactory.createLineBorder(Color.black, 1)
              contents += new Label(card.getFirstName) {
                xAlignment = Alignment.Center
              }
              contents += new Label(card.getLastName) {
                xAlignment = Alignment.Center
              }
              contents += new Label("ATK: " + card.atkToString) {
                xAlignment = Alignment.Center
              }
              contents += new Label("DEF: " + card.defeToString) {
                xAlignment = Alignment.Center
              }
              preferredSize = new Dimension(120, 100)
            }
            player2HandCards.contents += cardPanel
          })
          deckLabel.text = s"Deck: ${updatedDeck.getDeckCount}"
          player2HandCards.revalidate()
          repaint()
        } else {
          val (updatedDeck, updatedHand) = controller.drawCard(player1Hand, controller.field.getDeck)
          player1Hand = updatedHand
          player1HandCards.contents.clear()
          updatedHand.getCards.foreach({ card =>
            val cardPanel: BoxPanel = new BoxPanel(Orientation.Vertical) {
              border = BorderFactory.createLineBorder(Color.black, 1)
              contents += new Label(card.getFirstName) {
                xAlignment = Alignment.Center
              }
              contents += new Label(card.getLastName) {
                xAlignment = Alignment.Center
              }
              contents += new Label("ATK: " + card.atkToString) {
                xAlignment = Alignment.Center
              }
              contents += new Label("DEF: " + card.defeToString) {
                xAlignment = Alignment.Center
              }
              preferredSize = new Dimension(120, 100)
            }
            player1HandCards.contents += cardPanel
          })
          deckLabel.text = s"Deck: ${updatedDeck.getDeckCount}"
          player1HandCards.revalidate()
          repaint()
        }
      }
      contents += Button("Skip") {
        roundLabel.text = s"Round: ${controller.field.getRound}"
        controller.skip()
        updateLayoutForRound(controller.field.getRound)
      }
      border = Swing.EmptyBorder(10, 10, 10, 10)

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
}


}*/

