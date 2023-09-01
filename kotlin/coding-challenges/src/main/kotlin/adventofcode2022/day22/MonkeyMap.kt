package adventofcode2022.day22

import adventofcode2022.day22.Direction.*
import adventofcode2022.readText

private const val DAY = "day22"

private const val DEBUG = false

fun main() {
//    check(monkeyMap(readText(DAY, "exampleInput.txt")) == 6032)
//    println(monkeyMap(readText(DAY)))

    check(monkeyMap(readText(DAY, "exampleInput.txt"), true) == 5031)
    check(monkeyMap(readText(DAY, "exampleInput2.txt")) == 1206)
    println(monkeyMap(readText(DAY)))
    
}

fun monkeyMap(input: String, isExample: Boolean = false): Int {
//    println(input)
    val (mapInput, commands) = input.split("\n\n")
//    println("Map: $mapInput")
//    println("Commands: $commands")
    val commandsList = getCommandsList(commands)

    val map = parseMap(mapInput)
    
    val mapXRanges = getXRanges(map)
    val mapYRanges = getYRanges(map)
    
    var curPos = getStartingPos(map)
    var direction = EAST
    
    if (DEBUG) {
        println("Starting pos = $curPos, direction = $direction")
    }

    commandsList.forEach {
        val (command, steps) = it
        
//        println("\n Processing command $command, for $steps, starting at $curPos, facing $direction\n")
                
        when (command) {
            'R' -> {
//                println("Turn right")
//                print("Turn right from $direction - ")
                direction = direction.turnRight()
//                println("to $direction")
            }
            'L' -> {
//                println("Turn left")
//                print("Turn left from $direction - ")
                direction = direction.turnLeft()
//                println("to $direction")
            }
            'F' -> {
//                println("Move forward $steps spaces")
                
                println("Currently at $curPos. Move $steps steps ($direction)")
//                println("Currently at $curPos, facing $direction")

                var keepMoving = true
                for (i in 1 .. steps!!) {
//                repeat(steps!!) {
                    if (keepMoving) {
                        var nextPos = getNextPosition(curPos, direction)
                        var nextDirection = direction

                        if (!map.containsKey(nextPos)) {
                            println("Wrap position : X=${nextPos.x}, y=${nextPos.y}")

                            val (wrappedPos, newDirection) = when {
                                isExample -> {
                                    getWrappedPosAndDirectionExample(curPos, direction)
                                }
                                else -> {
                                    getWrappedPosAndDirection(curPos, direction)
                                }
                            }

                            nextPos = wrappedPos
                            nextDirection = newDirection
                            println("Next position : X=${nextPos.x}, y=${nextPos.y}. Facing ($newDirection)")
                        }

                        when (map.getValue(nextPos)) {
                            '#' -> {
//                                println("Next step $nextPos is a wall. Stop")
                                keepMoving = false
                            }
                            else -> {
                                curPos = nextPos
                                direction = nextDirection
//                                println("Move forward to: $curPos")
                            }
                        }
                    }
                }
            }
        }
    }

//    printMap(map)
    
    val column = curPos.x
    val row = curPos.y
    val facing = when (direction) {
        NORTH -> 3
        EAST -> 0
        SOUTH -> 1
        WEST -> 2
    }
    val result = (1000 * (row + 1)) + (4 * (column + 1)) + facing
//    println("Result = $result")
    return result
}

private fun getNextPosition(
    curPos: Pos,
    direction: Direction
): Pos {
    val offset = when (direction) {
        NORTH -> Pos(0, -1)
        EAST -> Pos(1, 0)
        SOUTH -> Pos(0, 1)
        WEST -> Pos(-1, 0)
    }

    return Pos(curPos.x + offset.x, curPos.y + offset.y)
}

