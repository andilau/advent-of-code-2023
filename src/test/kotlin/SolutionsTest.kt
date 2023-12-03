import days.Day1
import days.Day2
import days.Day3
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
    )
        .map { (day, answers) ->
            DynamicTest.dynamicTest("${day.javaClass.simpleName} -> ${answers.first} / ${answers.second}") {
                assertThat(day.partOne()).isEqualTo(answers.first)
                assertThat(day.partTwo()).isEqualTo(answers.second)
            }
        }
}