fun main() {
  fun part1(input: List<String>): Int {
    return input
      .mapNotNull { it.toIntOrNull() }
      .windowed(2)
      .count { it.first() < it.last() }
  }

  fun part2(input: List<String>): Int {
    return input
      .asSequence()
      .mapNotNull { it.toIntOrNull() }
      .windowed(3)
      .map { it.sum() }
      .windowed(2)
      .count { it.first() < it.last() }
  }

  println(part1(readInputAsLines("Day01.txt")))
  println(part2(readInputAsLines("Day01.txt")))
}