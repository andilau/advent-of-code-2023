package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 11")
class Day11Test {

    val spaceWithGalaxies = """
        ...#......
        .......#..
        #.........
        ..........
        ......#...
        .#........
        .........#
        ..........
        .......#..
        #...#.....
    """.trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun shouldReturnSumOfTheShortestPathsBetweenAllGalaxiesExpanded() {
            assertThat(Day11(spaceWithGalaxies).partOne()).isEqualTo(374)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun shouldReturnSumOfTheShortestPathsBetweenAllGalaxiesExpanded10Times() {
            assertThat(Day11(spaceWithGalaxies).test(10-1)).isEqualTo(1030L)
        }        @Test
        fun shouldReturnSumOfTheShortestPathsBetweenAllGalaxiesExpanded100Times() {
            assertThat(Day11(spaceWithGalaxies).test(100-1)).isEqualTo(8410L)
        }

    }
}