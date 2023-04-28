package model

import model.Field
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers.*

class FieldTest extends AnyWordSpec:
  val player1: Player = Player(
    "Your Name",
    Hand(List.fill(5)(Card.emptyCard)),
    FightField(List.fill(5)(Card.emptyCard))
  )
  val player2: Player = Player(
    "Your Name",
    Hand(List.fill(5)(Card.emptyCard)),
    FightField(List.fill(5)(Card.emptyCard))
  )

  "Game" should {
    val field = Field(
      6,
      1,
      40,
      player1,
      player2
    )
    val field1 = Field(
      4,
      1,
      40,
      player1,
      player2
    )
    val eol = sys.props("line.separator")

    // "have an inner bar as String of form '+   +---+---+'" in {
    //  field.innerBar() should be("+   +---+---+" + eol)
    // }
    "have an outer bar as String of form '+---+---+---+---+'" in {
      field.outerBar() should be("+---+---+---+---+" + eol)
    }
    "have a scalable outer bar" in {
      field.outerBar(1, 1) should be("+-+-+" + eol)
      field.outerBar(1, 2) should be("+-+-+-+" + eol)
      field.outerBar(2, 1) should be("+--+--+" + eol)
    }
    "have a scalable outerOuter bar" in {
      field.outerOuterBar(1, 1) should be("+---+" + eol)
      field.outerOuterBar(1, 2) should be("+-----+" + eol)
      field.outerOuterBar(2, 1) should be("+-----+" + eol)
    }
    // "have a otherPlayerRow" in {
    //   field.otherPlayerRow(1, 1, field1.fightField, 1) should be}

    /*"have a scalable inner bar" in {
      field.innerBar(1, 1) should be("+ +" + eol)
      field.innerBar(1, 2) should be("+ +-+" + eol)
      field.innerBar(2, 1) should be("+  +" + eol)
    }*/
    // "have cells as String of form '|   |   |   |'" in {
    //   field.cells() should be("|   |   |   |" + eol + "|   |   |   |" + eol)
    // }
    // "have scalable cells" in {
    //   field1.cells() should be("|   |   |   |" + eol + "|   |   |   |" + eol)
    // }
    // "have LPStatsCells as String of form '|           |'" in {
    //   field.LPStatsCell() should be("|           |" + eol)
    // }
    // "have scalable LPStatsCells" in {
    //   field1.LPStatsCell(1, 1) should be("| |" + eol)
    //   field1.LPStatsCell(1, 2) should be("|  |" + eol)
    //   field1.LPStatsCell(2, 1) should be("|   |" + eol)
    // }
    // "have LPStatsCellsPlayer as String of form '| Player 1      |'" in {
    //   field.LPStatsCellPlayer() should be("| Player 1      |" + eol)
    // }
    // "have scalable LPStatsCellsPlayer" in {
    //   field1.LPStatsCellPlayer(1, 1, "1") should be("| Player 1|" + eol)
    //   field1.LPStatsCellPlayer(1, 2, "1") should be("| Player 1 |" + eol)
    //   field1.LPStatsCellPlayer(2, 1, "1") should be("| Player 1|" + eol)
    //   field1.LPStatsCellPlayer(1, 1, "2") should be("| Player 2|" + eol)
    //   field1.LPStatsCellPlayer(1, 2, "2") should be("| Player 2 |" + eol)
    //   field1.LPStatsCellPlayer(2, 1, "2") should be("| Player 2|" + eol)
    // }
    // "have LPStatsCellsOtherPlayer as String of form '| Player 2      |'" in {
    //   field.LPStatsCellOtherPlayer() should be("| Player 2      |" + eol)
    // }
    // "have scalable LPStatsCellsOtherPlayer" in {
    //   field1.LPStatsCellOtherPlayer(1, 1, "1") should be("| Player 2|" + eol)
    //   field1.LPStatsCellOtherPlayer(1, 2, "1") should be("| Player 2 |" + eol)
    //   field1.LPStatsCellOtherPlayer(2, 1, "1") should be("| Player 2|" + eol)
    //   field1.LPStatsCellOtherPlayer(1, 1, "2") should be("| Player 1|" + eol)
    //   field1.LPStatsCellOtherPlayer(1, 2, "2") should be("| Player 1 |" + eol)
    //   field1.LPStatsCellOtherPlayer(2, 1, "2") should be("| Player 1|" + eol)
    // }
    // "have default mesh" in {
    //   field.mesh() should be(
    //     ("+---+---+---+" + eol + "| Player 2      |" + eol + "+---+---+---+" + eol) + ("|   |   |   |" + eol) * 2 + ("+   +---+---+" + eol) * 2
    //       + ("|   |   |   |" + eol) * 2 + "+   +---+---+" + eol + ("|   |   |   |" + eol) * 2 + ("+---+---+---+" + eol + "| Player 1      |" + eol + "+---+---+---+" + eol)
    //   )
    // }

    // "have a mesh in the form +-+  | |+-+" in {
    //   field1.mesh() should be(
    //     "+-+" + eol + "| Player 2|" + eol + "+-+" + eol + "| |" + eol + ("+ +" + eol) * 2 + "| |" + eol + "+ +" + eol + "| |" + eol + "+-+" + eol + "| Player 1|" + eol + "+-+" + eol
    //   )
    // }
  }
