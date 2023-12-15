package adventofcode2022.day9

import aocUtils.readText
import kotlin.math.sign

fun main() {
    val exampleInput = readText("day9", "exampleInput.txt")

    val examplePart1Result = ropeBridge(exampleInput)
    check(examplePart1Result == 13)

    val input = readText("day9")
    val part1Result = ropeBridge(input)
    println("Puzzle output. Part 1: $part1Result")

    val examplePart2Result = ropeBridgeP2(exampleInput)
    check(examplePart2Result == 1)

    val largerExampleInput = readText("day9", "largerExampleInput.txt")

    val largerExamplePart2Result = ropeBridgeP2(largerExampleInput)
    println("Larger Example Input. Result: $largerExamplePart2Result")
    check(largerExamplePart2Result == 36)
    
    val part2Result = ropeBridgeP2(input)
    println("Puzzle output. Part 2: $part2Result")
}

fun ropeBridgeP2(input: String): Int {
    var head = processRopePath(input, 9)
    var endOfRope = head
    while (endOfRope.tail != null){
        endOfRope = endOfRope.tail ?: break
    }

    return endOfRope.visitedPoints.size
}

fun ropeBridge(input: String): Int {
    val head = processRopePath(input, 1)
    return head.tail?.visitedPoints?.size ?: -1
}

private fun processRopePath(input: String, tailCount: Int): RopeNode {
    val head = RopeNode(0, 0, "H")
    var endOfRope = head
    for (i in 1..tailCount) {
        val tail = RopeNode(0, 0, Integer.toString(i))
        endOfRope.tail = tail
        endOfRope = tail
    }

    input.lines().forEach {
        val commandSplit = it.split(" ")
        val command = commandSplit[0]
        val weight = commandSplit[1].toInt()

        repeat(weight) {
            if (command == "R") {
                head.move(1, 0)
            }
            if (command == "L") {
                head.move(-1, 0)
            }
            if (command == "U") {
                head.move(0, 1)
            }
            if (command == "D") {
                head.move(0, -1)
            }
        }
//        printRope(head)
    }
    return head
}

class RopeNode(var x: Int, var y: Int, val name: String) {
    var tail: RopeNode? = null

    var visitedPoints = mutableSetOf<Pair<Int, Int>>(Pair(x, y))

    fun move(xd: Int, yd: Int) {
        x += xd
        y += yd
        tail?.follow(this)
    }

    private fun follow(head: RopeNode) {
        val xD = head.x - x
        val yD = head.y - y
        if (xD == 2 || xD == -2 || yD == 2 || yD == -2) {
            move(xD.sign, yD.sign)
        }
        visitedPoints.add(Pair(x, y))
    }

}

// Just for debugging during dev - a hot mess but useful.
fun printRope(head: RopeNode) {

    val length = 40;
    val offset = 15 // random guess

    val grid = Array(length) { Array(length) { "." } }
    var currentNode = head
    while (true) {
        // use an offset to index to negatives
        val currentValue = grid[currentNode.y + offset][currentNode.x + offset]
        if (currentValue == ".") {
            grid[currentNode.y + offset][currentNode.x + offset] = currentNode.name
        } else {
            if (currentValue == "H") {
            } else {
                if (currentValue.toInt() > currentNode.name.toInt()) {
                    grid[currentNode.y + offset][currentNode.x + offset] = currentNode.name
                }
            }
        }
        currentNode = currentNode.tail ?: break
    }
    grid.reverse() // print showing axes increasing from bottom to top (invert y coordinates for printin)
    grid.forEach {
        it.forEach {
            print(it)
        }
        println()
    }
    println()
}
