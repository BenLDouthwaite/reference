package adventofcode2022.day12

import aocUtils.readText
import java.util.LinkedList
import java.util.Queue

fun main() {
    check(hillClimbingAlgorithmP1(readText("day12", "exampleInput.txt")) == 31)
    println(hillClimbingAlgorithmP1(readText("day12")))
    check(hillClimbingAlgorithmP2(readText("day12", "exampleInput.txt")) == 29)
    println(hillClimbingAlgorithmP2(readText("day12")))
}

fun hillClimbingAlgorithmP1(input: String): Int {
    return hillClimbingAlgorithm(input, setOf('S'))
}

fun hillClimbingAlgorithmP2(input: String): Int {
    return hillClimbingAlgorithm(input, setOf('S', 'a'))
}

fun hillClimbingAlgorithm(input: String, startingChars: Set<Char>): Int {
    var map = initMap(input)
    val startingNodes = findNodes(map, startingChars)
    return startingNodes.map {
        map = initMap(input) // Clear the map data
        it.minSteps = 0

        val processingQueue = LinkedList<Node>(listOf(it))
        while (!processingQueue.isEmpty()) {
            processQueue(processingQueue, map)
        }
        findNodes(map, setOf('E')).first().minSteps
    }.min()
}

fun findNodes(map: Array<Array<Node>>, chars: Set<Char>): MutableList<Node> {
    val startingNodes = mutableListOf<Node>()
    map.forEach {
        it.forEach { 
            if (chars.contains(it.char)) {
                startingNodes.add(it)
            }
        }
    }
    return startingNodes
}

fun processQueue(processingQueue: Queue<Node>, map: Array<Array<Node>>) {
    
    val node = processingQueue.remove()
    val x = node.x
    val y = node.y
    
    if (node.visited) {
        return
    }

    checkNode(node, x, y - 1, map, processingQueue) // up
    checkNode(node, x + 1, y, map, processingQueue) // right
    checkNode(node, x, y + 1, map, processingQueue) // down
    checkNode(node, x - 1, y, map, processingQueue) // left
    
    map[y][x].visited = true
}


private fun checkNode(currentNode: Node, x: Int, y: Int, map: Array<Array<Node>>, processingQueue: Queue<Node>) {
    if (!validCoordinates(x, y, map)) return
    
    val node = map[y][x]
    
    if (node.visited) return 
    
    val heightDiff = node.height - currentNode.height
    if (heightDiff <= 1) {
        processingQueue.add(node)
        
        val distance = currentNode.minSteps + 1
        if (node.minSteps > distance) {
            node.minSteps = distance
        }
    }
}

fun validCoordinates(x: Int, y: Int, map: Array<Array<Node>>): Boolean {
    return x in 0 until map[0].size && y in 0 until map.size
}

data class Node(val char: Char, val x: Int, val y: Int) {
    
    val height: Int = getElevationValue(char)

    private fun getElevationValue(char: Char): Int {
        if (char == 'S') {
            return 1
        }
        if (char == 'E') {
            return 26
        }
        return char.code - 96
    }

    var visited: Boolean = false
    var minSteps: Int = 999
}

private fun initMap(
    input: String,
): Array<Array<Node>> {
    val lines = input.lines()
    val height = lines.size
    val width = lines[0].length
    
    val map = Array(height) { Array(width) { Node(' ', 0, 0) } }
    for (y in 0 until height) {
        for (x in 0 until width) {
            val char = lines[y][x]
            map[y][x] = Node(char, x, y)
        }
    }
    return map
}