package adventofcode2022.day13

import adventofcode2022.readText
import java.util.Comparator.comparing

private const val DAY = "day13"

fun main() {
    check(distressSignal(readText(DAY, "exampleInput.txt")) == 13)
  
////    // Extra test cases taken from help thread on Reddit
    check(compareOrder(parseValues("[5, 6, 6, 7, 3]"), parseValues("[5, 6, 6, 7]")) == false)
    check(compareOrder(parseValues("[[7, 6, 5], [9, 1]]"), parseValues("[[[3, 2, [1, 0, 9, 2, 7], 4, 2], [[4, 10, 3, 4], 6, [0, 4]], [], [9, [1, 0], []]], [], [5, [4, [4, 10, 9, 6, 3], 3], [[8, 2, 8], [10, 7, 7, 1], 10, [], 5], [9], 9], [0, 7, 3, 5, 10]]")) == false)
    check(compareOrder(parseValues("[[[0, 4], [[10, 8, 6, 3], 7], 3], [], [[], [[2, 5, 3], [], [6, 7, 10, 7], 5, 7]], [5, 9, [[], [4, 3], 3, [1, 4, 3]], 3], [10, 5, 3, 5, [9, 2, [9, 9], 10, [3, 8, 9, 8, 5]]]]"), parseValues("[[[6, 2, [8, 2, 4, 6]], [9, 6, [], []], [2, [9, 7, 8], [8, 8, 5, 7], [6, 7, 0, 10]], [[0, 7]], 8], [[[6], [9], 1], [[], 5]], [[7, 0, [4, 8], 10], 5, 7, 8]]")) == true)
    check(compareOrder(parseValues("[[[[6, 10, 2, 9, 8], 9], [[9, 7, 6], 4, [2, 10, 5], []], 8, 4, [[7, 1, 8], [0], [5]]]]"), parseValues("[[8, [2, [2], 8, []]], [[3, [10, 0, 8, 3, 9]]], [[4], [], [[7, 6, 8, 10], 3, [7, 6], [0, 8]]]]")) == true)
    check(compareOrder(parseValues("[[3, [7], [0], 7]]"), parseValues("[[5, 2]]")) == true)
    check(compareOrder(parseValues("[[[[6, 1, 4, 1], 8, 6, 10, [5, 3, 9, 10]], [[], 4, 0], 9], [4, 1, 7, 6], [[5, [3, 8, 1, 4, 6], 1, []], 7]]"), parseValues("[[], [7, [], [1, [6, 10, 5, 3], [1], 7, [9, 0, 5, 0, 5]], [7, 5, 4, 3], [[4, 7, 6], [], 4, [8, 9, 2, 9, 3]]], [[0, [], [8, 4, 8], 1], 4, 0, [6, [8, 10, 1, 8, 2], 3, 6]], []]")) == false)
    check(compareOrder(parseValues("[[[10, [1, 5], [1, 4, 3, 0, 8], 2, [9]], [], 3], [[[8, 0, 5], [], 2], [2], 5, [1, 6, [8, 8, 7], 5]], [10, 7, 9], []]"), parseValues("[[[[1], [8, 0, 0, 8, 1], [1], [8, 4]], [2, [], [2], 4, [1, 8, 9]], 9], [], [[4, 8, [8, 0], [10, 0, 10, 0, 0]], 1, 2, 1], [[6], 5, 6, 9, 6], [[[5], [7, 4, 9], 2, [6]], 9, [[10], 6]]]")) == false)
    check(compareOrder(parseValues("[[[[1, 7, 4, 3, 2], 4, 8, [2, 2, 6], 4], 6, 6], [3, 3, 3, 1, 5], [[1]], [10, []], [2]]"), parseValues("[[[], 3], [[], 7], [8, 0, 8], [8, 2]]")) == false)
    check(compareOrder(parseValues("[[0], [0], [[], [10, 2, 6, [], 1], 0, [[1], [6, 9, 6, 7], [], 0, 2], [6, [6, 10, 5, 5], [8, 5, 7, 4]]], [], []]"), parseValues("[[8, [[2, 6, 8, 4], 1]], [[], [], [[8, 3, 0, 8], 6], [3, [1, 3], 8]], [1, 10, [2, 2, [0, 5, 2, 9], [4, 10, 4, 8]]], [[8, 9, 6], [[], [4]]]]")) == true)
    check(compareOrder(parseValues("[[4, 2, [[4, 3], 2, [4, 5, 5, 1, 1]]], [[8, [7], [9, 7], 4, [0, 3, 8, 6]], 0], [5], [2, 2, [10, 10, [3, 6, 10, 2, 5], [], 0]]]"), parseValues("[[[[6, 3, 2, 2, 6], 1, [0, 2, 5, 9, 4], [6], [2]], 9, [7, 3]], [[[7], 7, 4, 2, 2], 5, 6, [3], 3]]")) == true)
    check(compareOrder(parseValues("[[1, 4, 6, []]]"), parseValues("[[1, 3], [2], [6, 6, 0, [5, [], 9]], [[2, [1, 4, 5], [6], [5, 10, 9, 4]], [7, 4, 6, [2, 6, 8, 9], [5, 6]], [[8], []], [[9, 5, 6], []], 8], [[0, [2, 9, 6, 3], [5, 3]]]]")) == false)
    check(compareOrder(parseValues("[[], [[], 0, 2], [9, []], [3, [[8, 4, 9, 1, 9]], [10, 3, 7], 1, [5, [10, 0, 4], [8, 8, 4, 10, 8], [6, 1, 3]]]]"), parseValues("[[0, []]]")) == true)
    check(compareOrder(parseValues("[1,1,3,1,1]"), parseValues("[1,[2,[3,[4,[5,6,0]]]],8,9]")) == true)
    check(compareOrder(parseValues("[[1],[2,3,4]]"), parseValues("[1,[2,[3,[4,[5,6,0]]]],8,9]")) == true)
    check(compareOrder(parseValues("[3]"), parseValues("[3, [4, [5, 6, 0]]]")) == true)
    check(compareOrder(parseValues("[1,1,5,1,1]"), parseValues("[[1],[2,3,4]]")) == true)
    check(compareOrder(parseValues("[[1],[2,3,4]]"), parseValues("[1,1,5,1,1]")) == false)

    val distressSignalP1 = distressSignal(readText(DAY))
    println(distressSignalP1)
    check(distressSignalP1 == 6369)
    
    check(distressSignalP2(readText(DAY, "exampleInput.txt")) == 140)
    val distressSignalP2 = distressSignalP2(readText(DAY))
    println(distressSignalP2)
    check(distressSignalP2 == 25800)
}

