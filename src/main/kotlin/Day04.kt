fun main() {
  fun part1(input: List<String>): Int {
    val drawingNumbers = input.toDrawingNumbers()
    val boards = input.toBingoBoards()

    drawingNumbers.forEach { number ->
      boards.forEach { bingoBoard ->
        if (bingoBoard.markNumber(number)) return bingoBoard.sumOfUnMarked() * number
      }
    }

    throw IllegalStateException("No board won.")
  }

  fun part2(input: List<String>): Int {
    val drawingNumbers = input.toDrawingNumbers()
    val boards = input.toBingoBoards()


    var finalBoardScore = -1
    drawingNumbers.forEach { number ->
      boards.forEach { bingoBoard ->
        if (bingoBoard.markNumber(number)) {
          finalBoardScore = bingoBoard.sumOfUnMarked() * number
        }
      }
    }

    return if (finalBoardScore >= 0) finalBoardScore else throw IllegalStateException("No board won.")
  }

  println(part1(readInputAsLines("Day04.txt")))
  println(part2(readInputAsLines("Day04.txt")))
}

fun List<String>.toDrawingNumbers(): List<Int> = first().split(",").map { it.toInt() }

fun List<String>.toBingoBoards(): List<BingoBoard> {
  return drop(2)
    .windowed(5, 6)
    .map { BingoBoard(it.map { row -> row.windowed(2, 3).map { number -> number.trim().toInt() } }) }
}

class BingoBoard(numbers: List<List<Int>>) {
  private class BingoNumber(val number: Int, var marked: Boolean)

  private var hasWon = false
  private val rows: List<List<BingoNumber>> = numbers.map { it.map { number -> BingoNumber(number, false) } }

  fun markNumber(number: Int): Boolean {
    return if (!hasWon) {
      rows.forEach { it.firstOrNull { bingoNumber -> bingoNumber.number == number }?.marked = true }

      hasWon = rows.any { row -> row.all { bingoNumber -> bingoNumber.marked } } ||
        (0 until rows.first().size).any { index -> rows.all { it[index].marked } }
      hasWon
    } else {
      false
    }
  }

  fun sumOfUnMarked() = rows.sumOf { row -> row.filterNot { it.marked }.sumOf { it.number } }
}

