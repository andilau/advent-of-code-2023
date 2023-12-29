package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 6")
class Day6Test {

    val records = """
        Time:      7  15   30
        Distance:  9  40  200""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `There should be 288 ways you could beat the record in each race`() {
            assertThat(Day6(records).partOne()).isEqualTo(4 * 8 * 9)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `There should be 71503 ways you could beat the record in this race`() {
            assertThat(Day6(records).partTwo()).isEqualTo(71503)
        }

    }

}