package days

@AdventOfCodePuzzle(
    name = "Wait For It",
    url = "https://adventofcode.com/2023/day/6",
    date = Date(day = 6, year = 2023)
)
class Day6(input: List<String>) : Puzzle {

    private val times = input[0].substringAfter("Time:").split("""\s+""".toRegex()).filter { it.isNotBlank() }.map { it.toLong() }
    private val distances = input[1].substringAfter("Distance:").split("""\s+""".toRegex()).filter { it.isNotBlank() }.map { it.toLong() }
    private val records = times.zip(distances).map { (time, distance) -> Record(time, distance) }

    override fun partOne(): Int = records.map { it.waysToWins }.reduce { a, b -> a * b }

    override fun partTwo(): Int = records
        .reduce { acc, record -> Record("${acc.time}${record.time}".toLong(), "${acc.distance}${record.distance}".toLong()) }
        .waysToWins

    data class Record(val time: Long, val distance: Long) {
        val waysToWins: Int
            get() = (1..time).count { hold -> (time - hold) * hold > distance }
    }

}