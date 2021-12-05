fun main() {
  fun part1(input: List<String>): Int {
    var horiz = 0
    var depth = 0
    input.forEach {
      when (it.substringBeforeLast(" ")) {
        "forward" -> horiz += it.substringAfterLast(" ").toInt()
        "down" -> depth += it.substringAfterLast(" ").toInt()
        "up" -> depth -= it.substringAfterLast(" ").toInt()
      }
    }
    return horiz * depth
  }

  fun part2(input: List<String>): Int {
    var horiz = 0
    var depth = 0
    var aim = 0
    input.forEach {
      when (it.substringBeforeLast(" ")) {
        "forward" -> {
          horiz += it.substringAfterLast(" ").toInt()
          depth += aim * it.substringAfterLast(" ").toInt()
        }
        "down" -> aim += it.substringAfterLast(" ").toInt()
        "up" -> aim -= it.substringAfterLast(" ").toInt()
      }
    }
    return horiz * depth
  }

  println(part1(readInputAsLines("Day02.txt")))
  println(part2(readInputAsLines("Day02.txt")))
}