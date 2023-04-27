package model


case class Field(size: Int, round: Int = 1, deck: Int = 40, hand: Hand, fightField: FightField):
  val eol: String = sys.props("line.separator")

  //def innerBar(cellWidth: Int = 3, cellNum: Int = 3) =
   // emptyCell(cellWidth) + ("+" + "-" * cellWidth) * (cellNum) + "+" + eol

  //def innerRoundBar(cellWidth: Int = 3, cellNum: Int = 3) =
  //  roundCounterCell(cellWidth, round) + ("+" + "-" * cellWidth) * (cellNum) + "+" + eol

 // def innerDeckBar(cellWidth: Int = 3, cellNum: Int = 3) =
  //  deckCounterCell(cellWidth, deck) + ("+" + "-" * cellWidth) * (cellNum) + "+" + eol

  //def emptyCell(cellWidth: Int = 3) = "|" + " " * cellWidth

 // def cardsFirstName(cellWidth: Int = 3, cellNum: Int = 3, card: Card) =
   // emptyCell(cellWidth) + (("|" + card.toString + (" " * (cellWidth - card.toString.length))) * (cellNum) + "|" + eol)
 // def cardsLastName(cellWidth: Int = 3, cellNum: Int = 3, card: Card) =
  //  emptyCell(cellWidth) + (("|" + card.getLastName  + (" " * (cellWidth - card.getLastName.length))) * (cellNum) + "|" + eol)
  //def cardsAtk(cellWidth: Int = 3, cellNum: Int = 3, card: Card) =
  //  emptyCell(cellWidth) + (("|" + "atk: " + card.atkToString + (" " * (cellWidth - (card.atkToString.length + 5)))) * (cellNum) + "|" + eol)
 // def cardsDefe(cellWidth: Int = 3, cellNum: Int = 3, card: Card) =
   // emptyCell(cellWidth) + (("|" + "def: " + card.defeToString + (" " * (cellWidth - (card.defeToString.length + 5)))) * (cellNum) + "|" + eol)
  // def cardsPosition(cellWidth: Int = 3, cellNum: Int = 3, card: Card) =
  //   emptyCell(cellWidth) + (("|" + "pos: " + card.getPosition + (" " * (cellWidth - (card.getPosition.length + 5)))) * (cellNum) + "|" + eol)

  //def roundCounterCell(cellWidth: Int = 3, round: Int = round) =
  // "|" + "Round: " + round +  (" " * (cellWidth - (round.toString.length + 8))) + " "
  //def deckCounterCell(cellWidth: Int = 3, deck: Int = deck) =
  //  "|" + "Deck: " + deck + (" " * (cellWidth - (deck.toString.length + 7))) + " "

  //def playerHandRow(cellWidth: Int = size, cellNum: Int = size, card: Card) =
  //  (innerBar(cellWidth, cellNum) + cardsFirstName(cellWidth, cellNum, card) + cardsLastName(cellWidth, cellNum, card) + cardsAtk(cellWidth, cellNum, card) + cardsDefe(cellWidth, cellNum, card) + cardsPosition(cellWidth, cellNum, card))

  //def otherPlayerRow(cellWidth: Int = size, cellNum: Int = size, card: Card) =
  // (outerBar(cellWidth, cellNum) + cardsFirstName(cellWidth, cellNum, card) + cardsLastName(cellWidth, cellNum, card) + cardsAtk(cellWidth, cellNum, card) + cardsDefe(cellWidth, cellNum, card) + cardsPosition(cellWidth, cellNum, card) + innerRoundBar(cellWidth, cellNum))

  // def playerRow(cellWidth: Int = size, cellNum: Int = size, card: Card) =
  // (innerDeckBar(cellWidth, cellNum) + cardsFirstName(cellWidth, cellNum, card) + cardsLastName(cellWidth, cellNum, card) + cardsAtk(cellWidth, cellNum, card) + cardsDefe(cellWidth, cellNum, card) + cardsPosition(cellWidth, cellNum, card))

  def otherPlayerRow(cellWidth: Int = size, cellNum: Int = size, card: Card) =
    fightField.otherPlayerRow(cellWidth, cellNum, card)
  def playerHandRow(cellWidth: Int = size, cellNum: Int = size, card: Card) =
    hand.playerHandRow(cellWidth, cellNum, card)
  //to do: soll später hand als parameter nehmen nicht nur eine card
  def outerBar(cellWidth: Int = 3, cellNum: Int = 3) =
    ("+" + "-" * cellWidth) * (cellNum + 1) + "+" + eol

  def outerOuterBar(cellWidth: Int = 3, cellNum: Int = 3) =
    ("+") + ("-" * cellWidth) * (cellNum + 1) + "-" * cellNum + "+" + eol

  def playerLP(cellWidth: Int = 3, player: Player) =
    "LP: " + player.getLifePoints + (" " * (cellWidth * 2 - (player.getLifePoints.length + 4)) + "  ")
  def playerName(cellWidth: Int = 3, player: Player) =
    "Player: " + player.toString + (" " * (cellWidth * 3 - (player.toString.length + 10)) + "   ")
  def playerStatsRow(cellWidth: Int = 3, cellNum: Int = 3, player: Player) =
    "| " + playerName(cellWidth, player) + playerLP(cellWidth, player) + (" " * cellWidth) * (cellNum-4) + " " * (cellNum-4) + "|" + eol

  def playerRow(cellWidth: Int = size, cellNum: Int = size, card: Card, round: Int = round, deck: Int = deck) =
    fightField.playerRow(cellWidth, cellNum, card, deck)


  def mesh(cellWidth: Int = 10, cellNum: Int = size, card: Card, player1: Player, player2: Player) =
      outerOuterBar(cellWidth,cellNum)
      + playerStatsRow(cellWidth, cellNum, player2)
      + outerBar(cellWidth, cellNum)
      + otherPlayerRow(cellWidth, cellNum, card)
      + playerRow(cellWidth, cellNum, card)
      + playerHandRow(cellWidth, cellNum, card)
      + outerBar(cellWidth, cellNum)
      + playerStatsRow(cellWidth, cellNum, player1)
      + outerOuterBar(cellWidth, cellNum)
