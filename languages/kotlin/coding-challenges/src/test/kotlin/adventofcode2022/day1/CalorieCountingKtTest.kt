package adventofcode2022.day1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.nio.file.Files
import java.nio.file.Path

class CalorieCountingKtTest {

    @Test
    fun calorieCounting_exampleInput_part1() {
        // given
        val path = Path.of("./src/main/kotlin/adventofcode2022/day1/exampleInput.txt")
        val caloriesList = Files.readAllLines(path)

        // when
        val result = calorieCountingPart1(caloriesList)

        // then
        assertEquals(24000, result)
    }

    @Test
    fun calorieCounting_exampleInput_part2() {
        // given
        val path = Path.of("./src/main/kotlin/adventofcode2022/day1/exampleInput.txt")
        val caloriesList = Files.readAllLines(path)

        // when
        val result = calorieCountingPart2Naive(caloriesList)

        // then
        assertEquals(45000, result)
    }
}