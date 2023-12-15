package adventofcode2023.day2

import aocUtils.AdventOfCodeSolution
import aocUtils.validate

fun main() {
    validate(
        CubeConundrum(),
        8,
        2286,
    )
}

class CubeConundrum: AdventOfCodeSolution {

    companion object {
        private const val RED_MAX = 12;
        private const val GREEN_MAX = 13;
        private const val BLUE_MAX = 14;
    }

    override fun part1(input: String): Int {
        return input.lines().sumOf { line ->
            val (gameId, gameRecord) = line.split(':')
            val gameNumber = Integer.valueOf(gameId.split(' ')[1])
            val allGamesValid = gameRecord.split(';').all { game ->
                game.split(',').all { round ->
                    val (count, colour) = round.trim().split(' ')
                    when (colour) {
                        "red" -> count.toInt() <= RED_MAX
                        "blue" -> count.toInt() <= BLUE_MAX
                        "green" -> count.toInt() <= GREEN_MAX
                        else -> throw IllegalArgumentException("bad colour")
                    }
                }
            }
            if (allGamesValid) gameNumber else 0
        }
    }

    override fun part2(input: String): Int {
        return input.lines().sumOf { line ->
            val (_, allGamesRecord) = line.split(':')
            var minRed = 0
            var minBlue = 0
            var minGreen = 0
            allGamesRecord.split(';').forEach { gameRecord ->
                gameRecord.split(',').forEach { colourRound ->
                    val (countStr, colour) = colourRound.trim().split(' ')
                    val count = countStr.toInt()
                    when (colour) {
                        "red" -> if (count > minRed) minRed = count
                        "blue" -> if (count > minBlue) minBlue = count
                        "green" -> if (count > minGreen) minGreen = count
                        else -> throw IllegalArgumentException("bad colour")
                    }
                }
            }
            minRed * minBlue * minGreen
        }
    }
}