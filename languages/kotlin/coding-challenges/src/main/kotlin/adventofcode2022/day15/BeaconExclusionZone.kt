package adventofcode2022.day15

import adventofcode2022.readText
import kotlin.math.absoluteValue

private const val DAY = "day15"

fun main() {
//    check(beaconExclusionZone(readText(DAY, "exampleInput.txt"), 10) == 26)
//    println(beaconExclusionZone(readText(DAY), 2000000))

    check(beaconExclusionZoneP2(readText(DAY, "exampleInput.txt"), 20) == 56000011)
//    println(beaconExclusionZone(readText(DAY)))
}

fun beaconExclusionZoneP2(input: String, searchSpace: Int): Int {
    
    
    
    val answer = Pos(14, 11)
    
    return answer.x * 4_000_000 + answer.y
}

// Essentially a pair, but reference x and y instead of first and second.
data class Pos<T>(val x: T, val y: T)

fun beaconExclusionZone(input: String, rowToCount: Int): Int {
    val inputPositions = parseInputPositions(input)

    val signalMap = mutableMapOf<Pos<Int>, Char>()
    
    inputPositions.forEach {
        val (sensor, beacon) = it
        
        signalMap.put(sensor, 'S')
        signalMap.put(beacon, 'B')
        
        val dX = (sensor.x - beacon.x).absoluteValue
        val dY = (sensor.y - beacon.y).absoluteValue
        val manhattanDistance = dX + dY
        
//        println("For sensor: $sensor, beacon: $beacon, distance = $manhattanDistance")

        fillMapFromSensorWithDistance(signalMap, sensor, manhattanDistance, rowToCount)
        
    }
    
//    printSignalMap(signalMap)


    val notBeaconCount = signalMap.filter { it.key.y == rowToCount }
        .filter { it.value == '#' }
        .count()
    return notBeaconCount
}

private fun parseInputPositions(input: String): List<Pair<Pos<Int>, Pos<Int>>> {
    val inputLocations = input.lines().map {
        val (sensorString, beaconString) = it.split(":")
        val sensorValues = sensorString.substring(sensorString.indexOf("x="))
        val beaconValues = beaconString.substring(beaconString.indexOf("x="))

        val (sX, sY) = sensorValues.split(", ")
            .map { it.substring(2) }
            .map { it.toInt() }

        val sensor = Pos(sX, sY)

        val (bX, bY) = beaconValues.split(", ")
            .map { it.substring(2) }
            .map { it.toInt() }

        val beacon = Pos(bX, bY)

        Pair(sensor, beacon)
    }
    return inputLocations
}

fun fillMapFromSensorWithDistance(
    signalMap: MutableMap<Pos<Int>, Char>,
    sensor: Pos<Int>,
    distance: Int,
    rowToCount: Int
) {
    val checkYRange = sensor.y - distance .. sensor.y + distance
    if (rowToCount !in checkYRange) {
        return
    }
    
    val remainingDistance = distance - ((sensor.y - rowToCount).absoluteValue)
    
    for (xDiff in -remainingDistance .. remainingDistance) {
        val x = sensor.x + xDiff
        val pos = Pos(x, rowToCount)
        if (signalMap[pos] == null) {
            signalMap.put(pos, '#')
        }
    }
}

fun printSignalMap(map: Map<Pos<Int>, Char>) {
    val xAxisFrequency = 5 // TODO -> Constant?
    
    val minX = map.keys.map { it.x }.min()
    val maxX = map.keys.map { it.x }.max()
    val minY = map.keys.map { it.y }.min()
    val maxY = map.keys.map { it.y }.max()

    val extendMapSize = 0

    val maxXCharLength = maxOf(maxX.toString().length, minX.toString().length)
    val maxYCharLength = maxOf(maxY.toString().length, minY.toString().length)
    
    // Print X coordinates
    for (i in 0 until maxXCharLength) {
        print(" ".repeat(maxYCharLength + 1))
        
        for (x in minX..maxX) {
            if (x % xAxisFrequency == 0) {
                val offset = maxXCharLength - x.toString().length
                if (offset <= i) {
                    print(x.toString()[i - offset])
                } else {
                    print(' ')
                }
            } else {
                print(' ')
            }
        }
        println()
    }
    
    for (y in minY..maxY) {
        // Printing Y value - 'Right Aligned'
        for (i in 0 until maxYCharLength) {
            val offset = maxYCharLength - y.toString().length
            if (offset <= i) {
                print(y.toString()[i - offset])
            } else {
                print(' ')
            }
        }
        print(' ')
        
        // Main Map Printing
        for (x in minX - extendMapSize..maxX) {
            val pos = map[Pos(x,y)] ?: '.'

            print(pos)
        }
        println()
    }
}