// TODO - test if I flatten the lists, can I just check 1 number at a time?

fun distressSignalP2(input: String): Int {
    val rawLines = input.split("\n")
        .filter { !it.isEmpty() }
        .toMutableList()
    rawLines.add("[[2]]")
    rawLines.add("[[6]]")
    val sortedLines = rawLines.map {
        parseValues(it)
    }.sortedWith(
        comparing<List<V>, List<V>>(
            { it },
            { c1, c2 ->
                val compOrder = compareOrder(c1, c2) == true
                if (compOrder == true) {
                    1
                } else {
                    -1
                }
            }
        )
    ).reversed()

    val strings = sortedLines.map {
        it.toString()
    }

    val index2 = strings.indexOf("[[2]]") + 1
    val index6 = strings.indexOf("[[6]]") + 1

    val decoderKey = index2 * index6
    println("Decoder Key: $decoderKey")
    return decoderKey
}

fun distressSignal(input: String): Int {
        
    val pairs = input.split("\n\n")
        .map { pair -> pair.split("\n") }

    var correctOrderIndexes = mutableListOf<Int>()
    for (i in 1 .. pairs.size) {
        println("== Pair $i ==")
        val (left, right) = pairs[i - 1]
        if (compareOrder(parseValues(left), parseValues(right)) == true) {
            correctOrderIndexes.add(i)
        }
    }
    val correctOrderTotal = correctOrderIndexes.sum()
    println("Correct order total: $correctOrderTotal")
    return correctOrderTotal
}

