package adventofcode2022.day1

import aocUtils.readText

fun main() {
    val input = readText("day1")

    val part1Result = calorieCountingPart1(input)
    println("Puzzle output. Part 1: $part1Result")

    val part2ResultV2 = calorieCountingPart2(input)
    println("Puzzle output. Part 2: $part2ResultV2")
}

fun calorieCountingPart2(input: String): Int {
    return input.splitToSequence("\n\n")
        .map { it.lineSequence().map(String::toInt) }.map { it.sum() }
        .sortedDescending()
        .take(3)
        .sum()
}

fun calorieCountingPart2Naive(input: String): Int {
    val caloriesList = input.lines()
    val totalsList = mutableListOf<Int>()
    var total = 0;
    caloriesList.forEach {
        if ("" == it) {
            totalsList.add(total)
            total = 0
        } else {
            total += it.toInt()
        }
        println(it)
    }
    if (total != 0) {
        totalsList.add(total)
    }

    totalsList.sort()
    totalsList.reverse()
    return totalsList.stream().limit(3).mapToInt { it }.sum()
}
fun calorieCountingPart1(input: String): Int {
    val caloriesList = input.lines()
    var maxTotal = 0;
    var total = 0;
    caloriesList.forEach {
        if ("" == it) {
            if (total > maxTotal) {
                maxTotal = total
            }
            total = 0
        } else {
            total += it.toInt()
        }
    }
    return maxTotal
}