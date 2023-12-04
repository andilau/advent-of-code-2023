package days

import kotlin.math.pow

@AdventOfCodePuzzle(
    name = "Scratchcards",
    url = "https://adventofcode.com/2023/day/4",
    date = Date(day = 4, year = 2023)
)
class Day4(private val input: List<String>) : Puzzle {

    private val scratchcards = input.map { Scratchcard.from(it) }

    override fun partOne(): Int = scratchcards.sumOf { it.points }

    override fun partTwo(): Int = scratchcards.map { it.matching }
        .foldIndexed(List(scratchcards.size) { _ -> 1 }.toMutableList()) { ix, acc, won ->
            (ix + 1..ix + won).forEach { acc[it] += acc[ix] }
            acc
        }
        .sum()


    data class Scratchcard(val id: Int, val picked: Set<Int>, val winning: Set<Int>) {

        val matching: Int
            get() = (picked intersect winning).count()

        val points: Int
            get() = 2.toDouble().pow(matching.toDouble() - 1).toInt()

        companion object {
            fun from(line: String): Scratchcard {
                val id: Int = line.substringBefore(":").substringAfter("Card ").trim().toInt()
                val picked: Set<Int> =
                    line.substringAfter(":").substringBefore("|").split(' ').filter { it.isNotBlank() }
                        .map(String::toInt).toSet()
                val winning: Set<Int> =
                    line.substringAfter("|").split(' ').filter { it.isNotBlank() }.map(String::toInt).toSet()
                return Scratchcard(id, picked, winning)
            }

        }
    }
}
