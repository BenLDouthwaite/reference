package adventofcode2022

import adventofcode2022.Constants.AOC_DIR
import java.nio.file.Files
import java.nio.file.Paths

fun readText(day: String, inputName: String = "puzzleInput.txt"): String {
    val path = Paths.get(AOC_DIR, day, inputName)
    return Files.readString(path)
}