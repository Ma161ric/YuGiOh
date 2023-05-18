package de.htwg.se.yuGiOh.model

case class Hand(hand: List[Card]):
  val eol: String = sys.props("line.separator")

  def getCards: List[Card] = hand

  def getCard(i: Int): Card = hand(i - 1)

  def getSize: Int = hand.size

  def emptyCell(cellWidth: Int): String = "|" + " " * cellWidth

  def innerBar(cellWidth: Int, cellNum: Int): String =
    emptyCell(cellWidth) + ("+" + "-" * cellWidth) * cellNum + "+" + eol

  def cardsFirstName(
      cellWidth: Int,
      hand: List[Card]
  ): String =
    emptyCell(cellWidth) +
      hand.foldLeft("") { (acc, card) =>
        acc +
          ("|" + card.getFirstName + (" " * (cellWidth - card.getFirstName.length)))
      } + "|" + eol
  def cardsLastName(
      cellWidth: Int,
      hand: List[Card]
  ): String =
    emptyCell(cellWidth) +
      hand.foldLeft("") { (acc, card) =>
        acc +
          ("|" + card.getLastName + (" " * (cellWidth - card.getLastName.length)))
      } + "|" + eol

  def cardsAtk(cellWidth: Int, hand: List[Card]): String =
    emptyCell(cellWidth) +
      hand.foldLeft("") { (acc, card) =>
        acc +
          ("|" + "atk: " + card.atkToString + (" " * (cellWidth - (card.atkToString.length + 5))))
      } + "|" + eol

  def cardsDefe(
      cellWidth: Int,
      hand: List[Card]
  ): String =
    emptyCell(cellWidth) +
      hand.foldLeft("") { (acc, card) =>
        acc +
          ("|" + "def: " + card.defeToString + (" " * (cellWidth - (card.defeToString.length + 5))))
      } + "|" + eol

  def cardsPosition(
      cellWidth: Int,
      hand: List[Card]
  ): String =
    emptyCell(cellWidth) +
      hand.foldLeft("") { (acc, card) =>
        acc +
          ("|" + "pos: " + card.getPosition + (" " * (cellWidth - (card.getPosition.length + 5))))
      } + "|" + eol

  def playerHandRow(
      cellWidth: Int,
      cellNum: Int,
      hand: List[Card]
  ): String =
    innerBar(cellWidth, cellNum) +
      cardsFirstName(cellWidth, hand) +
      cardsLastName(cellWidth, hand) +
      cardsAtk(cellWidth, hand) +
      cardsDefe(cellWidth, hand) +
      cardsPosition(cellWidth, hand)
