package days

@AdventOfCodePuzzle(
    name = "Cube Conundrum",
    url = "https://adventofcode.com/2023/day/2",
    date = Date(day = 2, year = 2023)
)
class Day2(input: List<String>) : Puzzle {

    val games = input.map { Game.from(it) }

    override fun partOne(): Int = games.filter { it.possible() }.sumOf { it.id }

    override fun partTwo(): Int = games.sumOf { it.multiple() }

    data class Game(val id: Int, val sets: Map<String, Int>) {

        fun possible(): Boolean = sets.run {
            getOrDefault("red", 0) <= 12 &&
                    getOrDefault("green", 0) <= 13 &&
                    getOrDefault("blue", 0) <= 14
        }

        fun multiple(): Int = sets.run {
            getOrDefault("red", 1) *
                    getOrDefault("green", 1) *
                    getOrDefault("blue", 1)
        }

        companion object {
            fun from(record: String) = Game(
                record.substringBefore(':').substringAfter("Game ").toInt(),
                record.substringAfter(": ").split("; ")
                    .map { it.split(", ").associate { it.split(" ").let { it[1] to it[0].toInt() } } }
                    .reduce { m1, m2 ->
                        (m1.asSequence() + m2.asSequence()).groupBy({ it.key }, { it.value })
                            .mapValues { it.value.max() }
                    }
            )
        }

    }

}
