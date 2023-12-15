package aocUtils

import adventofcode2022.Constants.AOC_2022_DIR
import java.nio.file.Files
import java.nio.file.Paths

private const val SOURCES_ROOT = "./src/main/kotlin"

// TODO - Legacy approach, update 2022 tests to use the 'validate' method below
fun readText(day: String, inputName: String = "puzzleInput.txt", dir: String = AOC_2022_DIR): String {
    val path = Paths.get(dir, day, inputName)
    return Files.readString(path)
}

private fun readPuzzleInput(aocYear: String, day: String, puzzleInputFile: String = "puzzleInput.txt"): String {
    return readFile(aocYear, day, puzzleInputFile)
}

private fun readExample(aocYear: String, day: String, exampleFile: String = "exampleInput.txt"): String {
    return readFile(aocYear, day, exampleFile)
}

private fun readFile(aocYear: String, day: String, file: String): String {
    val path = Paths.get(SOURCES_ROOT, aocYear, day, file)
    return Files.readString(path)
}

fun validate(
    classUnderTest: AdventOfCodeSolution,
    part1ExampleExpectedResult: Int,
    part2ExampleExpectedResult: Int,
    part1ExampleFileName: String = "exampleInput.txt",
    part2ExampleFileName: String = "exampleInput.txt",

    // Allow disabling a part for debugging during dev
    processPart1: Boolean = true,
    processPart2: Boolean = true
) {
    val packageName = classUnderTest::class.java.packageName;
    val packageDirs = packageName.split('.')
    val aocYear = packageDirs[0];
    val day = packageDirs[1];

    if (processPart1) {
        val part1 = classUnderTest::part1
        val part1ExampleInput = readExample(aocYear, day, part1ExampleFileName)
        val part1ExampleActualResult = part1(part1ExampleInput)
        println("Processing Part 1 Input")
        check(part1ExampleActualResult == part1ExampleExpectedResult) { "Part 1 failed example verification. $part1ExampleActualResult != $part1ExampleExpectedResult" }
        println("Part 1 - Puzzle input result: " + part1(readPuzzleInput(aocYear, day)))
    }

    if (processPart2) {
        val part2 = classUnderTest::part2
        val part2ExampleInput = readExample(aocYear, day, part2ExampleFileName)
        val part2ExampleActualResult = part2(part2ExampleInput)
        check(part2ExampleActualResult == part2ExampleExpectedResult) { "Part 2 failed example verification. $part2ExampleActualResult != $part2ExampleExpectedResult" }
        println("Part 2 - Puzzle input result: " + part2(readPuzzleInput(aocYear, day)))
    }
}

