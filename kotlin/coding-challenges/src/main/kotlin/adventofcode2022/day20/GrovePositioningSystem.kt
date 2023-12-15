package adventofcode2022.day20

import aocUtils.readText

private const val DAY = "day20"

fun main() {
    check(grovePositioningSystemP2(readText(DAY, "exampleInput.txt")) == 3L)
    println(grovePositioningSystemP2(readText(DAY)))

    val decryptionKey = 811589153
    check(grovePositioningSystemP2(readText(DAY, "exampleInput.txt"), decryptionKey, 10) == 1623178306L)
    println(grovePositioningSystemP2(readText(DAY), decryptionKey, 10))
}

fun grovePositioningSystemP2(input: String, decryptionKey: Int = 1, mixRepeat: Int = 1): Long {

    val originalValues = input.lines().map { it.toLong() }

    val values = originalValues.mapIndexed { index, value -> Pair(value * decryptionKey, index) }.toMutableList()

    repeat(mixRepeat, { mix(values, originalValues) })

    val startingIndex = values.indexOf(values.first { it.first == 0L })
    return listOf(1000, 2000, 3000).map {
        values.get((it + startingIndex).mod(values.size)).first
    }.sum()
}

private fun mix(
    values: MutableList<Pair<Long, Int>>,
    originalValues: List<Long>
) {
    for (i in 0 until values.size) {
        val itemToMove = values.first { it.second == i }
        val positionInList = values.indexOf(itemToMove)
        val indexToInsertAt = (positionInList + itemToMove.first).mod(originalValues.size - 1) // 0 based index
        values.remove(itemToMove)
        values.add(indexToInsertAt, itemToMove)
    }
}