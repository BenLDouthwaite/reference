package adventofcode2023.day1

import aocUtils.AdventOfCodeSolution
import aocUtils.validate

fun main() {
    validate(Trebuchet(),
        142,
        281,
        part2ExampleFileName = "exampleInput2.txt"
    )
}

class Trebuchet: AdventOfCodeSolution {

    companion object {
        val DIGIT_STRINGS = mapOf(
            "one" to '1',
            "two" to '2',
            "three" to '3',
            "four" to '4',
            "five" to '5',
            "six" to '6',
            "seven" to '7',
            "eight" to '8',
            "nine" to '9',
        )
    }

    override fun part1(input: String): Int {
        return trebuchet(input, false)
    }

    override fun part2(input: String): Int {
        return trebuchet(input, true)
    }

    private fun trebuchet(input: String, checkDigitWords: Boolean): Int {
        return input.lines().sumOf { line ->
            val first = getFirstDigit(line, checkDigitWords)
            val last = getLastDigit(line, checkDigitWords)
            "$first$last".toInt()
        }
    }

    private fun getFirstDigit(line: String, checkDigitWords: Boolean = false): Char? {
        for (i in line.indices) {
            if (line[i].isDigit()) {
                return line[i]
            }
            if (checkDigitWords) {
                for ((digitWord, digitChar) in DIGIT_STRINGS) {
                    if (line.substring(i).startsWith(digitWord)) {
                        return digitChar
                    }
                }
            }
        }
        return null
    }

    private fun getLastDigit(line: String, checkDigitWords: Boolean = false): Char? {
        for (i in line.length - 1 downTo 0) {
            if (line[i].isDigit()) {
                return line[i]
            }
            if (checkDigitWords) {
                for ((digitWord, digitChar) in DIGIT_STRINGS) {
                    if (line.substring(0, i + 1).endsWith(digitWord)) {
                        return digitChar
                    }
                }
            }
        }
        return null
    }
}

