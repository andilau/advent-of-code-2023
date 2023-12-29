package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 13")
class Day13Test {

    val patterns = """
        #.##..##.
        ..#.##.#.
        ##......#
        ##......#
        ..#.##.#.
        ..##..##.
        #.#.##.#.
        
        #...##..#
        #....#..#
        ..##..###
        #####.##.
        #####.##.
        ..##..###
        #....#..#
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `What number do you get after summarizing all of your notes`() {
            assertThat(Day13(patterns).partOne()).isEqualTo(405)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `What number do you get after summarizing the new reflection line in each pattern in your notes`() {
            assertThat(Day13(patterns).partTwo()).isEqualTo(400)
        }

    }

}