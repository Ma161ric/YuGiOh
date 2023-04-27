package model

case class Hand(hand: List[Card]):
  val eol: String = sys.props("line.separator")

  def getCards: List[Card] = hand

  def getSize: Int = hand.size

  def emptyCell(cellWidth: Int = 3): String = "|" + " " * cellWidth

  private def innerBar(cellWidth: Int = 3, cellNum: Int = 3): String =
    emptyCell(cellWidth) + ("+" + "-" * cellWidth) * (cellNum) + "+" + eol

  def cardsFirstName(cellWidth: Int = 3, cellNum: Int = 3, hand: List[Card]): String =
    emptyCell(cellWidth) +
    hand.foldLeft("") { (acc, card) =>
      acc +
        ("|" + card.getFirstName + (" " * (cellWidth - card.getFirstName.length)))
    } + "|" + eol
  def cardsLastName(cellWidth: Int = 3, cellNum: Int = 3, hand: List[Card]): String =
    emptyCell(cellWidth) +
      hand.foldLeft("") { (acc, card) =>
      acc +
        ("|" + card.getLastName + (" " * (cellWidth - card.getLastName.length)))
    } + "|" + eol

  def cardsAtk(cellWidth: Int = 3, cellNum: Int = 3, hand: List[Card]): String =
    emptyCell(cellWidth) +
    hand.foldLeft("") { (acc, card) =>
      acc +
        ("|" + "atk: " + card.atkToString + (" " * (cellWidth - (card.atkToString.length + 5))))
    } + "|" + eol

  def cardsDefe(cellWidth: Int = 3, cellNum: Int = 3, hand: List[Card]): String =
    emptyCell(cellWidth) +
    hand.foldLeft("") { (acc, card) =>
      acc +
        ("|" + "def: " + card.defeToString + (" " * (cellWidth - (card.defeToString.length + 5))))
    } + "|" + eol

  def cardsPosition(cellWidth: Int = 3, cellNum: Int = 3, hand: List[Card]): String =
    emptyCell(cellWidth) +
    hand.foldLeft("") { (acc, card) =>
      acc +
        ("|" + "pos: " + card.getPosition + (" " * (cellWidth - (card.getPosition.length + 5))))
    } + "|" + eol

  def playerHandRow(cellWidth: Int = 3, cellNum: Int = hand.size, hand: List[Card]): String =
    innerBar(cellWidth, cellNum) +
    cardsFirstName(cellWidth, cellNum, hand) +
    cardsLastName(cellWidth, cellNum, hand) +
    cardsAtk(cellWidth, cellNum, hand) +
    cardsDefe(cellWidth, cellNum, hand) +
    cardsPosition(cellWidth, cellNum, hand)

