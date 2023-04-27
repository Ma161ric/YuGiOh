package model

case class Field(
    size: Int,
    round: Int = 1,
    deck: Int = 40,
    hand: Hand,
    fightField: FightField,
    player1: Player,
    player2: Player
):
  val eol: String = sys.props("line.separator")

  def outerBar(cellWidth: Int = 3, cellNum: Int = 3) =
    ("+" + "-" * cellWidth) * (cellNum + 1) + "+" + eol
  def outerOuterBar(cellWidth: Int = 3, cellNum: Int = 3) =
    ("+") + ("-" * cellWidth) * (cellNum + 1) + "-" * cellNum + "+" + eol
  def otherPlayerRow(
      cellWidth: Int = 3,
      cellNum: Int = size,
      fightField: FightField,
      round: Int = 1
  ) =
    fightField.otherPlayerRow(cellWidth, cellNum, fightField.getCards, round)
  def playerHandRow(cellWidth: Int = 3, cellNum: Int = size, hand: Hand) =
    hand.playerHandRow(cellWidth, cellNum, hand.getCards)
  def playerRow(
      cellWidth: Int = 3,
      cellNum: Int = size,
      fightField: FightField,
      deck: Int = deck
  ) =
    fightField.playerRow(cellWidth, cellNum, fightField.getCards, deck)

  def playerLp(cellWidth: Int = 3, playerLp: String) =
    "LP: " + playerLp + (" " * (cellWidth * 2 - (playerLp.length + 4)) + "  ")
  def playerName(cellWidth: Int = 3, playerName: String) =
    "Player: " + playerName + (" " * (cellWidth * 3 - (playerName.length + 10)) + "   ")
  def playerStatsRow(cellWidth: Int = 3, cellNum: Int = 3, player: Player) =
    "| " + playerName(cellWidth, player.toString) + playerLp(
      cellWidth,
      player.getLp
    ) + (" " * cellWidth) * (cellNum - 4) + " " * (cellNum - 4) + "|" + eol

  def mesh(
      cellWidth: Int = 10,
      cellNum: Int = size,
      player1: Player,
      player2: Player
  ): String =
    outerOuterBar(cellWidth, cellNum)
      + playerStatsRow(cellWidth, cellNum, player2)
      + outerBar(cellWidth, cellNum)
      + otherPlayerRow(cellWidth, cellNum, player2.getFightField, round)
      + playerRow(cellWidth, cellNum, player1.getFightField)
      + playerHandRow(cellWidth, cellNum, player1.getHand)
      + outerBar(cellWidth, cellNum)
      + playerStatsRow(cellWidth, cellNum, player1)
      + outerOuterBar(cellWidth, cellNum)

  override def toString: String = mesh(10, size, player1, player2)
