package adventofcode2022.day10

import adventofcode2022.readText

/**
 * This puzzle is some of the hackiest code I've written
 * Couldn't wrap my head around the indexes to be comparing the 'sprite' to current 'position'
 */
fun main() {
//    check(cathodeRayTube(readText("day10", "exampleInput.txt")) == 13140)
    println(cathodeRayTube(readText("day10")))
//    println(cathodeRayTubeP2(readText("day10", "exampleInput.txt")))
    println(cathodeRayTubeP2(readText("day10")))
}

fun cathodeRayTubeP2(input: String): Any {
    var cycle = 1
    var x = 1
    
    var sb = StringBuilder()
    
    input.lines().forEach {
        
        println(sb.toString().chunked(40).joinToString("\n"))
        
        if (it.equals("noop")) {
            updateCrtRow(x, sb)
            cycle++
        } else {
            val split = it.split(" ")
            updateCrtRow(x, sb)
            cycle++
            updateCrtRow(x, sb)
            val v = split[1].toInt()
            x += v
            
            cycle++
        }
    }
    return sb.toString().chunked(40).joinToString("\n")
}

private fun updateCrtRow(x: Int, sb: StringBuilder) {
    var position = sb.length % 40
    if (position - x in -1..1) {
        sb.append("#")
    } else {
        sb.append(".")
    }
}

fun cathodeRayTube(input: String): Int {
    var cycle = 1
    var x = 1
    val interestingSignalStrengths = mutableListOf<Int>()
    
    var totalSignalStrength = 0
    input.lines().forEach {
        if (it.equals("noop")) {
            if ((cycle % 40) - 20 == 0) {
                totalSignalStrength += cycle * x
            }
            cycle++
        } else {
            val split = it.split(" ")

            if ((cycle % 40) - 20 == 0) {
                interestingSignalStrengths.add(cycle * x)
            }
            cycle++

            if ((cycle % 40) - 20 == 0) {
                interestingSignalStrengths.add(cycle * x)
            }
            cycle++
            
            val v = split[1].toInt()
            x += v
        }
    }
    return totalSignalStrength
}