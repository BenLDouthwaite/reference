package adventofcode2022.day5

import adventofcode2022.readText
import java.util.Stack

fun main() {
    val exampleInput = readText("day5", "exampleInput.txt")

    val examplePart1Result = supplyStacksP1(exampleInput)
    check(examplePart1Result == "CMZ")

    val input = readText("day5")
    val part1Result = supplyStacksP1(input)
    println("Puzzle output. Part 1: $part1Result")

    val examplePart2Result = supplyStacksP2(exampleInput)
    check(examplePart2Result == "MCD")

    val part2Result = supplyStacksP2(input)
    println("Puzzle output. Part 2: $part2Result")
}

fun supplyStacksP2(input: String): String {
    val split = input.split("\n\n")
    val state = split[0]

    val crateStacks = getInitialCrateState(state)
    
    val commands = getCommands(split[1])

    for ((times, start, end) in commands) {
        val tempStack = Stack<Char>()
        repeat(times) {
            val char = crateStacks[start - 1].pop()
            tempStack.push(char)
        }
        repeat(times) {
            val char = tempStack.pop()
            crateStacks[end - 1].push(char)
        }
    }
    
    val result = crateStacks.map { it.pop() }.joinToString(separator = "")

    return result}

private fun getCommands(commandsString: String): List<List<Int>> {
    return commandsString.lines().map {
        it.split(" ").mapNotNull { it.toIntOrNull() }
    }
}

fun supplyStacksP1(input: String): String {

    val split = input.split("\n\n")
    val state = split[0]

    val crateStacks = getInitialCrateState(state)
    
    val commands = getCommands(split[1])

    for ((times, start, end) in commands) {
        repeat(times) {
            val char = crateStacks[start - 1].pop()
            crateStacks[end - 1].push(char)
        }
    }

    val result = crateStacks.map { it.pop() }.joinToString(separator = "")

    return result
}

private fun getInitialCrateState(state: String): Array<Stack<Char>> {
    val initialStateLinesReversed = state.lines().reversed()
    val stackCount =
        (initialStateLinesReversed[1].length + 1) / 4 // Read from first row of values. Assumes all have at least 1 crate.
    val crateStacks = Array(stackCount) { _ -> Stack<Char>() }

    initialStateLinesReversed.forEach {
        for (i in 1..stackCount) {
            val charIndex = i * 4 - 3
            if (it.length <= charIndex) {
                continue
            }
            val char = it[charIndex]
            if (char != ' ') {
                crateStacks.get(i - 1).push(char)
            }
        }
    }
    return crateStacks
}
