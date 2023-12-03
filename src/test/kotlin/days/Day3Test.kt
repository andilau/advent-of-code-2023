package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 2")
class Day3Test {

    val schematic = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..
        """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun shouldReturnSumOfAllPartNumbers() {
            assertThat(Day3(schematic).partOne()).isEqualTo(4361)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun shouldReturnSumOfAllGearRatios() {
            assertThat(Day3(schematic).partTwo()).isEqualTo(467835)
        }

    }
}