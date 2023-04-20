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

  
  def mesh(cellWidth: Int = size, cellNum: Int = size) =
    (outerBar(cellWidth,cellNum)+ LPStatsCell(cellWidth, cellNum)) + (outerBar(cellWidth, cellNum) + cells(cellWidth, cellNum) + innerBar(cellWidth, cellNum)) + (innerBar(cellWidth, cellNum) + cells(cellWidth, cellNum)) * 2 + outerBar(
      cellWidth,
      cellNum
    ) + LPStatsCell(cellWidth, cellNum) + outerBar(cellWidth, cellNum)
