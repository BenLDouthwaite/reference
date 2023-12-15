import aocUtils.readText

private const val DAY = "day17"

/**
 * TODO Test tactic of tracking the iterations of the jetstreams and positions
 * will the inputs repeat after a certain number of jet input repetitions?
 * How to check? e.g. after 3 iterations of the input, we're back at the start
 * Then you can multiply out from the modulo of the total desired iterations to start
 */
fun main() {
//    val pyroclasticFlowP1Example = pyroclasticFlow(readText(DAY, "exampleInput.txt"), 2022)
//    println("Pyroclastic Flow: Part 1: Example: $pyroclasticFlowP1Example")
//    check(pyroclasticFlowP1Example == 3_068L)
//
//    val pyroclasticFlowP1 = pyroclasticFlow(readText(DAY), 2022)
//    check(pyroclasticFlowP1 == 3_065L)
//    println(pyroclasticFlowP1)
//
//    val testIterations = 1_000_000_000_000
//    val testIterations = 2022L
//
//    val pyroclasticFlowP2Example = pyroclasticFlow(
//        readText(DAY, "exampleInput.txt"), 
//        testIterations,
//        1_000_000_000_000
//    )
//    println("Pyroclastic Flow: Part 2: Example: $pyroclasticFlowP2Example")
//
//    check(pyroclasticFlowP2Example == 1_514_285_714_288)

    val testIterations = 10_000L

    val pyroclasticFlowP2 = pyroclasticFlow(
        readText(DAY),
        testIterations,
        1_000_000_000_000 - 1
    )
    println("Pyroclastic Flow: Part 2: Example: $pyroclasticFlowP2")

    check(pyroclasticFlowP2 == 1_514_285_714_288)
 
    // TODO My algorithm is producing results that are consistently off by one (too high. Not a clue why...)
}

private const val COLUMNS = 7

