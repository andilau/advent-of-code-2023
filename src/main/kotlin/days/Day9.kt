package days

@AdventOfCodePuzzle(
    name = "Mirage Maintenance",
    url = "https://adventofcode.com/2023/day/9",
    date = Date(day = 9, year = 2023)
)
class Day9(input: List<String>) : Puzzle {

    private val historyList = input.map { History.from(it) }

    override fun partOne(): Int = historyList.sumOf { it.next }

    override fun partTwo(): Int = historyList.sumOf { it.previous }

    data class History(val values: List<Int>) {

        val next: Int
            get() {
                lists.reversed().zipWithNext { a, b -> b += b.last() + a.last() }
                return lists.first().last()
            }

        val previous: Int
            get() {
                lists.reversed().zipWithNext { a, b -> b.add(0, b.first() - a.first()) }
                return lists.first().first()
            }

        private val lists: MutableList<MutableList<Int>> by lazy {
            val result = mutableListOf(values.toMutableList())
            while (result.last().toSet().count() != 1)
                result += result.last().zipWithNext().map { it.second - it.first }.toMutableList()
            result
        }

        companion object {
            fun from(line: String) = History(line.split(" ").map { it.toInt() })
        }

    }

}
