package adventofcode2022.day15

import adventofcode2022.readText
import kotlin.math.absoluteValue

private const val DAY = "day15"

fun main() {
    check(beaconExclusionZone(readText(DAY, "exampleInput.txt"), 10) == 26)
    println(beaconExclusionZone(readText(DAY), 2000000))
    check(beaconExclusionZoneP2(readText(DAY, "exampleInput.txt"), 20) == 56000011L)
    println(beaconExclusionZoneP2(readText(DAY), 4_000_000))
}

fun beaconExclusionZoneP2(input: String, searchSpace: Int): Long {

    val inputPositions = parseInputPositions(input)

    val signalMapDistances = mutableMapOf<Pos<Int>, Int>()
    
    inputPositions.forEach {
        val (sensor, beacon) = it
        val manhattanDistance = getManhattanDistance(sensor, beacon)
        signalMapDistances.put(sensor, manhattanDistance)
    }
    
    inputPositions.forEach {
        val (sensor, beacon) = it
        val manhattanDistance = getManhattanDistance(sensor, beacon)
        val borderAdjacentPositions = getBorderPositions(sensor, manhattanDistance)

        val filteredBorderAdjacentPositions = borderAdjacentPositions
            .filter { it.x in 0 .. searchSpace }
            .filter { it.y in 0 .. searchSpace }
        
        filteredBorderAdjacentPositions.forEach {
            val positionIsUncovered = signalMapDistances.all { (sensor, distanceCovered) ->
                val positionDistance = getManhattanDistance(sensor, it)
                positionDistance > distanceCovered
            }

            if (positionIsUncovered) {
                println("Position $it uncovered, return")
                return (it.x.toLong() * 4_000_000L) + it.y.toLong()
            }
        }
    }

    return 0L
}

fun getBorderPositions(sensor: Pos<Int>, distance: Int): List<Pos<Int>> {
    
    val borderAdjacentDistance = distance + 1
    
    val borderPositions = mutableListOf<Pos<Int>>()
    
    for (xOffset in 0 .. borderAdjacentDistance) {
        val yOffset = (borderAdjacentDistance - xOffset.absoluteValue)
        borderPositions.add(Pos(sensor.x + xOffset, sensor.y + yOffset))
        borderPositions.add(Pos(sensor.x + xOffset, sensor.y - yOffset))
        borderPositions.add(Pos(sensor.x - xOffset, sensor.y + yOffset))
        borderPositions.add(Pos(sensor.x - xOffset, sensor.y - yOffset))
    }
    
    return borderPositions
}

// Essentially a pair, but reference x and y instead of first and second.
data class Pos<T>(val x: T, val y: T)

fun beaconExclusionZone(input: String, rowToCount: Int): Int {
    val inputPositions = parseInputPositions(input)

    val signalMap = mutableMapOf<Pos<Int>, Char>()
    
    inputPositions.forEach{
        val (sensor, beacon) = it
        signalMap.put(sensor, 'S')
        signalMap.put(beacon, 'B')
        val manhattanDistance = getManhattanDistance(sensor, beacon)
        fillMapFromSensorWithDistance(signalMap, sensor, manhattanDistance, rowToCount)
    }

    // Debug only
//    printSignalMap(signalMap)
    
    val notBeaconCount = signalMap.filter { it.key.y == rowToCount }
        .filter { it.value == '#' }
        .count()
    return notBeaconCount
}

private fun getManhattanDistance(
    posA: Pos<Int>,
    posB: Pos<Int>
): Int {
    val dX = (posA.x - posB.x).absoluteValue
    val dY = (posA.y - posB.y).absoluteValue
    return dX + dY
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
    if (rowToCount !in sensor.y - distance..sensor.y + distance) {
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
    val xAxisFrequency = 5 
    
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
