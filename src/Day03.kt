fun main() {
    val mulInstructionRegex = "mul\\((\\d+),(\\d+)\\)".toRegex()
    val toggledMulInstructionRegex = "do\\(\\)|don't\\(\\)|mul\\((\\d+),(\\d+)\\)".toRegex()

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            mulInstructionRegex.findAll(line).sumOf { match ->
                val (_, a, b) = match.groupValues
                a.toInt() * b.toInt()
            }
        }
    }

    fun part2(input: List<String>): Int {
        var active = true
        var sum = 0
        input.forEach { line ->
            toggledMulInstructionRegex.findAll(line).forEach { match ->
                when {
                    match.value.startsWith("don") -> {
                        active = false
                    }
                    match.value.startsWith("do") -> {
                        active = true
                    }
                    else -> {
                        if (active) {
                            val (_, a, b) = match.groupValues
                            sum += a.toInt() * b.toInt()
                        }
                    }
                }
            }
        }
        return sum
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    val testInput2 = readInput("Day03_test2")
    check(part1(testInput) == 161)
    check(part2(testInput2) == 48)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
