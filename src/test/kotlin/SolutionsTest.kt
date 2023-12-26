import days.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import util.InputReader

@DisplayName("Solutions")
class SolutionsTest {

    @TestFactory
    @Disabled
    fun testAdventOfCode() = listOf(
        Day1(InputReader.getInputAsList(1)) to Pair(55208, 54578),
        Day2(InputReader.getInputAsList(2)) to Pair(2283, 78669),
        Day3(InputReader.getInputAsList(3)) to Pair(527369, 73074886),
        Day4(InputReader.getInputAsList(4)) to Pair(32001, 5037841),
        Day8(InputReader.getInputAsList(8)) to Pair(14257, 16187743689077),
        Day9(InputReader.getInputAsList(9)) to Pair(1921197370, 1124),
        Day11(InputReader.getInputAsList(11)) to Pair(9563821, 1124),
    )
        .map { (day, answers) ->
            DynamicTest.dynamicTest("${day.javaClass.simpleName} -> ${answers.first} / ${answers.second}") {
                assertThat(day.partOne()).isEqualTo(answers.first)
                assertThat(day.partTwo()).isEqualTo(answers.second)
            }
        }
}