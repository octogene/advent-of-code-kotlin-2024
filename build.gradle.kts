import java.time.LocalDateTime

plugins {
    kotlin("jvm") version "2.0.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "8.11"
    }
}

tasks.register("createANewDay") {
    val day = LocalDateTime.now().dayOfMonth.toString().padStart(2, '0')
    val template = """
        fun main() {
            fun part1(input: List<String>): Int {
                return 0
            }

            fun part2(input: List<String>): Int {
                return 0
            }

            // Or read a large test input from the `src/Day${day}_test.txt` file:
            val testInput = readInput("Day${day}_test")
            check(part1(testInput) == -1)
            check(part2(testInput) == -1)

            // Read the input from the `src/Day$day.txt` file.
            val input = readInput("Day$day")
            part1(input).println()
            part2(input).println()
        }
    """.trimIndent()
    val srcDir = layout.projectDirectory.dir("src")
    srcDir.files(
        "Day$day.kt",
        "Day${day}_test.txt",
        "Day$day.txt"
    ).files.forEach {
        if (!it.exists()) { it.createNewFile() }
    }
    val sourceFile = srcDir.file("Day$day.kt").asFile
    if(!sourceFile.exists()) { sourceFile.writeText(template) }
}
