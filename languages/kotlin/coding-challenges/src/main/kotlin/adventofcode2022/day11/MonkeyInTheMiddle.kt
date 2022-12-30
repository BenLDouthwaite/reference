package adventofcode2022.day11

import adventofcode2022.readText

fun main() {
    check(monkeyInTheMiddle(readText("day11", "exampleInput.txt")) == 10605L)
    println(monkeyInTheMiddle(readText("day11")))
    check(monkeyInTheMiddleP2(readText("day11", "exampleInput.txt")) == 2713310158)
    println(monkeyInTheMiddleP2(readText("day11")))
}

fun monkeyInTheMiddleP2(input: String): Long {

    val monkeys = input.split("\n\n").map { parseMonkeyInput(it) }

    val monkeyDivisorProduct = monkeys.map { it.testValue }.reduce { acc, i -> acc * i }
    for (i in 1..10000) {
        processRoundP3(monkeys, monkeyDivisorProduct)
    }

    return monkeys.map { it.inspectionCount.toLong() }.sortedDescending().take(2).reduce { acc, i -> acc * i }
}

fun monkeyInTheMiddle(input: String): Long {
    val monkeys = input.split("\n\n").map { parseMonkeyInput(it) }
    for (i in 1..20) {
        processRoundP1(monkeys)
    }
    return monkeys.map { it.inspectionCount.toLong() }.sortedDescending().take(2).reduce { acc, i -> acc * i }
}

private fun processRoundP3(monkeys: List<Monkey>, monkeyDivisorProduct: Long) {
    monkeys.forEach {
        val itemsToInspect = it.items.toList() // Avoid concurrent modification issues
        itemsToInspect.forEach { item ->
            it.inspectionCount++
            val updatedValue = it.operation(item)
            val lcmOfMonkeyDivisors = updatedValue % monkeyDivisorProduct
            val targetMonkeyId = if (lcmOfMonkeyDivisors % it.testValue == 0L) it.trueTargetId else it.falseTargetId
            it.items.removeFirst()
            monkeys[targetMonkeyId].items.add(lcmOfMonkeyDivisors)
        }
    }
}

private fun processRoundP1(monkeys: List<Monkey>) {
    monkeys.forEach {
        it.items.toList().forEach { item ->
            it.inspectionCount++
            val updatedValue = it.operation(item)
            val dividedValue = updatedValue / 3
            val targetMonkeyId = if (dividedValue % it.testValue == 0L) it.trueTargetId else it.falseTargetId
            it.items.removeFirst()
            monkeys[targetMonkeyId].items.add(dividedValue)
        }
    }
}

private fun parseMonkeyInput(monkeyInput: String): Monkey {
    val lines = monkeyInput.lines()
    val monkeyNumber = lines[0][7].digitToInt()
    val items = lines[1].substring(18).split(", ").map { it.toLong() }.toMutableList()
    val testValue = lines[3].substring(21).toLong()
    val trueTargetId = lines[4].last().digitToInt()
    val falseTargetId = lines[5].last().digitToInt()

    val operationValues = lines[2].substring(19).split(" ")
    val op = if (operationValues[1] == "*") {
        { x, y -> x * y }
    } else {
        { x: Long, y: Long -> x + y }
    }
    val operationLambda: (Long) -> Long = { old: Long ->
        val operatorValue = operationValues[2]
        val opVal = if (operatorValue == "old") old else operatorValue.toLong()
        op(old, opVal)
    }

    return Monkey(monkeyNumber, items, operationLambda, testValue, trueTargetId, falseTargetId)
}

data class Monkey(
    val id: Int,
    val items: MutableList<Long>,
    val operation: (Long) -> Long,
    val testValue: Long,
    val trueTargetId: Int,
    val falseTargetId: Int
) {
    var inspectionCount: Int = 0
}