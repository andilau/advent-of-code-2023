package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 1")
class Day1Test {
    val calibrationValues = """
        1abc2
        pqr3stu8vwx
        a1b2c3d4e5f
        treb7uchet
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun shouldReturnSumOfCalibrationValues() {
            assertThat(Day1(calibrationValues).partOne()).isEqualTo(142)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun shouldReturnProductOfEmptyList() {
            assertThat(Day1(emptyList()).partTwo()).isEqualTo(1)
        }


    }
}