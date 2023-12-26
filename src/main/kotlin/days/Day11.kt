package days

@AdventOfCodePuzzle(
    name = "Cosmic Expansion",
    url = "https://adventofcode.com/2023/day/11",
    date = Date(day = 11, year = 2023)
)
class Day11(input: List<String>) : Puzzle {

    private val galaxies = input.extract('#').toList()

    override fun partOne(): Int {
        return galaxies
            .expand()
            .uniquePairs()
            .sumOf { pair -> pair.first.distance(pair.second) }
    }

    override fun partTwo(): Int = galaxies.count()

}

private fun List<Point>.expand(factor: Int = 1): List<Point> {
    val emptyX = (0..maxOf { it.x }) - map { it.x }.toSet()
    val emptyY = (0..maxOf { it.y }) - map { it.y }.toSet()
    val factorX = (0..maxOf { it.x }).map { col -> emptyX.count { it < col } }
    val factorY = (0..maxOf { it.y }).map { row -> emptyY.count { it < row } }

    return map { (x, y) -> Point(x + factorX[x], y + factorY[y] * factor) }
}

fun <E> List<E>.uniquePairs(): List<Pair<E, E>> =
    flatMapIndexed { leftIx: Int, left: E ->
        drop(leftIx + 1).map { right -> left to right }
    }
