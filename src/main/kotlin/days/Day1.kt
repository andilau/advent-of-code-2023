package days

@AdventOfCodePuzzle(
    name = "Trebuchet?!",
    url = "https://adventofcode.com/2023/day/1",
    date = Date(day = 1, year = 2023)
)
class Day1(private val input: List<String>) : Puzzle {
    override fun partOne(): Int {
        return input
            .map { line -> Pair(line.first { it.isDigit() }, line.last { it.isDigit() }) }
            .map { p -> "" + p.first + p.second }
            .sumOf { va -> va.toInt() }
    }

    override fun partTwo(): Int {
        return input
            .asSequence()
            .map { line -> Pair(line.leftValidation(), line.rightVliadtion()) }
            .map { p -> "" + p.first + p.second }
            .map { va -> va.toInt() }.sum()

    }

    private val letterToDigit = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )

    private fun String.leftValidation(): Int {
        return windowedSequence(this.length, 1, true)
            .firstNotNullOfOrNull { substring ->
                when {
                    substring.first().isDigit() -> substring.first().digitToInt()
                    else -> letterToDigit.keys.firstOrNull() { substring.startsWith(it) }?.let { letterToDigit[it] }
                }
            }
            .also { print("L:$it ") }
            ?: error("Hää?")
    }

    private fun String.rightVliadtion(): Int {
        return indices.map { this.substring(0..<length - it) }
            .firstNotNullOfOrNull { substring ->
                when {
                    substring.last().isDigit() -> substring.last().digitToInt()
                    else -> letterToDigit.keys.firstOrNull() { substring.endsWith(it) }?.let { letterToDigit[it] }
                }
            }
            .also { println("R:$it") }
            ?: error("Hää?")
    }

}



