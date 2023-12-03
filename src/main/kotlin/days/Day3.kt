package days

import java.util.regex.Pattern

@AdventOfCodePuzzle(
    name = "Gear Ratios",
    url = "https://adventofcode.com/2023/day/3",
    date = Date(day = 3, year = 2023)
)
class Day3(val schematic: List<String>) : Puzzle {

    private val numbers = """\d+""".toRegex()

    override fun partOne(): Int {
        return schematic.mapIndexed() { ix, line ->
            val matchResult = numbers.findAll(line)
            matchResult.map {
                println("$ix,${it.range} ${it.value}")
                it.value.toInt()
            }.sum()
        }
            .sum()
    }

    override fun partTwo(): Int = 0

}
