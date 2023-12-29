package days

@AdventOfCodePuzzle(
    name = "Point of Incidence",
    url = "https://adventofcode.com/2023/day/13",
    date = Date(day = 13, year = 2023)
)
class Day13(input: List<String>) : Puzzle {

    private val patterns: List<List<String>> = extractPatterns(input)

    override fun partOne(): Int =
        patterns.sumOf { pattern -> 100 * pattern.verticalMirror() + pattern.horizontalMirror() }

    override fun partTwo(): Int =
        patterns.sumOf { pattern -> 100 * pattern.verticalMirror(1) + pattern.horizontalMirror(1) }

    private fun extractPatterns(input: List<String>) =
        input.fold(mutableListOf(mutableListOf<String>())) { acc, line ->
            if (line.isBlank()) {
                acc.add(mutableListOf())
            } else {
                acc.last().add(line)
            }
            acc
        }

    private fun List<String>.verticalMirror(difference: Int = 0): Int =
        (1..lastIndex).firstNotNullOfOrNull { iy ->
            val top = subList(0, iy)
            val bottom = subList(iy, lastIndex + 1)

            top.reversed().zip(bottom)
                .sumOf { pair -> pair.first.indices.count { pair.first[it] != pair.second[it] } }
                .let { if (it == difference) iy else null }
        } ?: 0

    private fun List<String>.horizontalMirror(difference: Int = 0): Int =
        transpose().verticalMirror(difference)

    private fun List<String>.transpose(): List<String> =
        first().indices.map { x -> this.map { line -> line[x] }.joinToString("") }

}