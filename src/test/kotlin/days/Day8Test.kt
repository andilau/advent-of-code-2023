package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 8")
class Day8Test {

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        private val instructions2Steps = """
        RL
        
        AAA = (BBB, CCC)
        BBB = (DDD, EEE)
        CCC = (ZZZ, GGG)
        DDD = (DDD, DDD)
        EEE = (EEE, EEE)
        GGG = (GGG, GGG)
        ZZZ = (ZZZ, ZZZ)
        """.trimIndent().lines()

        @Test
        fun shouldRequire6StepsToReachZZZ() {
            assertThat(Day8(instructions2Steps).partOne()).isEqualTo(2)
        }

        private val instructions6Steps = """
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent().lines()

        @Test
        fun shouldRequire2StepsToReachZZZ() {
            assertThat(Day8(instructions6Steps).partOne()).isEqualTo(6)
        }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        private val instructions6Steps = """
            LR
            
            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
        """.trimIndent().lines()

        @Test
        fun shouldRequire6StepsToReachZZZ() {
            assertThat(Day8(instructions6Steps).partTwo()).isEqualTo(6L)
        }

    }
}