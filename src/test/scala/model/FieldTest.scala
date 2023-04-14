package test.scala.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import main.scala.model.*

class FieldTest extends AnyWordSpec:

  "Game" should {
    val field = new Field(3)
    val field1 = new Field(1)
    val eol = sys.props("line.separator")

    "have a bar as String of form '+---+---+---+'" in {
      field.bar() should be("+---+---+---+" + eol)
    }
    "have a scalable bar" in {
      field.bar(1, 1) should be("+-+" + eol)
      field.bar(1, 2) should be("+-+-+" + eol)
      field.bar(2, 1) should be("+--+" + eol)
    }
    "have cells as String of form '|   |   |   |'" in {
      field.cells() should be("|   |   |   |" + eol + "|   |   |   |" + eol)
    }
    "have scalable cells" in {
      field1.cells() should be("|   |   |   |" + eol + "|   |   |   |" + eol)
    }
    "have default mesh" in {
      field.mesh() should be(
        ("+---+---+---+" + eol + "|   |   |   |" + eol + "|   |   |   |" + eol) * 3 + "+---+---+---+" + eol
      )
    }

    "have a mesh in the form +-+  | |+-+" in {
      field1.mesh() should be("+-+" + eol + "| |" + eol + "+-+" + eol)
    }
  }
