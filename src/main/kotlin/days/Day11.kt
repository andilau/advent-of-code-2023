package days

@AdventOfCodePuzzle(
    name = "Cosmic Expansion",
    url = "https://adventofcode.com/2023/day/11",
    date = Date(day = 11, year = 2023)
)
class Day11(input: List<String>) : Puzzle {

    private val galaxies = input.extractOnly('#').toList()

    override fun partOne(): Int {
        return galaxies
            .expandBy()
            .uniquePairs()
            .sumOf { pair -> pair.first.distance(pair.second) }
    }

    override fun partTwo(): Long = galaxies
        .expandBy(1000_000)
        .uniquePairs()
        .sumOf { pair -> pair.first.distance(pair.second).toLong() }

    fun sumOfTheShortestPathsBetweenAllGalaxiesExpanded(times: Int): Long =
        galaxies
            .expandBy(times)
            .uniquePairs()
            .sumOf { pair -> pair.first.distance(pair.second).toLong() }

}

private fun List<Point>.expandBy(factor: Int = 2): List<Point> {
    val emptyCols = (0..maxOf { it.x }) - map { it.x }.toSet()
    val emptyRows = (0..maxOf { it.y }) - map { it.y }.toSet()
    val xFactors = (0..maxOf { it.x }).map { col -> emptyCols.count { it < col } }
    val yFactors = (0..maxOf { it.y }).map { row -> emptyRows.count { it < row } }

    return map { (x, y) -> Point(x + xFactors[x] * (factor - 1), y + yFactors[y] * (factor - 1)) }
}


