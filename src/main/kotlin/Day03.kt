fun main() {
  fun part1(input: List<String>): Int {
    val rowsAsColumns = input.rowsToColumns()
    val gamma = rowsAsColumns
      .map { row -> row.groupingBy { it }.eachCount().maxByOrNull { it.value }!!.key }
      .joinToString("")
    val epsilon = rowsAsColumns
      .map { row -> row.groupingBy { it }.eachCount().minByOrNull { it.value }!!.key }
      .joinToString("")

    return gamma.toInt(2) * epsilon.toInt(2)
  }

  fun part2(input: List<String>): Int {
    val oxygen = getSingleMatchingBinaryString(input) { bitCount: Map<Int, Int> ->
      if (bitCount.getOrDefault(0, 0) > bitCount.getOrDefault(1, 0)) 0 else 1
    }
    val co2 = getSingleMatchingBinaryString(input) { bitCount: Map<Int, Int> ->
      if (bitCount.getOrDefault(0, 0) > bitCount.getOrDefault(1, 0)) 1 else 0
    }

    return oxygen.toInt(2) * co2.toInt(2)
  }

  println(part1(readInputAsLines("Day03.txt")))
  println(part2(readInputAsLines("Day03.txt")))
}

fun List<String>.rowsToColumns(): List<List<Int>> {
  return mutableListOf<List<Int>>().also { resultList ->
    for (i in 0 until minOf { it.length }) {
      resultList.add(map { it[i].toString().toInt() })
    }
  }
}

fun getSingleMatchingBinaryString(list: List<String>, index: Int = 0, bitCriteria: (Map<Int, Int>) -> Int): String {
  val rowsAsLines = list.rowsToColumns()
  val bitToFilter = rowsAsLines[index].groupingBy { it }.eachCount().let { bitCriteria(it) }
  val filteredList = list.filter { it[index].toString().toInt() == bitToFilter }

  return if (filteredList.size == 1) filteredList.first()
  else getSingleMatchingBinaryString(filteredList, index + 1, bitCriteria)
}