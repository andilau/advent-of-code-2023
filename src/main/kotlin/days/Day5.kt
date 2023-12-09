package days

@AdventOfCodePuzzle(
    name = "If You Give A Seed A Fertilizer",
    url = "https://adventofcode.com/2023/day/5",
    date = Date(day = 5, year = 2023)
)
class Day5(input: List<String>) : Puzzle {

    private val seeds = input.first().substringAfter("seeds: ").split(' ').map { it.toLong() }
        .also { println("seeds = $it") }
    private val seedRanges = seeds.windowed(2, 2).map { (start, length) -> start..<start + length }
        .also { println("seeds = $it") }

    private val rangeMaps: MutableList<MutableList<RangeMap>> = input.drop(2)
        .filter(String::isNotBlank)
        .fold(mutableListOf<MutableList<RangeMap>>()) { acc, line ->
            if (line.first().isLetter())
                acc.add(mutableListOf())
            else
                acc.last().add(RangeMap.from(line))
            acc
        }

    override fun partOne(): Long {
        val minOf = seeds.minOf { seed ->
            rangeMaps.fold(seed) { pos, ranges ->
                ranges.firstOrNull { pos in it }?.map(pos) ?: pos
            }
        }
        return minOf
    }

    override fun partTwo(): Long {
        val reversed = rangeMaps.reversed().map { ranges -> ranges.map { RangeMap(it.dest, it.source) } }
        return generateSequence(0L) { it + 1 }.first { position ->
            val start = reversed.fold(position) { pos, ranges ->
                ranges.firstOrNull() { pos in it }?.map(pos) ?: pos
            }
            seedRanges.any { start in it }
        }
    }

    data class RangeMap(val source: LongRange, val dest: LongRange) {

        fun map(value: Long): Long {
            return if (value in source) value - source.first + dest.first
            else value
        }

        operator fun contains(value: Long) = value in source

        override fun toString(): String {
            return "RangeMap($source -> $dest)"
        }

        companion object {
            fun from(line: String): RangeMap {
                return line.split(' ').map { it.toLong() }
                    .let { (to, from, length) ->
                        RangeMap(
                            from..<from + length,
                            to..<to + length
                        )
                    }
            }
        }
    }

}