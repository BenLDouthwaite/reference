package adventofcode2022.day22

import adventofcode2022.day22.Direction.*
import adventofcode2022.readText

private const val DAY = "day22"

fun main() {
    check(monkeyMap(readText(DAY, "exampleInput.txt")) == 6032)
    println(monkeyMap(readText(DAY)))

//    check(monkeyMap(readText(DAY, "exampleInput.txt")) == 301L)
//    println(monkeyMap(readText(DAY)))

}

fun monkeyMapP2(input: String): Int {
    println("God knows")
    return 0
} 

fun monkeyMap(input: String): Int {
    println(input)
    val (mapInput, commands) = input.split("\n\n")
//    println("Map: $mapInput")
//    println("Commands: $commands")
    val commandsList = getCommandsList(commands)

    val map = parseMap(mapInput)
    printMap(map)
    
    val mapXRanges = getXRanges(map)
    val mapYRanges = getYRanges(map)
    
    var curPos = getStartingPos(map)
    var direction = EAST
    
    println(direction)
    println(direction.turnRight())
    
    println("Starting pos = $curPos")
    // Process commands

    // Begin main loop
    commandsList.forEach {
        val (command, steps) = it
        
        println("\n Processing command $command, for $steps, starting at $curPos, facing $direction\n")
        
        when (command) {
            'R' -> {
                println("Turn right")
                direction = direction.turnRight()
            }
            'L' -> {
                println("Turn left")
                direction = direction.turnLeft()
            }
            'F' -> {
                println("Move forward $steps spaces")
                
                println("Currently at $curPos, facing $direction")
                
                // make generic
                // east so check x + 1
                val offset = when (direction) {
                    NORTH -> Pos(0, -1)
                    EAST -> Pos(1, 0)
                    SOUTH -> Pos(0, 1)
                    WEST -> Pos(-1, 0)
                }
                
                for (i in 1 .. steps!!) {
                    val nextPos = Pos(curPos.x + offset.x, curPos.y + offset.y)
                    val nextPosValue = map.get(nextPos) ?: ' '
                    when (nextPosValue) {
                        ' ' -> {
                            println("Need to loop around")
                            
//                            val (wrappedPos, newDirection) = getWrappedPosAndDirection(curPos, direction, map)
                            
                            // Get the coords of next tile after wrapping, 
                            val wrappedPos = when (direction) {
                                NORTH -> Pos(curPos.x, mapYRanges.getValue(curPos.x).max())
                                EAST -> Pos(mapXRanges.getValue(curPos.y).min(), curPos.y)
                                SOUTH -> Pos(curPos.x, mapYRanges.getValue(curPos.x).min())
                                WEST -> Pos(mapXRanges.getValue(curPos.y).max(), curPos.y)
                            }
                            
                            // Check if it's a wall, if so, break here
                            when (map.get(wrappedPos) ?: ' ') {
                                '.' -> {
                                    println("WRAP: Empty Space, can move forward")
                                    curPos = wrappedPos
                                }
                                '#' -> {
                                    println("WRAP: Next step is a wall, this command of moving forward is done")
                                    break
                                }
                            }
                            
                        }
                        '.' -> {
                            println("Empty Space, can move forward")
                            curPos = nextPos
                        }
                        '#' -> {
                            println("Next step is a wall, this command of moving forward is done")
                            break
                        }
                    }
                }
            }
        }
    }
    
    val column = curPos.x
    val row = curPos.y
    val facing = when (direction) {
        NORTH -> 3
        EAST -> 0
        SOUTH -> 1
        WEST -> 2
    }
    val result = (1000 * (row + 1)) + (4 * (column + 1)) + facing
    println("Result = $result")
    return result
}

// TODO How to combine with YRanges fun?
fun getXRanges(map: MutableMap<Pos, Char>): Map<Int, IntRange> {
    val maxY = map.keys.map { it.y }.max()
    return (0..maxY).map { y ->
        val rowValues = map.keys.filter { it.y == y }.map { it.x }
        y to rowValues.min()..rowValues.max()
    }.toMap()
}

fun getYRanges(map: MutableMap<Pos, Char>): Map<Int, IntRange> {
    val maxX = map.keys.map { it.x }.max()
    return (0..maxX).map { x ->
        val columnValues = map.keys.filter { it.x == x }.map { it.y }
        x to columnValues.min()..columnValues.max()
    }.toMap()
}

fun getStartingPos(map: MutableMap<Pos, Char>): Pos {
    return map.filter { (k, _) -> k.y == 0 }
        .filter { (_, v) -> v != ' ' }
        .toList()
        .sortedBy { it.first.y }
        .first().first
}

fun printMap(map: MutableMap<Pos, Char>) {

    val maxX = map.keys.map { it.x }.max()
    val maxY = map.keys.map { it.y }.max()

    for (y in 0 .. maxY) {
        for (x in 0 .. maxX) {
            val cc = map.get(Pos(x, y))
            if (cc == null) {
                print(' ')
            } else {
                print(cc)
            }
        }
        println()
    }
}

// Essentially a pair, but reference x and y instead of first and second.
data class Pos(val x: Int, val y: Int)

fun parseMap(mapInput: String): MutableMap<Pos, Char> {
    val map = mutableMapOf<Pos, Char>()

    val lines = mapInput.lines()
    for (y in 0 until lines.size) {
        val row = lines[y]
        for (x in 0 until row.length) {
            if (row[x] != ' ') {
                map.put(Pos(x, y), row[x])
            }
        }
    }
    
    return map
}

fun getCommandsList(commands: String): MutableList<Pair<Char, Int?>> {
    val commandsList = mutableListOf<Pair<Char, Int?>>()
    
    var remCommands = commands
    while (true) {
        val nextCharacter = remCommands.first()
        
        when (nextCharacter) {
            'R' -> {
                commandsList.add(Pair('R', null))
                remCommands = remCommands.substring(1)
            }
            'L' -> {
                commandsList.add(Pair('L', null))
                remCommands = remCommands.substring(1)
            }
            else -> {
                if (remCommands.toIntOrNull() != null) {
                    commandsList.add(Pair('F', remCommands.toInt()))
                    break
                }
                
                val rIndex = remCommands.indexOf('R')
                val lIndex = remCommands.indexOf('L')
                
                val index = listOf(rIndex, lIndex).filter { it > 0 }.min()

                val dist = remCommands.substring(0, index).toInt()
                commandsList.add(Pair('F', dist))
                remCommands = remCommands.substring(index)
            }
        }
    }
    
    return commandsList
}

enum class Direction() {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    fun turnRight() = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }

    fun turnLeft() = when (this) {
        NORTH -> WEST
        EAST -> NORTH
        SOUTH -> EAST
        WEST -> SOUTH
    }
}