import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class GameFinalSpec extends AnyWordSpec:
  "Game" should {
    "have a bar as String of form '+---+---+---+'" in {
      bar() should be("+---+---+---+" + eol)
    }
    "have a scalable bar" in {
      bar(1, 1) should be("+-+" + eol)
      bar(1, 2) should be("+-+-+" + eol)
      bar(2, 1) should be("+--+" + eol)
    }
    "have cells as String of form '|   |   |   |'" in {
      cells() should be("|   |   |   |" + eol)
    }
    "have scalable cells" in {
      cells(1, 1) should be("| |" + eol)
      cells(1, 2) should be("| | |" + eol)
      cells(2, 1) should be("|  |" + eol)
    }
    "have a mesh in the form +-+  | |+-+" in {
      mesh(1, 1) should be("+-+" + eol + "| |" + eol + "+-+" + eol)
    }
  }
