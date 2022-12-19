package adventofcode2022.day3

import adventofcode2022.readText

fun main() {
    println()

    val exampleInput = readText("day3", "exampleInput.txt")

    val examplePart1Result = rucksackReorganisationP1(exampleInput)
    check(examplePart1Result == 157)

    val input = readText("day3")
    val part1Result = rucksackReorganisationP1(input)
    println("Puzzle output. Part 1: $part1Result")

    val examplePart2Result = rucksackReorganisationP2(exampleInput)
    check(examplePart2Result == 70)

    val part2Result = rucksackReorganisationP2(input)
    println("Puzzle output. Part 2: $part2Result")
}

fun rucksackReorganisationP2(input: String): Int {
    return input.lines().chunked(3).sumOf {
        val group = it[0].toSet().intersect(it[1].toSet()).intersect(it[2].toSet())
        characterScore(group.first())
    }
}

fun rucksackReorganisationP1(input: String): Int {
    return input.lines().sumOf {
        val compartmentSize = it.length / 2 // assume even
        val firstHalf = it.substring(0, compartmentSize)
        val secondHalf = it.substring(compartmentSize, it.length)

        val intersect = firstHalf.toSet().intersect(secondHalf.toSet())
        check(intersect.size == 1)

        characterScore(intersect.first())
    }
}

private fun characterScore(c: Char): Int {
    val score = if (c.isLowerCase()) {
        c.code - 96
    } else {
        c.code - 38
    }
    return score
}
