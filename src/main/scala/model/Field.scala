package model

case class Field(size: Int):
  val eol: String = sys.props("line.separator")

  def innerBar(cellWidth: Int = 3, cellNum: Int = 3) =
    ("+" + " " * cellWidth) + ("+" + "-" * cellWidth) * (cellNum - 1 ) + "+" + eol

  def outerBar(cellWidth: Int = 3, cellNum: Int = 3) =
    ("+" + "-" * cellWidth) * cellNum + "+" + eol

  def cells(cellWidth: Int = 3, cellNum: Int = 3) =
    (("|" + " " * cellWidth) * cellNum + "|" + eol) * ((cellWidth / 3) + 1)

  def LPStatsCell(cellWidth: Int = 3, cellNum: Int = 3) =
    ("|" + " " * (cellWidth - 1)) + (" " * cellWidth) * cellNum + "|" + eol

  def LPStatsCellPlayer(cellWidth: Int = 3, cellNum: Int = 3, input: String = "1") =
    "| Player " + input + (" " * (cellWidth - 4)) + (" " * cellWidth) * (cellNum-1) + "|" + eol

  def LPStatsCellOtherPlayer(cellWidth: Int = 3, cellNum: Int = 3, input: String = "1") =
    var otherInput: Int = 1
    if (input.toInt == 1) {
      otherInput = 2
    }
    "| Player " + otherInput + (" " * (cellWidth - 4)) + (" " * cellWidth) * (cellNum - 1) + "|" + eol

  def mesh(cellWidth: Int = size, cellNum: Int = size, input: String = "1") =
    (outerBar(cellWidth,cellNum)+ LPStatsCellOtherPlayer(cellWidth, cellNum, input)) + (outerBar(cellWidth, cellNum) + cells(cellWidth, cellNum) + innerBar(cellWidth, cellNum)) + (innerBar(cellWidth, cellNum) + cells(cellWidth, cellNum)) * 2 + outerBar(
      cellWidth,
      cellNum
    ) + LPStatsCellPlayer(cellWidth, cellNum, input) + outerBar(cellWidth, cellNum)
