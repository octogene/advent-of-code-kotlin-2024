import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (left, right) =
            input.flatMap { it.split("\\s+".toRegex()) }
                .withIndex()
                .partition { it.index % 2 == 0 }
        val sortedLeft = left.map { it.value.toInt() }.sortedBy { it }
        val sortedRight = right.map {it.value.toInt() }.sortedBy { it }
        return sortedLeft.zip(sortedRight) { left, right -> abs(left - right) }.sum()
    }

    fun part2(input: List<String>): Int {
        val (left, right) =
            input.flatMap { line -> line.split("\\s+".toRegex()).map { it.toInt() } }
                .withIndex()
                .partition { it.index % 2 == 0 }
        val counts = right.groupingBy { it.value }.eachCount()
        return left.sumOf { it.value * counts.getOrDefault(it.value, 0) }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
