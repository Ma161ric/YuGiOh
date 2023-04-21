package model

import model.Field
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class FieldTest extends AnyWordSpec:

  "Game" should {
    val field = Field(3)
    val field1 = Field(1)
    val eol = sys.props("line.separator")


    "have an inner bar as String of form '+   +---+---+'" in {
      field.innerBar() should be("+   +---+---+" + eol)
    }
    "have an outer bar as String of form '+---+---+---+'" in {
      field.outerBar() should be("+---+---+---+" + eol)
    }
    "have a scalable outer bar" in {
      field.outerBar(1, 1) should be("+-+" + eol)
      field.outerBar(1, 2) should be("+-+-+" + eol)
      field.outerBar(2, 1) should be("+--+" + eol)
    }
    "have a scalable inner bar" in {
      field.innerBar(1, 1) should be("+ +" + eol)
      field.innerBar(1, 2) should be("+ +-+" + eol)
      field.innerBar(2, 1) should be("+  +" + eol)
    }
    "have cells as String of form '|   |   |   |'" in {
      field.cells() should be("|   |   |   |" + eol + "|   |   |   |" + eol)
    }
    "have scalable cells" in {
      field1.cells() should be("|   |   |   |" + eol + "|   |   |   |" + eol)
    }
    "have LPStatsCells as String of form '|           |'" in {
      field.LPStatsCell() should be("|           |" + eol)
    }
    "have scalable LPStatsCells" in {
      field1.LPStatsCell(1,1) should be("| |" + eol)
      field1.LPStatsCell(1,2) should be("|  |" + eol)
      field1.LPStatsCell(2,1) should be("|   |" + eol)

    }
    "have default mesh" in {
      field.mesh() should be(
        ("+---+---+---+" + eol + "|           |" + eol + "+---+---+---+" + eol) + ("|   |   |   |" + eol) * 2 + ("+   +---+---+" + eol)*2
        + ("|   |   |   |" + eol) *2 + "+   +---+---+" + eol + ("|   |   |   |" + eol) *2  + ("+---+---+---+" + eol + "|           |" + eol + "+---+---+---+" + eol)
      )
    }

    "have a mesh in the form +-+  | |+-+" in {
      field1.mesh() should be("+-+" + eol + "| |" + eol + "+-+" + eol + "| |" + eol + ("+ +" + eol)*2 + "| |" + eol + "+ +" + eol + "| |" + eol + "+-+" + eol + "| |" + eol + "+-+" + eol)
    }
  }
