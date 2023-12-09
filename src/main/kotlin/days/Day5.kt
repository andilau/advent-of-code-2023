package days

@AdventOfCodePuzzle(
    name = "If You Give A Seed A Fertilizer",
    url = "https://adventofcode.com/2023/day/5",
    date = Date(day = 5, year = 2023)
)
class Day5(input: List<String>) : Puzzle {

    private val seeds = input.first().substringAfter("seeds: ").split(' ').map { it.toLong() }
    private val seedRanges = seeds.windowed(2, 2).map { (start, length) -> start..<start + length }
    private val rangeMaps: MutableList<MutableList<RangeMap>> = input.drop(2)
        .filter(String::isNotBlank)
        .fold(mutableListOf()) { acc, line ->
            if (line.first().isLetter())
                acc.add(mutableListOf())
            else
                acc.last().add(RangeMap.from(line))
            acc
        }
    private val reversed = rangeMaps.reversed().map { ranges -> ranges.map { RangeMap(it.dest, it.source) } }

    override fun partOne(): Long {
        val minOf = seeds.minOf { seed ->
            rangeMaps.fold(seed) { pos, ranges ->
                ranges.firstOrNull { pos in it }?.map(pos) ?: pos
            }
        }
        return minOf
    }


    override fun partTwo(): Long = generateSequence(0L) { it + 1 }
        .first { position ->
            val start = reversed.fold(position) { pos, ranges ->
                ranges.firstOrNull { pos in it }?.map(pos) ?: pos
            }
            seedRanges.any { start in it }
        }

    data class RangeMap(val source: LongRange, val dest: LongRange) {

        fun map(value: Long): Long = value - source.first + dest.first

        operator fun contains(value: Long) = value in source

        override fun toString(): String = "RangeMap($source -> $dest)"

        companion object {
            fun from(line: String): RangeMap = line
                .split(' ').map { it.toLong() }
                .let { (to, from, length) ->
                    RangeMap(
                        from..<from + length,
                        to..<to + length
                    )
                }
        }

    }

}