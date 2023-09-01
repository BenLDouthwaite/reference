package adventofcode2022.day6

import adventofcode2022.readText

fun main() {
    val exampleInput = readText("day6", "exampleInput.txt")

    val examplePart1Result = tuningTrouble(exampleInput, 4)
    check(examplePart1Result == 7)

    val input = readText("day6")
    val part1Result = tuningTrouble(input, 4)
    println("Puzzle output. Part 1: $part1Result")

    val examplePart2Result = tuningTrouble(exampleInput, 14)
    check(examplePart2Result == 19)

    val part2Result = tuningTrouble(input, 14)
    println("Puzzle output. Part 2: $part2Result")
}

fun tuningTrouble(input: String, uniqueCharCount: Int): Int {
    val length = input.length
    for (i in uniqueCharCount.. length) {
        val ss = input.substring(i - uniqueCharCount, i)
        if (ss.toSet().size == uniqueCharCount) {
            return i
        }
    }
    return 0
}