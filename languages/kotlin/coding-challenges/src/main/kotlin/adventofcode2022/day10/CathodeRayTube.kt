package adventofcode2022.day10

import adventofcode2022.readText

/**
 * This puzzle is some of the hackiest code I've written
 * Couldn't wrap my head around the indexes to be comparing the 'sprite' to current 'position'
 */
fun main() {
//    check(cathodeRayTube(readText("day10", "exampleInput.txt")) == 13140)
//    println(cathodeRayTube(readText("day10")))
//    println(cathodeRayTubeP2(readText("day10", "exampleInput.txt")))
    val resultP2 = cathodeRayTubeP2(readText("day10"))
    println(resultP2)
    
    val expectedP2 = """
        ###...##..###..#..#.####.#..#.####...##.
        #..#.#..#.#..#.#.#..#....#.#..#.......#.
        #..#.#..#.#..#.##...###..##...###.....#.
        ###..####.###..#.#..#....#.#..#.......#.
        #....#..#.#....#.#..#....#.#..#....#..#.
        #....#..#.#....#..#.#....#..#.####..##..
    """.trimIndent()
    
    check(resultP2 == expectedP2)
}

fun cathodeRayTubeP2(input: String): Any {
    var cycle = 1
    var x = 1
    val rows = mutableListOf<String>()
    
    var currentCrtRow = ""

    var sb = StringBuilder()
    
    printSprite(x)
    
    input.lines().forEach {
//        println(rows.joinToString("\n"))
        println(sb.toString())
        if (it.equals("noop")) {

            currentCrtRow = updateCrtRow(cycle, x, currentCrtRow, it, sb)

            if (currentCrtRow.length == 40) {
                rows.add(currentCrtRow)
                currentCrtRow = ""
            }
            
            cycle++
        } else {
            val split = it.split(" ")

            print("Start cycle $cycle:")
            println("begin executing $it")
            
            currentCrtRow = updateCrtRow(cycle, x, currentCrtRow, it, sb)
            
            if (currentCrtRow.length == 40) {
                rows.add(currentCrtRow)
                currentCrtRow = ""
            }
            
            cycle++

            currentCrtRow = updateCrtRow(cycle, x, currentCrtRow, it, sb)

            if (currentCrtRow.length == 40) {
                rows.add(currentCrtRow)
                currentCrtRow = ""
            }
            
            val v = split[1].toInt()
            x += v
            println("End of cycle $cycle: finish executing $it (Register X is now $x)")
            printSprite(x)

            cycle++
        }
    }
    return rows.joinToString("\n")
//    return sb.toString()
}

fun printSprite(x: Int) {
    var chars = ".".repeat(40).toCharArray()
    
    if (x in 0 until 40) {
        chars[x] = '#'
    }
    if (x-1 >= 0) {
        chars[x-1] = '#'
    }
    if (x + 1 < 40) {
        chars[x+1] = '#'
    }
    println(chars)
}

/**
 * When cycle = 11 & x = 13, is drawing but shouldn't be when checking <=2
 * 
 * when cycle = 10 and x = 8, is not drawing but should when <=1
 */
private fun updateCrtRow(cycle: Int, x: Int, currentCrtRow: String, command: String, sb: StringBuilder): String {

    var newCrtRow = currentCrtRow
    var position = currentCrtRow.length
//    var position = sb.length % 40
    
    if (position == 0) {
        sb.append("\n")
    }
    
    if (position - x in -1..1) {
        newCrtRow += "#"
        sb.append("#")
    } else {
        newCrtRow += "."
        sb.append(".")
    }
    
    
    println("During Cycle $cycle : CRT draws pixel in position $position")

    println("Current CRT row: $newCrtRow")

    return newCrtRow
}

fun cathodeRayTube(input: String): Int {
    var cycle = 1
    var x = 1
    val interestingSignalStrengths = mutableListOf<Int>()
    
    input.lines().forEach {
        if (it.equals("noop")) {
            if ((cycle % 40) - 20 == 0) {
                val signalStrength = cycle * x
                interestingSignalStrengths.add(signalStrength)
            }
            cycle++
        } else {
            val split = it.split(" ")

            if ((cycle % 40) - 20 == 0) {
                val signalStrength = cycle * x
                interestingSignalStrengths.add(signalStrength)
            }
            cycle++

            if ((cycle % 40) - 20 == 0) {
                val signalStrength = cycle * x
                interestingSignalStrengths.add(signalStrength)
            }
            cycle++
            
            val v = split[1].toInt()
            x += v
        }
    }
    return interestingSignalStrengths.sum()
}