// True if in correct order, false if not
fun compareOrder(lVals: List<V>, rVals: List<V>): Boolean? {

    println("- Compare $lVals vs $rVals")
    
    for (i in 0 until lVals.size) {
        
        if (rVals.size <= i) {
            println("- Right side ran out of items, so inputs are not in the right order :: NN")
            return false
        }
        
        val lVal = lVals[i]
        val rVal = rVals[i]
        println("\t- Compare $lVal vs $rVal")

        val compareOrder = compareOrder(lVal, rVal)
        if (compareOrder != null) {
            return compareOrder
        }
    }
    
    if (lVals.size < rVals.size) {
        println("- Left side ran out of items, so inputs are in the right order")
        return true
    }
    
    return null
}

private fun compareOrder(left: V, right: V): Boolean? {

    println("- Compare $left vs $right")

    var lVal = left.intValue
    var rVal = right.intValue

    // Check exact int match
    if (lVal != null && rVal != null) {
        if (lVal < rVal) {
            println("Left side is smaller, so inputs are in the right order")
            return true
        } else if (lVal > rVal) {
            println("Right side is smaller, so inputs are not in the right order")
            return false
        } else {
            return null
        }
    }
    
    var lList = left.vs
    var rList = right.vs
    
    if (lVal != null && rVal == null) {
        println("Mixed types; convert left to [$lVal] and retry comparison")
        lList = mutableListOf(V(lVal))
    }

    if (lVal == null && rVal != null) {
        println("Mixed types; convert right to [$right] and retry comparison")
        rList = mutableListOf(V(rVal))
    }
    
    if (lList != null && lList.isEmpty() && rList != null && !rList.isEmpty()) {
        println("- Left side ran out of items, so inputs are in the right order")
        return true
    }
    
    return compareOrder(lList!!, rList!!)
}

fun parseValues(input: String): List<V> {
    var valuesString = input.substring(1, input.length - 1)
    
    if (isNumeric(valuesString)) {
        return mutableListOf(V(Integer.parseInt(valuesString)))
    }
    
    val valueStrings = mutableListOf<String>()
    
    var indexTracker = 0
    
    while (indexTracker != valuesString.length) {
        
        val str = valuesString.substring(indexTracker)
        if (str.first() == ',') {
            indexTracker++
            continue
        }
        if (str.first() == ' ') {
            indexTracker++
            continue
        }
        if (str.first() == '[') {
            
            var bracketIndex = 1
            var bracketCount = 1;
            while (bracketIndex < str.length) {
                val char = str[bracketIndex]
                if (char == '['){
                    bracketCount++
                }
                if (char == ']') {
                    bracketCount--
                    if (bracketCount == 0) {
                        break;
                    }    
                }
                bracketIndex++
            }
            
            val closingIndex = bracketIndex

            val listString = str.substring(0 .. closingIndex)
            valueStrings.add(listString)
            indexTracker += listString.length
        } else {
            val commaIndex = str.indexOf(',')
            if (commaIndex == -1) {
                valueStrings.add(str)
                break
            } else {
                val number = str.substring(0 until commaIndex)
                valueStrings.add(number)
                indexTracker += number.length
            }
        }
    }
    
    val vals = valueStrings.map {
        parseValue(it)
    }
    return vals
}

fun parseValue(value: String): V {
    if (isNumeric(value)) {
        return V(Integer.parseInt(value))
    }
    val vals = parseValues(value)
    return V(vs = vals)
}

fun isNumeric(toCheck: String): Boolean {
    if (toCheck.isEmpty()) {
        return false
    }
    return toCheck.all { char -> char.isDigit() }
}

class Packet(val vs: List<V> = mutableListOf()) {}

class V(var intValue: Int? = null, var vs: List<V>? = null) {
    
    // Generated by IntelliJ
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as V

        if (intValue != other.intValue) return false
        if (vs != other.vs) return false

        return true
    }

    override fun toString(): String {
        if (intValue != null) {
            return "$intValue"
        } else {
            return vs.toString()
        }
//        return "V(intValue=$intValue, vs=$vs)"
    }
}