fun pyroclasticFlow(jetPattern: String, rockIterations: Long, targetRockId: Long = rockIterations): Long {
    
    val restRocksByTypeAndXPositionToYPositions : MutableMap<Pair<Long, Long>, MutableList<Long>> = mutableMapOf()

    var fallenRocks: MutableMap<Pos<Long>, Char> = mutableMapOf()
    for (x in 0 until COLUMNS) {
        fallenRocks.put(Pos(x.toLong(), 0L), '-')
    }
 
    var rockId = 0L
    var jetIndex = -1
    
    var rock = spawnRock(fallenRocks, rockId, jetPattern, jetIndex)
    jetIndex = (jetIndex + 3) % jetPattern.length

    while (rockId < rockIterations) {

        jetIndex = (jetIndex + 1) % jetPattern.length

        processJet(jetPattern, jetIndex, rock, fallenRocks)
        fallRock(rock, fallenRocks)
        if (rock.atRest) {

            if (rockId % 10_000 == 0L) {
                println("ROCK ID: $rockId")
            }

            if (rockId % 5 == 0L) {
                fallenRocks = clearRedundantFallenRocks(fallenRocks, rock)
            }

            val rockBasePosition = rock.positions.first()
//            val rockIdToXPos = Pair(rockId % 5, rockBasePosition.x.toInt())
            val rockIdToXPos = Pair(rockId, rockBasePosition.x)
            val list = restRocksByTypeAndXPositionToYPositions.getOrPut(rockIdToXPos, { mutableListOf<Long>() })
            list.add(rockBasePosition.y)

            rock.positions.forEach {
                fallenRocks.put(it, '#')
            }

            rockId = rockId + 1
            rock = spawnRock(fallenRocks, rockId % 5, jetPattern, jetIndex)
            jetIndex = (jetIndex + 3) % jetPattern.length

        }
    }
    
    
    val lineRocksAtXPosition1 = restRocksByTypeAndXPositionToYPositions.filter { (k, v) -> 
        k.first % 5 == 0L && k.second == 1L
    }.toMap()
    
    val list = lineRocksAtXPosition1.values.flatMap { it }
    println("Find repeating pattern for rock type 0 at X position 0 ")
    for (i in 3 .. list.size / 2) {
        println("")
        var chunked = list.chunked(i)

        // TODO for some reason, first and last are a pain so just drop them..
        chunked = chunked.drop(1).dropLast(1)
        
//        val uChunked = chunked
        if (chunked.size == 1) {
            continue
        }
        
        val chunkedDiffs = chunked.map { chunk ->
            val items = mutableListOf<Long>()
            for (item in 0 .. chunk.size - 2) {
                val diff = chunk[item + 1] - chunk[item] 
                items.add(diff)
            }
            items
        }
        
        println("Chunked Diffs: $chunkedDiffs")

        if (chunkedDiffs.isEmpty()) {
            continue
        }
        
        val comp = chunkedDiffs.get(0)
        val allEqual = chunkedDiffs.all { it.equals(comp) }
        
        println("For window of $i, pattern = $allEqual")
        
        if (allEqual) {
            println("Chunks $chunked. With pattern of each being: $comp")

            // TODO Would be able to reduce this down, down need all the values
            val filter4List = lineRocksAtXPosition1.map { (k, v) ->
                Pair(k.first, v.first())
            }.drop(i)
                .filterIndexed { index, value ->
                    index % (i) == 0
                }

            println("F3M: $filter4List")

            val base = filter4List.get(0)
            val next = filter4List.get(1)
            val rockIdDiff = next.first - base.first
            val heightDiff = next.second - base.second

            var newRockId: Long = base.first.toLong()

            val x = (targetRockId - newRockId) / rockIdDiff

            val highestRockIdBelowTarget = (x * rockIdDiff) + newRockId
            val scoreAtHighestRockBelowTarget = (x * heightDiff) + base.second

            val remainingRocksToLetFall = targetRockId - highestRockIdBelowTarget
            val rockToCheck = base.first + remainingRocksToLetFall

            // TODO Need to update this data type to make sure it;s the same wind index position too, I think.
            val rockToCheckValue = restRocksByTypeAndXPositionToYPositions.filter { (k, _) ->
                k.first == rockToCheck
            }.values.first().first()
            
            val remainingRocksToCheckOffset = rockToCheckValue - base.second

            val totalHeight = scoreAtHighestRockBelowTarget + remainingRocksToCheckOffset
            
            // TODO Can update these for debugging
            for (j in newRockId .. 1800 step rockIdDiff) {
                println("TEST: $j")
                // TODO Check that all of these , up to 'remainingRocksToLetFall' have the same difference, 
                val checkId = j + remainingRocksToLetFall

                val jVal = restRocksByTypeAndXPositionToYPositions.filter { (k, _) ->
                    k.first == j
                }.values.first().first()

                val checkVal = restRocksByTypeAndXPositionToYPositions.filter { (k, _) ->
                    k.first == checkId
                }.values.first().first()
                
                val remainingRocksToCheckOffset2 = checkVal - jVal
                
                println("Start: $j, Check: $checkId, val: $remainingRocksToCheckOffset2")
                // if not, need to take that into considerations. 
//                val a = restRocksByTypeAndXPositionToYPositions
            }

            return totalHeight.toLong()
        }
    }
    
    throw RuntimeException("Couldn't find pattern")

//    return fallenRocks.keys.map { it.y }.max()
}

private fun processJet(
    jetPattern: String,
    jetIndex: Int,
    rock: Rock,
    fallenRocks: MutableMap<Pos<Long>, Char>
) {
    when (jetPattern.get(jetIndex)) {
        '>' -> {
            moveRock(rock, xDelta = 1, fallenRocks)
        }

        '<' -> {
            moveRock(rock, xDelta = -1, fallenRocks)
        }

        else -> throw IllegalArgumentException("Invalid input")
    }
}

fun clearRedundantFallenRocks(fallenRocks: MutableMap<Pos<Long>, Char>, rock: Rock): MutableMap<Pos<Long>, Char> {
    
    val currentMaxBottomPosition = fallenRocks.keys
        .map { it.y }
        .maxOrNull() ?: 0
    if (currentMaxBottomPosition < 100) {
        return fallenRocks // Shortcut to avoid going below 0
    }
    
    val filteredMap = fallenRocks.filter { (k, _) ->
        k.y >= (currentMaxBottomPosition - 40)
    }.toMutableMap()
    return filteredMap
}

fun fallRock(rock: Rock, fallenRocks: MutableMap<Pos<Long>, Char>) {
    
    val canMoveY = rock.positions.all {
        !fallenRocks.contains(Pos(it.x, it.y - 1))
    }
    if (canMoveY) {
        rock.positions = rock.positions.map {
            Pos(it.x, it.y -1)
        }
    } else {
        rock.atRest = true
    }
}

