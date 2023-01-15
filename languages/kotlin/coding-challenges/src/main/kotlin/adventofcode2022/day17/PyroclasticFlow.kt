import adventofcode2022.readText

private const val DAY = "day17"

/**
 * TODO Test tactic of tracking the iterations of the jetstreams and positions
 * will the inputs repeat after a certain number of jet input repetitions?
 * How to check? e.g. after 3 iterations of the input, we're back at the start
 * Then you can multiply out from the modulo of the total desired iterations to start
 */
fun main() {
    val pyroclasticFlowP1Example = pyroclasticFlow(readText(DAY, "exampleInput.txt"), 2022)
    println("Pyroclastic Flow: Part 1: Example: $pyroclasticFlowP1Example")
    check(pyroclasticFlowP1Example == 3_068L)

    val pyroclasticFlowP1 = pyroclasticFlow(readText(DAY), 2022)
    check(pyroclasticFlowP1 == 3_065L)
    println(pyroclasticFlowP1)
//
//    val testIterations = 1_000_000_000_000
//    val testIterations = 1_000_000L
//    val pyroclasticFlowP2 = pyroclasticFlow(readText(DAY, "exampleInput.txt"), testIterations)
//    println("Pyroclastic Flow: Part 2: Example: $pyroclasticFlowP2")


//    check(pyroclasticFlowP2 == 1_514_285_714_288)

}

private const val COLUMNS = 7

fun pyroclasticFlow(jetPattern: String, rockIterations: Long): Long {

    var fallenRocks: MutableMap<Pos<Long>, Char> = mutableMapOf()
    for (x in 0 until COLUMNS) {
        fallenRocks.put(Pos(x.toLong(), 0L), '-')
    }
 
    var rockId = 0
    var rock = spawnRock(fallenRocks, rockId)

    var jetIndex = -1
    while (rockId < rockIterations) {

        jetIndex = (jetIndex + 1) % jetPattern.length

        when (jetPattern.get(jetIndex)) {
            '>' -> {
                moveRock(rock, xDelta = 1, fallenRocks)
            }

            '<' -> {
                moveRock(rock, xDelta = -1, fallenRocks)
            }

            else -> throw IllegalArgumentException("Invalid input")
        }
        fallRock(rock, fallenRocks)
        if (rock.atRest) {

            if (rockId % 10_000 == 0) {
                println("ROCK ID: $rockId")
            }

            if (rockId % 5 == 0) {
                fallenRocks = clearRedundantFallenRocks(fallenRocks, rock)
            }

            rock.positions.forEach {
                fallenRocks.put(it, '#')
            }

            rockId = rockId + 1
            rock = spawnRock(fallenRocks, rockId % 5)
        }
    }
    
    return fallenRocks.keys.map { it.y }.max()
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

/**
 * Given spawn location:
 * Will always follow the pattern of
 * 1. Spawn
 * 2. Jet, Fall, jet, fall, jet, fall
 * 3. Then need to check one at a time if possible
 * 
 * Instead, can we spawn 3 levels lower, and process the summary of the jets.
 * 
 */
fun spawnRock(fallenRocks: Map<Pos<Long>, Char>, rockId: Int): Rock {
    val lPos = 2L // 2 away from the chamber
    val currentMaxBottomPosition = fallenRocks.keys
        .map { it.y }
        .maxOrNull() ?: 0 
    val bPos = currentMaxBottomPosition + 4L
    
    val rockPositions: List<Pos<Long>> = when (rockId) {
        0 -> { // - shape
            listOf(
                Pos(lPos, bPos),
                Pos(lPos + 1, bPos),
                Pos(lPos + 2, bPos),
                Pos(lPos + 3, bPos)
            )        
        }
        1 -> { // + shape
            listOf(
                Pos(lPos + 1, bPos),
                Pos(lPos, bPos + 1),
                Pos(lPos + 1, bPos + 1),
                Pos(lPos + 2, bPos + 1),
                Pos(lPos + 1, bPos + 2)
            )
        }
        2 -> { // _| shape
            listOf(
                Pos(lPos, bPos),
                Pos(lPos + 1, bPos),
                Pos(lPos + 2, bPos),
                Pos(lPos + 2, bPos + 1),
                Pos(lPos + 2, bPos + 2),
            )
        }
        3 -> { // | shape
            listOf(
                Pos(lPos, bPos),
                Pos(lPos, bPos + 1),
                Pos(lPos, bPos + 2),
                Pos(lPos, bPos + 3),
            )
        }
        4 -> { // square shape
            listOf(
                Pos(lPos, bPos),
                Pos(lPos + 1, bPos),
                Pos(lPos, bPos + 1),
                Pos(lPos + 1, bPos + 1),
            )
        }
        else -> throw IllegalArgumentException("Invalid Rock ID")
    }
    
    return Rock(rockPositions)
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
