package days

@AdventOfCodePuzzle(
    name = "Gear Ratios",
    url = "https://adventofcode.com/2023/day/3",
    date = Date(day = 3, year = 2023)
)
class Day3(private val schematic: List<String>) : Puzzle {

    private val numberRegex = """\d+""".toRegex()

    override fun partOne(): Int =
        schematic.flatMapIndexed() { y, line ->
            numberRegex.findAll(line).map {
                val isPartNumber: Boolean = schematic
                    .slice((y - 1).coerceAtLeast(0)..(y + 1).coerceAtMost(schematic.lastIndex))
                    .any { line ->
                        line.slice((it.range.first - 1).coerceAtLeast(0)..(it.range.last + 1).coerceAtMost(line.lastIndex))
                            .any { it !in "0123456789." }
                    }
                if (isPartNumber) it.value.toInt() else 0
            }
        }.sum()


    override fun partTwo(): Int =
        schematic.flatMapIndexed() { lineNumber, line ->
            numberRegex.findAll(line).flatMap { matchResult ->
                ((lineNumber - 1).coerceAtLeast(0)..(lineNumber + 1).coerceAtMost(schematic.lastIndex))
                    .map { IndexedValue(it, schematic[it]) }
                    .mapNotNull { (y, line) ->
                        ((matchResult.range.first - 1).coerceAtLeast(0)..(matchResult.range.last + 1).coerceAtMost(line.lastIndex))
                            .firstNotNullOfOrNull { x -> if (line[x] == '*') Pair(x, y) else null }
                            ?.let { it to matchResult.value.toInt() }
                    }.toList()
            }
        }
            .groupBy({ it.first }, { it.second })
            .filterValues { it.size == 2 }
            .values.sumOf { it[0] * it[1] }
}
