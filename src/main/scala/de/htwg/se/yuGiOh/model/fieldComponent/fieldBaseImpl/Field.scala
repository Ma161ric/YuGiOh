package de.htwg.se.yuGiOh.model.fieldComponent.fieldBaseImpl

import de.htwg.se.yuGiOh.model.fieldComponent.{FieldInterface,PlayerInterfaceOld}
//to do: check if player or playerinterface is better here
case class Field (
                   size: Int,
                   round: Int,
                   deck: Deck,
                   player1: PlayerInterfaceOld,
                   player2: PlayerInterfaceOld
) extends FieldInterface :

  val eol: String = sys.props("line.separator")
  var currentPlayer: Int = 1

  /*def this(size: Int, round: Int) =
    this(size, round, deck, player1, player2)*/

  def copy(size: Int = this.size, round: Int = this.round, deck: Deck = this.deck, player1: PlayerInterfaceOld = this.player1, player2: PlayerInterfaceOld = this.player2): FieldInterface =
    Field(size, round, deck, player1, player2)

  def getCurrentPlayer(): PlayerInterfaceOld =
    currentPlayer match
      case 1 => player1
      case 2 => player2

  def nextPlayer(): Unit =
    currentPlayer match
      case 1 => currentPlayer = 2
      case 2 => currentPlayer = 1

  def getSize: Int = size
  def getPlayer1: PlayerInterfaceOld = player1
  def getPlayer2: PlayerInterfaceOld = player2
  def getRound: Int = round
  def getDeck: Deck = deck


  def outerBar(cellWidth: Int, cellNum: Int): String =
    ("+" + "-" * cellWidth) * (cellNum + 1) + "+" + eol
  def outerOuterBar(cellWidth: Int, cellNum: Int): String =
    "+" + "-" * cellWidth * (cellNum + 1) + "-" * cellNum + "+" + eol
  private def otherPlayerRow(
      cellWidth: Int,
      cellNum: Int,
      fightField: FightField,
      round: Int
  ): String =
    fightField.otherPlayerRow(cellWidth, cellNum, fightField.getCards, round)
  def playerHandRow(
      cellWidth: Int,
      cellNum: Int,
      hand: Hand
  ): String =
    hand.playerHandRow(cellWidth, cellNum, hand.getCards)
  private def playerRow(
      cellWidth: Int,
      cellNum: Int,
      fightField: FightField,
      deck: Int
  ): String =
    fightField.playerRow(cellWidth, cellNum, fightField.getCards, deck)

  private def playerLp(cellWidth: Int, playerLp: String): String =
    "LP: " + playerLp + (" " * (cellWidth * 2 - (playerLp.length + 4)) + "  ")
  private def playerName(cellWidth: Int, playerName: String): String =
    "Player: " + playerName + (" " * (cellWidth * 3 - (playerName.length + 10)) + "   ")
  private def playerStatsRow(
      cellWidth: Int,
      cellNum: Int,
      player: PlayerInterfaceOld
  ): String =
    "| " + playerName(cellWidth, player.toString) + playerLp(
      cellWidth,
      player.getLp.toString
    ) + (" " * cellWidth) * (cellNum - 4) + " " * (cellNum - 4) + "|" + eol

  private def mesh(
                    cellWidth: Int,
                    cellNum: Int,
                    player1: PlayerInterfaceOld,
                    player2: PlayerInterfaceOld
  ): String =
    outerOuterBar(cellWidth, cellNum)
      + playerStatsRow(cellWidth, cellNum, player2)
      + outerBar(cellWidth, cellNum)
      + otherPlayerRow(cellWidth, cellNum, player2.getFightField, round)
      + playerRow(cellWidth, cellNum, player1.getFightField, deck.getDeckCount)
      + playerHandRow(cellWidth, cellNum, player1.getHand)
      + outerBar(cellWidth, cellNum)
      + playerStatsRow(cellWidth, cellNum, player1)
      + outerOuterBar(cellWidth, cellNum)

  override def toString: String = mesh(10, size, player1, player2)
