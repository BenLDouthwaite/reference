package adventofcode2022.day18

import aocUtils.readText
import java.util.LinkedList

private const val DAY = "day18"

fun main() {
    val boilingBoulders = boilingBoulders(readText(DAY, "exampleInput.txt"))
    println(boilingBoulders)
    check(boilingBoulders == 64)

    println(boilingBoulders(readText(DAY)))

    check(boilingBouldersP2(readText(DAY, "exampleInput.txt")) == 58)
    
    println(boilingBouldersP2(readText(DAY)))
}

data class Pos<T>(val x: T, val y: T, val z: T)

fun boilingBouldersP2(input: String): Int {

    val lavaPositions = input.lines()
        .map {
            val (x, y, z) = it.split(',').map { it.toInt() }
            Pos(x, y, z)
        }
        .toSet()

    val minX = lavaPositions.minBy { it.x }.x - 1
    val maxX = lavaPositions.maxBy { it.x }.x + 1
    val minY = lavaPositions.minBy { it.y }.y - 1
    val maxY = lavaPositions.maxBy { it.y }.y + 1
    val minZ = lavaPositions.minBy { it.z }.z - 1
    val maxZ = lavaPositions.maxBy { it.z }.z + 1

    val xRange = minX..maxX
    val yRange = minY .. maxY
    val zRange = minZ .. maxZ
    val steamPositions: MutableMap<Pos<Int>, Boolean> = mutableMapOf()
    
    println("MinX $minX, MaxX $maxX, MinY $minY, MaxY $maxY, MinZ $minZ, MaxZ $maxZ")

    val processingQueue = LinkedList<Pos<Int>>(listOf(Pos(minX, minY, minZ)))
    while (!processingQueue.isEmpty()) {
        val pos = processingQueue.remove()
        checkPosition(steamPositions, lavaPositions, pos, xRange, yRange, zRange, processingQueue)
    }

    val count = lavaPositions.map {
        val (x, y, z) = it
        listOf(
            steamPositions.contains(Pos(x + 1, y, z)),
            steamPositions.contains(Pos(x - 1, y, z)),
            steamPositions.contains(Pos(x, y + 1, z)),
            steamPositions.contains(Pos(x, y - 1, z)),
            steamPositions.contains(Pos(x, y, z + 1)),
            steamPositions.contains(Pos(x, y, z - 1))
        ).count { it == true }
    }.sum()
    
    println(count)
    return count
}

fun checkPosition(
    steamPositions: MutableMap<Pos<Int>, Boolean>,
    lavaPositions: Set<Pos<Int>>,
    pos: Pos<Int>,
    xRange: IntRange,
    yRange: IntRange,
    zRange: IntRange,
    processingQueue: LinkedList<Pos<Int>>
) {
    
    val (x, y, z) = pos

    steamPositions.put(pos, true)

    listOf(
        Pos(x + 1, y, z),
        Pos(x - 1, y, z),
        Pos(x, y + 1, z),
        Pos(x, y - 1, z),
        Pos(x, y, z + 1),
        Pos(x, y, z - 1)
    ).forEach {
        if (it.x in xRange && it.y in yRange && it.z in zRange 
            && !lavaPositions.contains(it) 
            && !steamPositions.contains(it) 
            && !processingQueue.contains(it)) // Could probably use a set as a queue for this for quicker lookup. 
        {
            processingQueue.add(it)
        }
    }
}

fun boilingBoulders(input: String): Int {
    val positions = input.lines()
        .map {
            val (x, y, z) = it.split(',').map { it.toInt() }
            Pos(x, y, z)
        }
        .toSet()

    val count = positions.map {
        val (x, y, z) = it
        listOf(
            !positions.contains(Pos(x + 1, y, z)),
            !positions.contains(Pos(x - 1, y, z)),
            !positions.contains(Pos(x, y + 1, z)),
            !positions.contains(Pos(x, y - 1, z)),
            !positions.contains(Pos(x, y, z + 1)),
            !positions.contains(Pos(x, y, z - 1))
        ).count { it == true }
    }.sum()

    return count
}