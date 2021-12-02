fun main() {
  fun part1(input: List<String>): Int {
    return input
      .mapNotNull { it.toIntOrNull() }
      .windowed(2)
      .count { it.first() < it.last() }
  }

  println(part1(readInputAsLines("Day01.txt")))
}