fun getWrappedPosAndDirectionExample(curPos: Pos, direction: Direction): Pair<Pos, Direction> {
    val GW = 4
    val gw = 4
    val g0s = 0
    val g0e = GW - 1
    val g1s = GW
    val g1e = (GW * 2) - 1
    val g2s = GW * 2
    val g2e = (GW * 3) - 1
    val g3s = GW * 3
    val g3e = (GW * 4 - 1)

    // get block
    val (cx, cy) = curPos

    val xb = curPos.x / GW
    val yb = curPos.y / GW
    
    val curBlock = Pos(xb, yb)
    when (curBlock) {
        Pos(2, 0) -> {
            when (direction) {
                NORTH -> { return Pair(Pos(g2e - cx, g1s), SOUTH) }
                EAST -> { return Pair(Pos(g3e, g2e - cy), WEST) }
                WEST -> { return Pair(Pos(g1s + cy, g1s), SOUTH) }
                else -> { throw IllegalArgumentException("Can't wrap in this direction") }
            }
        }
        Pos(0,1) -> {
            when (direction) {
                NORTH -> { return Pair(Pos(g2e - cx, g0s), SOUTH) }
                SOUTH -> { return Pair(Pos(g2e - cx, g2e), NORTH) }
                WEST -> { return Pair(Pos(g3e - (cy - gw) , g2e), NORTH) }
                else -> { throw IllegalArgumentException("Can't wrap in this direction") }
            }
        }
        Pos(1, 1) -> {
            when (direction) {
                NORTH -> { return Pair(Pos(g2s, cx - gw), EAST) }
                SOUTH -> { return Pair(Pos(g2s, g2s + (g1e - cx)), EAST) }
                else -> { throw IllegalArgumentException("Can't wrap in this direction") }
            }
        }
        Pos(2, 1) -> {
            when (direction) {
                EAST -> { return Pair(Pos(g3s + (g1e - cy), g2s), SOUTH) }
                else -> { throw IllegalArgumentException("Can't wrap in this direction") }
            }
        }
        Pos(2, 2) -> {
            when (direction) {
                SOUTH -> { return Pair(Pos(g2e - cx, g1e), NORTH) }
                WEST -> { return Pair(Pos(g1s + (g2e - cy), g1e), NORTH) }
                else -> { throw IllegalArgumentException("Can't wrap in this direction") }
            }
        }
        Pos(3, 2) -> {
            when (direction) {
                NORTH -> { return Pair(Pos(g2e, g1s + (g3e - cx)), WEST) }
                EAST -> { return Pair(Pos(g2e, g2e - cy), WEST) }
                SOUTH -> { return Pair(Pos(0, g1s + (g3e - cx)), EAST) }
                else -> { throw IllegalArgumentException("Can't wrap in this direction") }
            }
        }
    }
    throw IllegalArgumentException("Should have returned")
}

fun getWrappedPosAndDirection(curPos: Pos, direction: Direction): Pair<Pos, Direction> {
    val gridWidth = 50

    // get block
    val (cx, cy) = curPos

    val xb = curPos.x / gridWidth
    val yb = curPos.y / gridWidth
    
    val curBlock = Pos(xb, yb)
    when (curBlock) {
        Pos(2, 0) -> {
            when (direction) {
                NORTH -> { return Pair(Pos(cx - 100, 199), NORTH) }
                EAST -> { return Pair(Pos(99, 100 + (49 - cy)), WEST) }
                SOUTH -> { return Pair(Pos(99, 50 + (cx - 100)), WEST) }
                else -> { throw IllegalArgumentException("Can't wrap in this direction") }
            }
        }
        Pos(1, 0) -> {
            when (direction) {
                NORTH -> { return Pair(Pos(0, cx + 100), EAST) }
                WEST -> { return Pair(Pos(0, 100 + (49 - cy)), EAST) }
                else -> { IllegalArgumentException("Invalid input for Current Pos $curPos and direction $direction")}
            }
        }
        Pos(1, 1) -> {
            when (direction) {
                EAST -> { return Pair(Pos(50 + cy, 49), NORTH) }
                WEST -> { return Pair(Pos(cy - 50, 100), SOUTH) }
                else -> { IllegalArgumentException("Invalid input for Current Pos $curPos and direction $direction")}
            }
        }
        Pos(1, 2) -> {
            when (direction) {
                EAST -> { return Pair(Pos(149, 149 - cy), WEST) }
                SOUTH -> { return Pair(Pos(49, 100 + cx), WEST) }
                else -> { IllegalArgumentException("Invalid input for Current Pos $curPos and direction $direction")}
            }
        }
        Pos(0, 2) -> {
            when (direction) {
                WEST -> { return Pair(Pos(50, (149 - cy)), EAST) }
                NORTH -> { return Pair(Pos(50, 50 + (cx)), EAST) }
                else -> { IllegalArgumentException("Invalid input for Current Pos $curPos and direction $direction")}
            }
        }
        Pos(0, 3) -> {
            when (direction) {
                EAST -> { return Pair(Pos(50 + (cy - 150), 149), NORTH) }
                SOUTH -> { return Pair(Pos(100 + cx, 0), SOUTH) }
                WEST -> { return Pair(Pos(cy - 100, 0), SOUTH) }
                else -> { IllegalArgumentException("Invalid input for Current Pos $curPos and direction $direction")}
            }
        }
    }
    throw IllegalArgumentException("Should have returned")
}

// TODO How to combine with YRanges function?
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
            '\n' -> { return commandsList }
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

// Get the coords of next tile after wrapping, 
// P1 implementation. 
//                            val wrappedPos = when (direction) {
//                                NORTH -> Pos(curPos.x, mapYRanges.getValue(curPos.x).max())
//                                EAST -> Pos(mapXRanges.getValue(curPos.y).min(), curPos.y)
//                                SOUTH -> Pos(curPos.x, mapYRanges.getValue(curPos.x).min())
//                                WEST -> Pos(mapXRanges.getValue(curPos.y).max(), curPos.y)
//                            }