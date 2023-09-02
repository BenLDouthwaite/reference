package adventofcode2022.day8

import adventofcode2022.readText

fun main() {
    val exampleInput = readText("day8", "exampleInput.txt")

    val examplePart1Result = treetopTreeHouse(exampleInput)
    check(examplePart1Result == 21)

    val input = readText("day8")
    val part1Result = treetopTreeHouse(input)
    println("Puzzle output. Part 1: $part1Result")

    val examplePart2Result = treetopTreeHouseP2(exampleInput)
    check(examplePart2Result == 8)

    val part2Result = treetopTreeHouseP2(input)
    println("Puzzle output. Part 2: $part2Result")
}

private const val VISIBLE = 1
private const val UNKNOWN = -1
private const val NOT_VISIBLE = 0

fun treetopTreeHouseP2(input: String): Int {

    val length = input.split("\n")[0].length

    val treeGrid = initGrid(input)

    var maxScenicScore = 0
    for (i in 1 until length - 1) {
        for (j in 1 until length - 1) {
            val scenicScore = measureScenicScore(treeGrid, i, j)
            if (scenicScore > maxScenicScore) {
                maxScenicScore = scenicScore
            }
        }
    }
    return maxScenicScore
}

fun measureScenicScore(treeGrid: Array<Array<Int>>, i: Int, j: Int): Int {

    val tree = treeGrid[i][j]

    val upCoordsToCheck = (i - 1 downTo  0).map { Pair(it, j) }
    val leftCoordsToCheck = (j - 1 downTo  0).map { Pair(i, it) }
    val rightCoordsToCheck = (j + 1 until treeGrid.size).map { Pair(i, it) }
    val downCoordsToCheck = (i + 1 until treeGrid.size).map { Pair(it, j) }

    val result = listOf(
        upCoordsToCheck,
        leftCoordsToCheck,
        rightCoordsToCheck,
        downCoordsToCheck
    ).map { directionScore(it, treeGrid, tree) }
        .reduce { acc, score ->  acc * score }
    return result
}

private fun directionScore(
    coordinatesToCheck: List<Pair<Int, Int>>,
    treeGrid: Array<Array<Int>>,
    tree: Int,
): Int {
    var score = 0
    for (coordinates in coordinatesToCheck) {
        score++
        if (treeGrid[coordinates.first][coordinates.second] >= tree) {
            break
        }
    }
    return score
}

fun treetopTreeHouse(input: String): Int {

    val length = input.split("\n")[0].length
    
    val treeGrid = initGrid(input)
    
    val visibilityGrid = Array(length) { Array(length) { UNKNOWN } }
    
    // Set outer grid to visible.
    for (i in 0 until length) {
        visibilityGrid[0][i] = VISIBLE
        visibilityGrid[length - 1][i] = VISIBLE
        visibilityGrid[i][0] = VISIBLE
        visibilityGrid[i][length - 1] = VISIBLE
    }

    for (i in 1 until length - 1) {
        for (j in 1 until length - 1) {
            visibilityGrid[i][j] = getVisibility(treeGrid, i, j)
        }
    }

    val result = visibilityGrid.sumOf { 
        it.sumOf { it }
    }
    
    return result
}

private fun getVisibility(
    treeGrid: Array<Array<Int>>,
    i: Int,
    j: Int,
): Int {
    val tree = treeGrid[i][j]
    
    val upCoordsToCheck = (i - 1 downTo  0).map { Pair(it, j) }
    val leftCoordsToCheck = (j - 1 downTo  0).map { Pair(i, it) }
    val rightCoordsToCheck = (j + 1 until treeGrid.size).map { Pair(i, it) }
    val downCoordsToCheck = (i + 1 until treeGrid.size).map { Pair(it, j) }

    val directionalCoordinatesToCheck = listOf(
        upCoordsToCheck,
        leftCoordsToCheck,
        rightCoordsToCheck,
        downCoordsToCheck
    )
    
    if (directionalCoordinatesToCheck.any {
        it.map { treeGrid[it.first][it.second] }.all { it < tree } 
    }) {
        return VISIBLE
    }
    
    return NOT_VISIBLE
}

private fun initGrid(
    input: String,
): Array<Array<Int>> {
    val length = input.split("\n")[0].length
    val lines = input.lines()
    val treeGrid = Array(length) { Array(length) {0} }
    for (i in 0 until length) {
        for (j in 0 until length) {
            treeGrid[i][j] = lines[i][j].digitToInt()
        }
    }
    return treeGrid
}

private fun printGrid(treeGrid: Array<Array<Int>>) {
    treeGrid.forEach {
        it.forEach {
            print(it)
        }
        println()
    }
}


