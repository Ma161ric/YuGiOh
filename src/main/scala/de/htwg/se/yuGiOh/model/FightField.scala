package de.htwg.se.yuGiOh
package model

//import de.htwg.se.yuGiOh.model.Card


case class FightField(fightField: List[Card]):
  val eol: String = sys.props("line.separator")

  def getCards: List[Card] = fightField

  def getSize: Int = fightField.size

  /*def addCard(card: Card): FightField =
    val (left, right) = fightField.zipWithIndex.partition { case (noCard, _) =>
      noCard.toString != "No Card"
    }
    val firstZeroIndex = right.headOption.map(_._2)
    val updatedFightFieldList = firstZeroIndex match {
      case Some(index) =>
        left.map { case (c, _) => c } ++ Seq(card) ++ right.tail.map {
          case (c, _) => c
        }
      case None => fightField :+ card
    }
    val updatedFightField = FightField(updatedFightFieldList)
    updatedFightField*/

  private def roundCounterCell(cellWidth: Int, round: Int) =
    "|" + "Round: " + round + (" " * (cellWidth - (round.toString.length + 8))) + " "

  private def deckCounterCell(cellWidth: Int, deck: Int) =
    "|" + "Deck: " + deck + (" " * (cellWidth - (deck.toString.length + 7))) + " "

  private def innerRoundBar(cellWidth: Int, cellNum: Int, round: Int) =
    roundCounterCell(
      cellWidth,
      round
    ) + ("+" + "-" * cellWidth) * cellNum + "+" + eol

  private def innerDeckBar(cellWidth: Int, cellNum: Int, deck: Int) =
    deckCounterCell(
      cellWidth,
      deck
    ) + ("+" + "-" * cellWidth) * cellNum + "+" + eol

  def emptyCell(cellWidth: Int): String = "|" + " " * cellWidth

  def cardsFirstName(
      cellWidth: Int,
      fightFieldCards: List[Card]
  ): String =
    emptyCell(cellWidth) +
      fightFieldCards.foldLeft("") { (acc, card) =>
        acc +
          ("|" + card.getFirstName + (" " * (cellWidth - card.getFirstName.length)))
      } + "|" + eol

  def cardsLastName(
      cellWidth: Int,
      fightFieldCards: List[Card]
  ): String =
    emptyCell(cellWidth) +
      fightFieldCards.foldLeft("") { (acc, card) =>
        acc +
          ("|" + card.getLastName + (" " * (cellWidth - card.getLastName.length)))
      } + "|" + eol

  def cardsAtk(
      cellWidth: Int,
      fightFieldCards: List[Card]
  ): String =
    emptyCell(cellWidth) +
      fightFieldCards.foldLeft("") { (acc, card) =>
        acc +
          ("|" + "atk: " + card.atkToString + (" " * (cellWidth - (card.atkToString.length + 5))))
      } + "|" + eol

  def cardsDefe(
      cellWidth: Int,
      fightFieldCards: List[Card]
  ): String =
    emptyCell(cellWidth) +
      fightFieldCards.foldLeft("") { (acc, card) =>
        acc +
          ("|" + "def: " + card.defeToString + (" " * (cellWidth - (card.defeToString.length + 5))))
      } + "|" + eol

  def cardsPosition(
      cellWidth: Int,
      fightFieldCards: List[Card]
  ): String =
    emptyCell(cellWidth) +
      fightFieldCards.foldLeft("") { (acc, card) =>
        acc +
          ("|" + "pos: " + card.getPosition + (" " * (cellWidth - (card.getPosition.length + 5))))
      } + "|" + eol

  def otherPlayerRow(
      cellWidth: Int,
      cellNum: Int,
      fightFieldCards: List[Card],
      round: Int
  ): String =
    cardsFirstName(cellWidth, fightFieldCards) +
      cardsLastName(cellWidth, fightFieldCards) +
      cardsAtk(cellWidth, fightFieldCards) +
      cardsDefe(cellWidth, fightFieldCards) +
      cardsPosition(cellWidth, fightFieldCards) +
      innerRoundBar(cellWidth, cellNum, round)

  def playerRow(
      cellWidth: Int,
      cellNum: Int,
      fightFieldCards: List[Card],
      deck: Int
  ): String =
    innerDeckBar(cellWidth, cellNum, deck) +
      cardsFirstName(cellWidth, fightFieldCards) +
      cardsLastName(cellWidth, fightFieldCards) +
      cardsAtk(cellWidth, fightFieldCards) +
      cardsDefe(cellWidth, fightFieldCards) +
      cardsPosition(cellWidth, fightFieldCards)
