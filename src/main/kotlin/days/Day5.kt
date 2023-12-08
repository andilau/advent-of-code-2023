package days

@AdventOfCodePuzzle(
    name = "If You Give A Seed A Fertilizer",
    url = "https://adventofcode.com/2023/day/5",
    date = Date(day = 5, year = 2023)
)
class Day5(input: List<String>) : Puzzle {

    private val seeds = input.first().substringAfter("seeds: ").split(' ').map { it.toInt() }
    private val seedToSoilMap = input.dropWhile { !it.startsWith("seed-to-soil map:") }
        .takeWhile { it.isNotBlank() }
        .drop(1)
        .map { Range.from(it) }

    override fun partOne(): Int {
        println("seeds = ${seeds}")
        println("seedToSoilMap = ${seedToSoilMap}")

        val range = Range(50, 98, 2)
        val range2 = Range(52, 50, 48)
        (0..100).forEach { println("$it -> " + range.map(range2.map(it))) }
        return 0
    }

    override fun partTwo(): Int {
        return 0
    }

    data class Range(val destination: Int, val source: Int, val length: Int) {
        // 50 98 2
        // 98 -> 50
        // 99 -> 51

        fun map(input: Int): Int {
            if (input in source..<source + length) return input - source + destination
            else return input
        }

        companion object {
            fun from(line: String): Range {
                return line.split(' ').map { it.toInt() }.let { Range(it[0], it[1], it[2]) }
            }
        }
    }
}
