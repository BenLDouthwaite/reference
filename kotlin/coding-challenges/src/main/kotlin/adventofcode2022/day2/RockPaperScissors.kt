package adventofcode2022.day2

import aocUtils.readText

fun main() {
    val exampleInput = readText("day2", "exampleInput.txt")

    val examplePart1Result = rockPaperScissorsPart1(exampleInput)
    check(examplePart1Result == 15)

    val input = readText("day2")
    val part1Result = rockPaperScissorsPart1(input)
    println("Puzzle output. Part 1: $part1Result")

    val examplePart2Result = rockPaperScissorsPart2(exampleInput)
    check(examplePart2Result == 12)

    val part2Result = rockPaperScissorsPart2(input)
    println("Puzzle output. Part 2: $part2Result")
}

/** TODO Comment from reddit that is worth investigating:
 * If you take 0 = rock 1 = paper and 2 = scissors you can use Input + 2 % 3 To find the winner and Input - 2 % 3 To find the loser.
 * I used this in my solution to part 2 which ended up being just a couple lines of code.
*/

fun rockPaperScissorsPart2(input: String): Int {
    val scoresSum = input.lines().sumOf {
        val roundScore = roundScoreP2(it[0], it[2])
        roundScore
    }

    return scoresSum
}

fun rockPaperScissorsPart1(input: String): Int {

    val scoresSum = input.lines().sumOf {
        val roundScore = roundScoreP1(it[0], it[2])
        roundScore
    }

    return scoresSum
}

fun roundScoreP2(opponent: Char, response: Char): Int {

    val opponentMove = Move.fromOpponent(opponent)

    val requiredResultMap = mapOf(
        'X' to 0,
        'Y' to 3,
        'Z' to 6
    )

    val roundScore = requiredResultMap[response]!!

    val neededMoveMap = mapOf(
        // Draw
        Pair(Move.ROCK, 3) to Move.ROCK,
        Pair(Move.PAPER, 3) to Move.PAPER,
        Pair(Move.SCISSORS, 3) to Move.SCISSORS,

        // Lose
        Pair(Move.ROCK, 0) to Move.SCISSORS,
        Pair(Move.PAPER, 0) to Move.ROCK,
        Pair(Move.SCISSORS, 0) to Move.PAPER,

        // Win
        Pair(Move.ROCK, 6) to Move.PAPER,
        Pair(Move.PAPER, 6) to Move.SCISSORS,
        Pair(Move.SCISSORS, 6) to Move.ROCK,
    )

    val neededMove = neededMoveMap[Pair(opponentMove, roundScore)]

    return roundScore + neededMove!!.score
}


fun roundScoreP1(opponent: Char, response: Char): Int {

    val opponentMove = Move.fromOpponent(opponent)
    val responseMove = Move.fromResponse(response)

    val scoreMap = mapOf(
        // Draw
        Pair(Move.ROCK, Move.ROCK) to 3,
        Pair(Move.PAPER, Move.PAPER) to 3,
        Pair(Move.SCISSORS, Move.SCISSORS) to 3,

        // Lose
        Pair(Move.ROCK, Move.SCISSORS) to 0,
        Pair(Move.PAPER, Move.ROCK) to 0,
        Pair(Move.SCISSORS, Move.PAPER) to 0,

        // Win
        Pair(Move.ROCK, Move.PAPER) to 6,
        Pair(Move.PAPER, Move.SCISSORS) to 6,
        Pair(Move.SCISSORS, Move.ROCK) to 6,
    )

    val roundPair = Pair(opponentMove, responseMove)

    return scoreMap[roundPair]!! + responseMove!!.score
}

enum class Move(val opponent: Char, val response: Char, val score: Int) {
    ROCK('A', 'X', 1),
    PAPER('B', 'Y', 2),
    SCISSORS('C', 'Z', 3);

    companion object {
        private val opponentMap = values().associateBy { it.opponent }
        fun fromOpponent(opponent: Char) = opponentMap[opponent]

        private val responseMap = values().associateBy { it.response }
        fun fromResponse(response: Char) = responseMap[response]
    }
}