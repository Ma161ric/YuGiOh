package model

case class Hand(size: Int, hand: List[Card]):
  val eol: String = sys.props("line.separator")

  def emptyCell(cellWidth: Int = 3) = "|" + " " * cellWidth
  
  def innerBar(cellWidth: Int = 3, cellNum: Int = 3) =
    emptyCell(cellWidth) + ("+" + "-" * cellWidth) * (cellNum) + "+" + eol

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


  def playerHandRow(cellWidth: Int = size, cellNum: Int = size, card: Card) =
    (innerBar(cellWidth, cellNum) + cardsFirstName(cellWidth, cellNum, card) + cardsLastName(cellWidth, cellNum, card) + cardsAtk(cellWidth, cellNum, card) + cardsDefe(cellWidth, cellNum, card) + cardsPosition(cellWidth, cellNum, card))
