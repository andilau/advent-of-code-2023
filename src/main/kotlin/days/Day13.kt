package days

@AdventOfCodePuzzle(
    name = "Point of Incidence",
    url = "https://adventofcode.com/2023/day/13",
    date = Date(day = 13, year = 2023)
)
class Day13(input: List<String>) : Puzzle {

    private val patterns = input.fold(mutableListOf(mutableListOf<String>())) { acc, line ->
        if (line.isBlank()) {
            acc.add(mutableListOf())
        } else {
            acc.last().add(line)
        }
        acc
    }

    override fun partOne(): Int =
        patterns.sumOf { verticalMirror(it) * 100 + horizontalMirror(it) }

    private fun verticalMirror(pattern: List<String>): Int {
        return (1..pattern.lastIndex).firstNotNullOfOrNull { iy ->
            val top = pattern.subList(0, iy)
            val bottom = pattern.subList(iy, pattern.lastIndex + 1)

            if (top.reversed().zip(bottom).all { it.first == it.second })
                iy else null
        } ?: 0
    }

    private fun horizontalMirror(pattern: List<String>): Int {
        return (1..pattern.first().lastIndex).firstNotNullOfOrNull { ix ->
            val left = (0..<ix).map { x -> pattern.map { line -> line[x] }.joinToString("") }
            val right = (ix..pattern.first().lastIndex).map { x -> pattern.map { line -> line[x] }.joinToString("") }

            if (left.reversed().zip(right).all { it.first == it.second })
                ix else null
        } ?: 0

    }

    override fun partTwo(): Int = 0

}