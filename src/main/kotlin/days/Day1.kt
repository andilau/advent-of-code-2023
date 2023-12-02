package days

@AdventOfCodePuzzle(
    name = "Trebuchet?!",
    url = "https://adventofcode.com/2023/day/1",
    date = Date(day = 1, year = 2023)
)
class Day1(private val input: List<String>) : Puzzle {
    override fun partOne():Int {
        return input.map { line-> Pair(line.first { it.isDigit() }, line.last { it.isDigit() }) }
            .map { p -> "" + p.first + p.second }
            .map { va -> va.toInt() }.sum()

    }

    override fun partTwo() = 0
}