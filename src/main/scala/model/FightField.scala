package model

case class FightField(size: Int, fightField: List[Card]):
  val eol: String = sys.props("line.separator")

  def roundCounterCell(cellWidth: Int = 3, round: Int = 1) =
    "|" + "Round: " + round + (" " * (cellWidth - (round.toString.length + 8))) + " "

  def deckCounterCell(cellWidth: Int = 3, deck: Int = 40) =
    "|" + "Deck: " + deck + (" " * (cellWidth - (deck.toString.length + 7))) + " "

  def innerRoundBar(cellWidth: Int = 3, cellNum: Int = 3, round: Int = 1) =
    roundCounterCell(cellWidth, round) + ("+" + "-" * cellWidth) * (cellNum) + "+" + eol

  def innerDeckBar(cellWidth: Int = 3, cellNum: Int = 3, deck: Int = 40 ) =
    deckCounterCell(cellWidth, deck) + ("+" + "-" * cellWidth) * (cellNum) + "+" + eol

  def emptyCell(cellWidth: Int = 3) = "|" + " " * cellWidth

  def cardsFirstName(cellWidth: Int = 3, cellNum: Int = 3, card: Card) =
    emptyCell(cellWidth) + (("|" + card.toString + (" " * (cellWidth - card.toString.length))) * (cellNum) + "|" + eol)

  def cardsLastName(cellWidth: Int = 3, cellNum: Int = 3, card: Card) =
    emptyCell(cellWidth) + (("|" + card.getLastName + (" " * (cellWidth - card.getLastName.length))) * (cellNum) + "|" + eol)

  def cardsAtk(cellWidth: Int = 3, cellNum: Int = 3, card: Card) =
    emptyCell(cellWidth) + (("|" + "atk: " + card.atkToString + (" " * (cellWidth - (card.atkToString.length + 5)))) * (cellNum) + "|" + eol)

  def cardsDefe(cellWidth: Int = 3, cellNum: Int = 3, card: Card) =
    emptyCell(cellWidth) + (("|" + "def: " + card.defeToString + (" " * (cellWidth - (card.defeToString.length + 5)))) * (cellNum) + "|" + eol)

  def cardsPosition(cellWidth: Int = 3, cellNum: Int = 3, card: Card) =
    emptyCell(cellWidth) + (("|" + "pos: " + card.getPosition + (" " * (cellWidth - (card.getPosition.length + 5)))) * (cellNum) + "|" + eol)

  def otherPlayerRow(cellWidth: Int = size, cellNum: Int = size, card: Card, round: Int = 1) =
    cardsFirstName(cellWidth, cellNum, card) + cardsLastName(cellWidth, cellNum, card) + cardsAtk(cellWidth, cellNum, card) + cardsDefe(cellWidth, cellNum, card) + cardsPosition(cellWidth, cellNum, card) + innerRoundBar(cellWidth, cellNum, round)

  def playerRow(cellWidth: Int = size, cellNum: Int = size, card: Card, deck: Int = 40) =
    (innerDeckBar(cellWidth, cellNum, deck) + cardsFirstName(cellWidth, cellNum, card) + cardsLastName(cellWidth, cellNum, card) + cardsAtk(cellWidth, cellNum, card) + cardsDefe(cellWidth, cellNum, card) + cardsPosition(cellWidth, cellNum, card))
