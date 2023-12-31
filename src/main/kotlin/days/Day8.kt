package days

@AdventOfCodePuzzle(
    name = "Haunted Wasteland",
    url = "https://adventofcode.com/2023/day/8",
    date = Date(day = 8, year = 2023)
)
class Day8(input: List<String>) : Puzzle {

    private val instructions: String = input.first()
    private val routeFromTo: Map<String, Pair<String, String>> = input.drop(2).associate {
        val (from, to) = it.split(" = ")
        val pair = to.subSequence(1, to.lastIndex).split(", ").let { it[0] to it[1] }
        from to pair
    }

    override fun partOne(): Int = steps("AAA")

    override fun partTwo(): Long = routeFromTo
        .keys.filter { it.endsWith('A') }
        .map { steps(it).toLong() }
        .reduce { a, b -> lcm(a, b) }

    private fun steps(from: String) = instructions().runningFold(from) { position, direction ->
        when (direction) {
            'L' -> routeFromTo.getValue(position).first
            'R' -> routeFromTo.getValue(position).second
            else -> error("Should not happen: $direction")
        }
    }.takeWhile { !it.endsWith('Z') }.count()

    private fun instructions() = sequence {
        while (true)
            instructions.forEach { yield(it) }
    }

}
