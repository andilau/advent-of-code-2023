package days

@AdventOfCodePuzzle(
    name = "Trebuchet?!",
    url = "https://adventofcode.com/2023/day/1",
    date = Date(day = 1, year = 2023)
)
class Day1(private val input: List<String>) : Puzzle {
    override fun partOne(): Int = input
        .map { line -> Pair(line.firstDigit, line.lastDigit) }
        .sumOf { it.first * 10 + it.second }

    override fun partTwo(): Int = input
        .map { line -> Pair(line.firstDigitOrNumber, line.lastDigitOrNumber) }
        .sumOf { it.first * 10 + it.second }

    private val String.firstDigit get() = first { it.isDigit() }.digitToInt()

    private val String.lastDigit get() = last { it.isDigit() }.digitToInt()

    private val String.firstDigitOrNumber
        get() = windowedSequence(this.length, 1, true)
            .firstNotNullOfOrNull { substring ->
                when {
                    substring.first().isDigit() -> substring.first().digitToInt()
                    else -> letterToDigit.keys.firstOrNull() { substring.startsWith(it) }?.let { letterToDigit[it] }
                }
            } ?: error("Contains no number: $this")

    private val String.lastDigitOrNumber
        get() = indices.asSequence()
            .map { this.substring(0..<length - it) }
            .firstNotNullOfOrNull { substring ->
                when {
                    substring.last().isDigit() -> substring.last().digitToInt()
                    else -> letterToDigit.keys.firstOrNull() { substring.endsWith(it) }?.let { letterToDigit[it] }
                }
            } ?: error("Contains no number: $this")

    private val letterToDigit = listOf(
        "one",
        "two",
        "three",
        "four",
        "five",
        "six",
        "seven",
        "eight",
        "nine"
    ).mapIndexed { index, s -> s to index + 1 }.toMap()
}
