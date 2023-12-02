package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 1")
class Day1Test {
    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        val calibrationValues = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent().lines()

        @Test
        fun shouldReturnSumOfCalibrationValues() {
            assertThat(Day1(calibrationValues).partOne()).isEqualTo(142)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        val calibrationValues = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent().lines()
        @Test
        fun shouldReturnSumOfCalibrationValues() {
            assertThat(Day1(calibrationValues).partTwo()).isEqualTo(281)
        }


    }
}