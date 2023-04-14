package main.scala.model

case class Field(size: Int):
  val eol = sys.props("line.separator")
  def bar(cellWidth: Int = 3, cellNum: Int = 3) =
    ("+" + "-" * cellWidth) * cellNum + "+" + eol
  def cells(cellWidth: Int = 3, cellNum: Int = 3) =
    (("|" + " " * cellWidth) * cellNum + "|" + eol) * ((cellWidth / 3) + 1)

  def mesh(cellWidth: Int = size, cellNum: Int = size) =
    (bar(cellWidth, cellNum) + cells(cellWidth, cellNum)) * cellNum + bar(
      cellWidth,
      cellNum
    )
