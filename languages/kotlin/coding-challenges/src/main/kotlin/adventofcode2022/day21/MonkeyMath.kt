package adventofcode2022.day21

import adventofcode2022.readText

private const val DAY = "day21"



fun main() {
    check(monkeyMath(readText(DAY, "exampleInput.txt")) == 152L)
//    println(monkeyMath(readText(DAY)))

//    check(monkeyMathP2(readText(DAY, "exampleInput.txt")) == 301L)
    println(monkeyMathP2(readText(DAY)))
    
    // 83397964201949 is too high
}

fun monkeyMathP2(input: String): Long {
    val map = input.lines()
        .map { it.split(':').map { it.trim() } }
        .map { it[0] to it[1] }
        .toMap()

    val mapFiltered = map.filter { (k, v) ->
        v.toLongOrNull() == null
    }

    // TODO Try a recursive approach. 
//    val value = getValue(map, "root")
    val mapVal = map.getValue("root")

    val inputs = mapVal.split(' ')
    val first = inputs[0]
    val operator = inputs[1][0] // only care for first char
    val second = inputs[2]

    val f1 = getValue(map, first)
    val s1 = getValue(map, second) // example, doesn't contain 'humn'

    // Desired Value = 83397964201949
    val test1 = getValue(map, first, 83397964201949)

// Note -> Solved by using trial and error with the offset amounts, 
// until the gap was small enough to offset by 1 each time
    
    // TODO - Write a real algorithm for this, that actually zones in on the answer
    var result = 0L
    var humnVal = 3451534022000
    while (result != 83397964201949L) {
        result = getValue(map, first, humnVal)
        println("For input $humnVal, result was $result")
        if (result > 83397964201949L) {
            println("Result was larger")
            humnVal = humnVal + 1
        } else if (result < 83397964201949L) {
            println("Result was smaller")
            humnVal = humnVal - 1
        } else {
            return humnVal
        }
    }
    
    return f1
}

fun getValue(map: Map<String, String>, key: String, humnVal: Long? = null): Long {

    if (key == "humn" && humnVal != null) {
        return humnVal.toLong()
    }
    
    val mapVal = map.getValue(key)
    val intVal = mapVal.toLongOrNull()
    if (intVal != null) {
        return intVal
    }

    val inputs = mapVal.split(' ')
    val first = inputs[0]
    val operator = inputs[1][0] // only care for first char
    val second = inputs[2]

    val firstValue = getValue(map, first, humnVal)
    val secondValue = getValue(map, second, humnVal)
    
    return when (operator) {
        '+' -> firstValue + secondValue
        '-' -> firstValue - secondValue
        '*' -> firstValue * secondValue
        '/' -> firstValue / secondValue
        else -> { throw IllegalArgumentException("Invalid operation")}
    }
}

fun monkeyMath(input: String): Long {
    val map = input.lines()
        .map { it.split(':').map { it.trim() } }
        .map { it[0] to it[1] }
        .toMap()
    return getValue(map, "root")
}
