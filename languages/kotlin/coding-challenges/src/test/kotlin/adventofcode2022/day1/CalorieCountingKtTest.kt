package adventofcode2022.day1

import adventofcode2022.readText
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.nio.file.Files
import java.nio.file.Path

class CalorieCountingKtTest {

    @Test
    fun calorieCounting_exampleInput_part1() {
        // given
        val input = readText("day1", "exampleInput.txt")

        // when
        val result = calorieCountingPart1(input)

        // then
        assertEquals(24000, result)
    }

    @Test
    fun calorieCounting_exampleInput_part2() {
        // given
        val input = readText("day1", "exampleInput.txt")

        // when
        val result = calorieCountingPart2(input)

        // then
        assertEquals(45000, result)
    }

    @Test
    fun calorieCounting_exampleInput_part2_naive() {
        // given
        val input = readText("day1", "exampleInput.txt")

        // when
        val result = calorieCountingPart2Naive(input)

        // then
        assertEquals(45000, result)
    }
}