fun moveRock(rock: Rock, xDelta: Int = 0, fallenRocks: MutableMap<Pos<Long>, Char>) {
    val canMoveX = rock.positions.all {
        it.x + xDelta in 0 until COLUMNS &&
        !fallenRocks.contains(Pos(it.x + xDelta, it.y))
    }
    if (canMoveX) {
        rock.positions = rock.positions.map {
            Pos(it.x + xDelta, it.y)
        }
    }
}

fun spawnRock(fallenRocks: MutableMap<Pos<Long>, Char>, rockId: Long, jetPattern: String, jetIndex: Int): Rock {
    val lPos = 2L // 2 away from the chamber
    val currentMaxBottomPosition = fallenRocks.keys
        .map { it.y }
        .maxOrNull() ?: 0 
    val bPos = currentMaxBottomPosition + 1L

    val rockPositions: List<Pos<Long>> = getRockPositions(rockId, lPos, bPos)

    val rock = Rock(rockPositions)
    
//    // TODO How to refactor these to process all 3 at once?
//    val xDelta = listOf(
//        (jetIndex + 1) % jetPattern.length,
//        (jetIndex + 2) % jetPattern.length,
//        (jetIndex + 3) % jetPattern.length
//    ).map {
//        when (jetPattern.get(it)) {
//            '>' -> 1
//            '<' -> -1
//            else -> throw IllegalArgumentException("Invalid input")
//        }
//    }.sum()
//    
//    for (i in 1 .. xDelta.absoluteValue) {
//        moveRock(rock, xDelta.sign, fallenRocks)
//    }
    
    var jetIndex = (jetIndex + 1) % jetPattern.length
    processJet(jetPattern, jetIndex, rock, fallenRocks)

    jetIndex = (jetIndex + 1) % jetPattern.length
    processJet(jetPattern, jetIndex, rock, fallenRocks)

    jetIndex = (jetIndex + 1) % jetPattern.length
    processJet(jetPattern, jetIndex, rock, fallenRocks)

    return rock
}

// Essentially a pair, but reference x and y instead of first and second.
data class Pos<T>(val x: T, val y: T)

data class Rock(
    var positions: List<Pos<Long>>,
    var atRest: Boolean = false
)

fun printChamber(fallenRocks: MutableMap<Pos<Long>, Char>, rock: Rock) {
    val columns = COLUMNS
    val rows = rock.positions.map { it.y }.max()

    //    val rowsMin = 1L
    val rowsMin = rows - 20
    for (r in rows downTo rowsMin) {
        print("|")
        for (c in 0L until columns) {
            val pos = Pos(c, r)
            if (fallenRocks.contains(pos)) {
                print(fallenRocks.get(pos))
            } else if (rock.positions.contains(pos)) {
                print('@')
            } else {
                print(".")
            }
        }
        print("|\n")
    }
    
    // Final 'bed' = row 0
    print("+")
    for (c in 0 until columns) {
        print("-")
    }
    print("+\n")
    println()
}

private fun getRockPositions(rockId: Long, lPos: Long, bPos: Long): List<Pos<Long>> {
    val rockPositions: List<Pos<Long>> = when (rockId) {
        0L -> { // - shape
            listOf(
                Pos(lPos, bPos),
                Pos(lPos + 1, bPos),
                Pos(lPos + 2, bPos),
                Pos(lPos + 3, bPos)
            )
        }

        1L -> { // + shape
            listOf(
                Pos(lPos + 1, bPos),
                Pos(lPos, bPos + 1),
                Pos(lPos + 1, bPos + 1),
                Pos(lPos + 2, bPos + 1),
                Pos(lPos + 1, bPos + 2)
            )
        }

        2L -> { // _| shape
            listOf(
                Pos(lPos, bPos),
                Pos(lPos + 1, bPos),
                Pos(lPos + 2, bPos),
                Pos(lPos + 2, bPos + 1),
                Pos(lPos + 2, bPos + 2),
            )
        }

        3L -> { // | shape
            listOf(
                Pos(lPos, bPos),
                Pos(lPos, bPos + 1),
                Pos(lPos, bPos + 2),
                Pos(lPos, bPos + 3),
            )
        }

        4L -> { // square shape
            listOf(
                Pos(lPos, bPos),
                Pos(lPos + 1, bPos),
                Pos(lPos, bPos + 1),
                Pos(lPos + 1, bPos + 1),
            )
        }

        else -> throw IllegalArgumentException("Invalid Rock ID")
    }
    return rockPositions
}
