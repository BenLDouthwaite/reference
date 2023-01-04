package adventofcode2022.day14

import adventofcode2022.readText

private const val DAY = "day14"

fun main() {
    check(regolithResevoirP1(readText(DAY, "exampleInput.txt")) == 24)
    val p1 = regolithResevoirP1(readText(DAY))
    check(p1 == 683)
    println(p1)
    
    check(regolithResevoirP2(readText(DAY, "exampleInput.txt")) == 93)
    val p2 = regolithResevoirP2(readText(DAY))
    check(p2 == 28821)
    println(p2)
}

fun regolithResevoirP2(input: String): Int {
    val rockPositionLists = readInputCoordinateLists(input)
    val (_, maxY) = getBoundaries(rockPositionLists)

    val map = initMap(rockPositionLists)
    initMapFloor(map, maxY)

    return simulateFallingSand(map)
}

private fun initMapFloor(map: Array<Array<Char>>, maxY: Int) {
    for (i in 0 until map.size) {
        map[i][maxY + 2] = '#'
    }
}

private fun initSand(map: Array<Array<Char>>): Pos<Int> {
    var initialSandPosition = Pos(500, 0)
    map[initialSandPosition.x][initialSandPosition.y] = 'o'
    return initialSandPosition
}

fun regolithResevoirP1(input: String): Int {
    val rockPositionLists = readInputCoordinateLists(input)
    val map = initMap(rockPositionLists)
    return simulateFallingSand(map)
}

private fun simulateFallingSand(
    map: Array<Array<Char>>
): Int {
    var fallingSand = initSand(map)
    var sandAtRest = 0
    while (true) {
        val state = dropSand(map, fallingSand)
        when (state) {
            0 -> {
                sandAtRest++
                if (fallingSand.x == 500 && fallingSand.y == 0) {
                    break // Source blocked
                }
                fallingSand = initSand(map)
            }
            -1 -> {
                break // Fell to the void
            }
        }
    }
    return sandAtRest
}

private fun dropSand(
    map: Array<Array<Char>>,
    currentSandPosition: Pos<Int>
): Int {

    // Test if falling off the map to end the program
    val maxY = map[0].size
    if (currentSandPosition.y + 1 == maxY) {
        return -1
    }
    
    // Falling directly down.
    if (map[currentSandPosition.x][currentSandPosition.y + 1] == '.') {
        map[currentSandPosition.x][currentSandPosition.y] = '.'
        map[currentSandPosition.x][currentSandPosition.y + 1] = 'o'
        currentSandPosition.y++
        return 1 // Success
    } 
    // Down and left
    else if (map[currentSandPosition.x - 1][currentSandPosition.y + 1] == '.') {
        map[currentSandPosition.x][currentSandPosition.y] = '.'
        map[currentSandPosition.x - 1][currentSandPosition.y + 1] = 'o'
        currentSandPosition.y++
        currentSandPosition.x--
        return 1 // Success
    } 
    // Down and right
    else if (map[currentSandPosition.x + 1][currentSandPosition.y + 1] == '.') {
        map[currentSandPosition.x][currentSandPosition.y] = '.'
        map[currentSandPosition.x + 1][currentSandPosition.y + 1] = 'o'
        currentSandPosition.y++
        currentSandPosition.x++
        return 1 // Success
    }
    
    return 0 // At Rest
}


private fun readInputCoordinateLists(input: String): List<List<Pair<Int, Int>>> {
    return input.lines()
        .map {
            it.split(" -> ")
                .map {
                    val (x, y) = it.split(",").map { it.toInt() }
                    Pair(x, y)
                }
        }
}

private fun initMap(rockPositionLists: List<List<Pair<Int, Int>>>): Array<Array<Char>> {

    val (maxX, maxY) = getBoundaries(rockPositionLists)

    val map = Array(maxX + 300) { Array(maxY + 3) { '.' } }

    rockPositionLists.forEach {
        for (i in 0 until it.size - 1) {
            val start = it[i]
            val end = it[i + 1]
            for (x in start.first toward end.first) {
                for (y in start.second toward end.second) {
                    map[x][y] = '#'
                }
            }
        }
    }
    
    return map
}

private fun getBoundaries(rockPositionLists: List<List<Pair<Int, Int>>>): Pair<Int, Int> {
    val maxX = rockPositionLists.flatMap { it.map { it.first } }.max()
    val maxY = rockPositionLists.flatMap { it.map { it.second } }.max()
    return Pair(maxX, maxY)
}

private infix fun Int.toward(to: Int): IntProgression {
    val step = if (this > to) -1 else 1
    return IntProgression.fromClosedRange(this, to, step)
}

// For debugging 
fun printMap(map: Array<Array<Char>>, minX: Int, maxX: Int, minY: Int, maxY: Int) {
    
    val extendMapSize = 0

    for (i in 0 until 3) {
        for (x in minX..maxX) {
            print(x.toString()[i])
        }
        println()
    }

    for (y in minY..maxY + 2) {
        for (x in minX - extendMapSize..maxX + 10) {
            val pos = map[x][y]
            
            print(pos)
        }
        println()
    }
}

// 'Position' unsure best name.
data class Pos<T>(var x: T, var y: T)