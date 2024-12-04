fun main() {
    fun part1(input: List<String>): Int {
        val xmas = "XMAS"
        var count = 0
        input.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, char ->
                if (char == 'X') {
                    // Horizontal
                    if (charIndex + 3 < line.length) {
                        if (line.substring(charIndex, charIndex + 4) == xmas) {
                            count++
                        }
                    }
                    if (charIndex - 3 >= 0) {
                        if (line.substring(charIndex - 3, charIndex + 1) == xmas.reversed()) {
                            count++
                        }
                    }
                    // Vertical
                    if (lineIndex + 3 < input.size) {
                        var equals = false
                        for (i in 1..3) {
                            if (input[lineIndex + i][charIndex] == xmas[i]) {
                                equals = true
                            } else {
                                equals = false
                                break
                            }
                        }
                        if (equals) {
                            count++
                        }
                    }
                    if (lineIndex - 3 >= 0) {
                        var equals = false
                        for (i in 1..3) {
                            if (input[lineIndex - i][charIndex] == xmas[i]) {
                                equals = true
                            } else {
                                equals = false
                                break
                            }
                        }
                        if (equals) {
                            count++
                        }
                    }
                    // Diagonal
                    if (lineIndex + 3 < input.size && charIndex + 3 < line.length) {
                        var equals = false
                        for (i in 1..3) {
                            if (input[lineIndex + i][charIndex + i] == xmas[i]) {
                                equals = true
                            } else {
                                equals = false
                                break
                            }
                        }
                        if (equals) {
                            count++
                        }
                    }
                    if (lineIndex - 3 >= 0 && charIndex - 3 >= 0) {
                        var equals = false
                        for (i in 1..3) {
                            if (input[lineIndex - i][charIndex - i] == xmas[i]) {
                                equals = true
                            } else {
                                equals = false
                                break
                            }
                        }
                        if (equals) {
                            count++
                        }
                    }
                    if (lineIndex - 3 >= 0 && charIndex + 3 < line.length) {
                        var equals = false
                        for (i in 1..3) {
                            if (input[lineIndex - i][charIndex + i] == xmas[i]) {
                                equals = true
                            } else {
                                equals = false
                                break
                            }
                        }
                        if (equals) {
                            count++
                        }
                    }
                    if (lineIndex + 3 < input.size && charIndex - 3 >= 0) {
                        var equals = false
                        for (i in 1..3) {
                            if (input[lineIndex + i][charIndex - i] == xmas[i]) {
                                equals = true
                            } else {
                                equals = false
                                break
                            }
                        }
                        if (equals) {
                            count++
                        }
                    }
                }
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        var count = 0
        input.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, char ->
                if (char == 'A' &&
                    lineIndex + 1 < input.size &&
                    lineIndex - 1 >= 0 &&
                    charIndex + 1 < line.length &&
                    charIndex - 1 >= 0
                ) {
                    val ldiag = "${input[lineIndex - 1][charIndex - 1]}$char${input[lineIndex + 1][charIndex + 1]}"
                    val rdiag = "${input[lineIndex - 1][charIndex + 1]}$char${input[lineIndex + 1][charIndex - 1]}"
                    if ((ldiag == "MAS" || ldiag == "SAM") && (rdiag == "MAS" || rdiag == "SAM")) {
                        count++
                    }
                }
            }
        }
        return count
    }

    // Or read a large test input from the `src/Day04_test.txt` file:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 18)
    check(part2(testInput) == 9)

    // Read the input from the `src/Day04.txt` file.
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
