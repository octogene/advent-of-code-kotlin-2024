import kotlin.math.abs

fun main() {

    fun isSafe(levels: List<Int>): Boolean {
        val isIncreasing = levels[0] < levels[1]
        var safe = false
        if (!isIncreasing) {
            for (i in 0..<levels.lastIndex) {
                if (!(levels[i] > levels[i + 1] && levels[i] - levels[i + 1] <= 3)) {
                    safe = false
                    break
                } else {
                    safe = true
                }
            }
        } else {
            for (i in 0..<levels.lastIndex) {
                if (!(levels[i] < levels[i + 1] && levels[i + 1] - levels[i] <= 3)) {
                    safe = false
                    break
                } else {
                    safe = true
                }
            }
        }
        return safe
    }

    fun part1(input: List<String>): Int {
        val reports =
            input.map { line -> line.split("\\s+".toRegex()).map { it.toInt() } }
        return reports.map { levels -> if (isSafe(levels)) 1 else 0 }.sum()
    }

    fun part2(input: List<String>): Int {
        val reports =
            input.map { line -> line.split("\\s+".toRegex()).map { it.toInt() } }
        var count = 0
        reports.forEach { levels ->
            val isSafe = isSafe(levels)
            if (isSafe) {
                count++
            } else {
                for (i in levels.indices) {
                    if(isSafe(levels.mapIndexedNotNull { index, v -> if (index == i) null else v })) {
                        count++
                        break
                    }
                }
            }
        }
        return count
    }

    // Or read a large test input from the `src/Day02_test.txt` file:
    val testInput = readInput("Day02_test")
    part1(testInput).println()
    check(part1(testInput) == 2)
    part2(testInput).println()
    check(part2(testInput) == 4)

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
