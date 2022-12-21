package adventofcode2022.day7

import adventofcode2022.readText
import java.lang.IllegalStateException

fun main() {
    val exampleInput = readText("day7", "exampleInput.txt")

    val examplePart1Result = noSpaceLeftOnDeviceP1(exampleInput)
    check(examplePart1Result == 95437)

    val input = readText("day7")
    val part1Result = noSpaceLeftOnDeviceP1(input)
    println("Puzzle output. Part 1: $part1Result")

    val examplePart2Result = noSpaceLeftOnDeviceP2(exampleInput)
    check(examplePart2Result == 24933642)

    val part2Result = noSpaceLeftOnDeviceP2(input)
    println("Puzzle output. Part 2: $part2Result")
}

private const val FILE_SYSTEM_TOTAL_SIZE = 70_000_000

private const val UPDATE_REQUIRED_SIZE = 30_000_000

fun noSpaceLeftOnDeviceP2(input: String): Int {
    val lines = input.lines()

    val root = File("/", "dir")
    val rootNode = TreeNode<File>(root)
    var currentNode = rootNode

    readInput(lines, currentNode)
    
    val totalSize = size(rootNode)
    val unusedSpace = FILE_SYSTEM_TOTAL_SIZE - totalSize
    val requiredSpace = UPDATE_REQUIRED_SIZE - unusedSpace
    var dirToDeleteSize = totalSize; // root would be worst case

    rootNode.forEachDepthFirst {
        if (it.value.type == "dir") {
            val dirSize = size(it)
            if (dirSize >= requiredSpace && dirSize <= dirToDeleteSize) {
                dirToDeleteSize = dirSize
            }
        }
    }

    return dirToDeleteSize
}

fun noSpaceLeftOnDeviceP1(input: String): Int {
    val lines = input.lines()
    
    val root = File("/", "dir")
    val rootNode = TreeNode<File>(root)
    var currentNode = rootNode

    readInput(lines, currentNode)
    
    var result = 0;

    rootNode.forEachDepthFirst {
        if (it.value.type == "dir") {
            val dirSize = size(it)
            if (dirSize <= 100_000) {
                result += dirSize
            }
        }
    }

    return result
}

private fun readInput(
    lines: List<String>,
    currentNode: TreeNode<File>
) {
    var currentNode1 = currentNode
    lines
        .drop(1) // Skip first line of cd to '/'
        .forEach {
            if (it.startsWith("$")) {
                if (it.startsWith("$ cd")) {
                    val dirName = it.substring(5) // hack, might not be right.
                    if (dirName == "..") {
                        currentNode1 = currentNode1.parent ?: throw IllegalStateException("Cannot cd to parent")
                    } else {
                        val newNode = currentNode1.children.first {
                            it.value.name == dirName
                        }
                        currentNode1 = newNode
                    }
                }
            } else {
                if (it.startsWith("dir")) {
                    val dirName = it.substring(4)
                    val dir = File(dirName, "dir")
                    currentNode1.addValueAsChild(dir)
                } else {
                    val split = it.split(" ")
                    val file = File(split[1], "file", split[0].toInt())
                    currentNode1.addValueAsChild(file)
                }
            }
        }
}

private fun size(fileNode: TreeNode<File>): Int {
    if (fileNode.value.type == "dir") {
        return fileNode.children.sumOf {
            size(it)
        }
    } else {
        return fileNode.value.size
    }
}

typealias  Visitor<T> = (TreeNode<T>) -> Unit

class TreeNode<T>(val value: T) {
    val children: MutableList<TreeNode<T>> = mutableListOf()
    var parent: TreeNode<T>? = null

    fun addValueAsChild(value: T) {
        val child = TreeNode(value)
        child.parent = this
        children.add(child)
    }

    fun forEachDepthFirst(visit: Visitor<T>) {
        visit(this)

        children.forEach {
            it.forEachDepthFirst(visit)
        }
    }

    override fun toString(): String {
        return toString(0)
    }

    private fun toString(depth: Int): String {
        var childrenString = ""
        if (!children.isEmpty()) {
            children.forEach {
                childrenString += "\n" + it.toString(depth + 1)
            }
        }
        return "\t".repeat(depth) + "$value$childrenString"
    }
}

// File can also be a directory
class File(val name: String, val type: String, var size: Int = 0) {
    override fun toString(): String {
        val sizeString = if (size == 0) "" else ", $size"
        return "$name ($type$sizeString)"
    }
}