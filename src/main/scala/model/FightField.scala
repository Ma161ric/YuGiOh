package model

case class FightField(fightField: List[Card]):
  val eol: String = sys.props("line.separator")

  def getCards = fightField

  def getSize = fightField.size

  def roundCounterCell(cellWidth: Int = 3, round: Int = 1) =
    "|" + "Round: " + round + (" " * (cellWidth - (round.toString.length + 8))) + " "

  def deckCounterCell(cellWidth: Int = 3, deck: Int = 40) =
    "|" + "Deck: " + deck + (" " * (cellWidth - (deck.toString.length + 7))) + " "

  def innerRoundBar(cellWidth: Int = 3, cellNum: Int = 3, round: Int = 1) =
    roundCounterCell(cellWidth, round) + ("+" + "-" * cellWidth) * (cellNum) + "+" + eol

  def innerDeckBar(cellWidth: Int = 3, cellNum: Int = 3, deck: Int = 40 ) =
    deckCounterCell(cellWidth, deck) + ("+" + "-" * cellWidth) * (cellNum) + "+" + eol

  def emptyCell(cellWidth: Int = 3): String = "|" + " " * cellWidth

  def cardsFirstName(cellWidth: Int = 3, cellNum: Int = 3, fightFieldCards: List[Card]) =
    emptyCell(cellWidth) +
    fightFieldCards.foldLeft("") { (acc, card) =>
      acc +
        ("|" + card.getFirstName + (" " * (cellWidth - card.getFirstName.length)))
    } + "|" + eol


  def cardsLastName(cellWidth: Int = 3, cellNum: Int = 3, fightFieldCards: List[Card]) =
    emptyCell(cellWidth) +
    fightFieldCards.foldLeft("") { (acc, card) =>
      acc +
        ("|" + card.getLastName + (" " * (cellWidth - card.getLastName.length)))
    } + "|" + eol

  def cardsAtk(cellWidth: Int = 3, cellNum: Int = 3, fightFieldCards: List[Card]) =
    emptyCell(cellWidth) +
    fightFieldCards.foldLeft("") { (acc, card) =>
      acc +
        ("|" + "atk: " + card.atkToString + (" " * (cellWidth - (card.atkToString.length + 5))))
    } + "|" + eol

  def cardsDefe(cellWidth: Int = 3, cellNum: Int = 3, fightFieldCards: List[Card]) =
    emptyCell(cellWidth) +
    fightFieldCards.foldLeft("") { (acc, card) =>
      acc +
        ("|" + "def: " + card.defeToString + (" " * (cellWidth - (card.defeToString.length + 5))))
    } + "|" + eol

  def cardsPosition(cellWidth: Int = 3, cellNum: Int = 3, fightFieldCards: List[Card]) =
    emptyCell(cellWidth) +
    fightFieldCards.foldLeft("") { (acc, card) =>
      acc +
        ("|" + "pos: " + card.getPosition + (" " * (cellWidth - (card.getPosition.length + 5))))
    } + "|" + eol

  def otherPlayerRow(cellWidth: Int = 3, cellNum: Int = fightField.size, fightFieldCards: List[Card], round: Int = 1) =
    cardsFirstName(cellWidth, cellNum, fightFieldCards) +
    cardsLastName(cellWidth, cellNum, fightFieldCards) +
    cardsAtk(cellWidth, cellNum, fightFieldCards) +
    cardsDefe(cellWidth, cellNum, fightFieldCards) +
    cardsPosition(cellWidth, cellNum, fightFieldCards) +
    innerRoundBar(cellWidth, cellNum, round)

  def playerRow(cellWidth: Int = 3, cellNum: Int = fightField.size, fightFieldCards: List[Card], deck: Int = 40) =
    innerDeckBar(cellWidth, cellNum, deck) +
    cardsFirstName(cellWidth, cellNum, fightFieldCards) +
    cardsLastName(cellWidth, cellNum, fightFieldCards) +
    cardsAtk(cellWidth, cellNum, fightFieldCards) +
    cardsDefe(cellWidth, cellNum, fightFieldCards) +
    cardsPosition(cellWidth, cellNum, fightFieldCards)

