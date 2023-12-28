package days

@AdventOfCodePuzzle(
    name = "Point of Incidence",
    url = "https://adventofcode.com/2023/day/13",
    date = Date(day = 13, year = 2023)
)
class Day13(input: List<String>) : Puzzle {

    private val patterns: MutableList<MutableList<String>> = input.fold(mutableListOf(mutableListOf<String>())) { acc, line ->
        if (line.isBlank()) {
            acc.add(mutableListOf())
        } else {
            acc.last().add(line)
        }
        acc
    }

    override fun partOne(): Int =
        patterns.sumOf { verticalMirror(it) * 100 + horizontalMirror(it) }

    override fun partTwo(): Int =
        patterns.sumOf { verticalMirror(it, 1) * 100 + horizontalMirror(it, 1) }

    private fun verticalMirror(pattern: List<String>, diff :Int = 0): Int {
        return (1..pattern.lastIndex).firstNotNullOfOrNull { iy ->
            val top = pattern.subList(0, iy)
            val bottom = pattern.subList(iy, pattern.lastIndex + 1)

            if (top.reversed().zip(bottom)
                    .sumOf { pair -> pair.first.indices.count { pair.first[it] != pair.second[it] } } == diff
            )
                iy else null
        } ?: 0
    }

    private fun horizontalMirror(pattern: List<String>, diff: Int = 0): Int {
        return (1..pattern.first().lastIndex).firstNotNullOfOrNull { ix ->
            val left = (0..<ix).map { x -> pattern.map { line -> line[x] }.joinToString("") }
            val right = (ix..pattern.first().lastIndex).map { x -> pattern.map { line -> line[x] }.joinToString("") }

            if (left.reversed().zip(right)
                    .sumOf { pair -> pair.first.indices.count { pair.first[it] != pair.second[it] } } == diff
            )
                ix else null
        } ?: 0

    }

}