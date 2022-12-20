package adventofcode2022.day4

import adventofcode2022.readText

fun main() {
    println()

    val exampleInput = readText("day4", "exampleInput.txt")

    val examplePart1Result = campCleanupP1(exampleInput)
    check(examplePart1Result == 2)

    val input = readText("day4")
    val part1Result = campCleanupP1(input)
    println("Puzzle output. Part 1: $part1Result")

    val examplePart2Result = campCleanupP2(exampleInput)
    check(examplePart2Result == 4)

    val part2Result = campCleanupP2(input)
    println("Puzzle output. Part 2: $part2Result")
}

fun campCleanupP2(input: String): Int {
    return input.lines().count {
        val pairs = it.split(',')

        val (aStart, aEnd) = pairs[0].split("-").map(String::toInt)
        val (bStart, bEnd) = pairs[1].split("-").map(String::toInt)

        val aRange = aStart..aEnd
        val bRange = bStart .. bEnd

        (bStart in aRange || bEnd in aRange) || (aStart in bRange || aEnd in bRange)
    }
}

fun campCleanupP1(input: String): Int {
    return input.lines().count {
        val pairs = it.split(',')

        val (aStart, aEnd) = pairs[0].split("-").map(String::toInt)
        val (bStart, bEnd) = pairs[1].split("-").map(String::toInt)

        val aRange = aStart..aEnd
        val bRange = bStart..bEnd

        (bStart in aRange && bEnd in aRange) || (aStart in bRange && aEnd in bRange)
    }
}