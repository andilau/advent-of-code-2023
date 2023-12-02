package days

@AdventOfCodePuzzle(
    name = "Cube Conundrum",
    url = "https://adventofcode.com/2023/day/2",
    date = Date(day = 2, year = 2023)
)
class Day2(private val input: List<String>) : Puzzle {
    override fun partOne(): Int = input.map { Game.from(it) }
        .filter { it.possible() }
        .sumOf { it.id }

    override fun partTwo(): Int = input.map { Game.from(it) }
        .sumOf { it.multiple() }

    data class Game(val id: Int, val sets: List<Map<String, Int>>) {

        fun possible(): Boolean {
            val reduce: Map<String, Int> = sets.reduce { m1, m2 ->
                (m1.asSequence() + m2.asSequence()).groupBy({ it.key }, { it.value }).mapValues { it.value.max() }
            }

            return reduce.getOrDefault("red", 0) <= 12 &&
                    reduce.getOrDefault("green", 0) <= 13 &&
                    reduce.getOrDefault("blue", 0) <= 14
        }

        fun multiple(): Int {
            val reduce: Map<String, Int> = sets.reduce { m1, m2 ->
                (m1.asSequence() + m2.asSequence()).groupBy({ it.key }, { it.value }).mapValues { it.value.max() }
            }
            println("reduce = ${reduce}")

            return reduce.getOrDefault("red", 1) *
                    reduce.getOrDefault("green", 1 ) *
                    reduce.getOrDefault("blue", 1)
        }


        companion object {
            fun from(record: String) = Game(
                record.substringBefore(':').substringAfter("Game ").toInt(),
                record.substringAfter(": ").split("; ")
                    .map { it.split(", ").associate { it.split(" ").let { it[1] to it[0].toInt() } } }
            )
        }
    }

}
