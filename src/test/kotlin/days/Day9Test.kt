package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 9")
class Day9Test {

    val historyValues = """
        0 3 6 9 12 15
        1 3 6 10 15 21
        10 13 16 21 30 45
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun shouldReturnSumOfTheExtrapolatedValues() {
            assertThat(Day9(historyValues).partOne()).isEqualTo(114)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun shouldReturnSumOfTheExtrapolatedPreviousValues() {
            assertThat(Day9(historyValues).partTwo()).isEqualTo(2)
        }

    }
}