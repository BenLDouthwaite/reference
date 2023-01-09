import adventofcode2022.readText
import java.lang.IllegalStateException
import java.util.LinkedList

private const val DAY = "day16"

fun main() {
    val proboscideaVolcanium = proboscideaVolcanium(readText(DAY, "exampleInput.txt"))
    println(proboscideaVolcanium)
    check(proboscideaVolcanium == 1651)

//    val part1 = proboscideaVolcanium(readText(DAY))
//    println(part1)
    
//    check(proboscideaVolcanium(readText(DAY, "exampleInput.txt")) == 0)
//    println(proboscideaVolcanium(readText(DAY)))
}

fun proboscideaVolcanium(input: String): Int {
    
    val valves = parseValves(input)

    val pathsBetweenValvesMap = getPathsBetweenValuesMap(valves)
    
    // precalculate all possible routes between nodes with a value.
    val flowValves = valves.values
        .filter { it.flow != 0 }

//    var maxScore = permutationBasedSolution(flowValves, valves, pathsBetweenValvesMap)
//
    var maxScore = recursionBasedSolution(flowValves, valves, pathsBetweenValvesMap)
    return maxScore
}

fun recursionBasedSolution(flowValves: List<Valve>, valves: Map<String, Valve>, pathsBetweenValvesMap: MutableMap<Pair<String, String>, List<String>>): Int {
    // How would I even approach this?
    
    // Single respond. 
    val minute = 0
    val source = "AA"
    val destination = "DD"
    val path = listOf(destination)
    getScore(minute, source, path, valves, pathsBetweenValvesMap)
    
    return 0
}

fun getScore(
    minute: Int,
    source: String,
    path: List<String>,
    valves: Map<String, Valve>,
    pathsBetweenValvesMap: MutableMap<Pair<String, String>, List<String>>
): Int {

    // Get first item
    val destination = path.get(0) 
    
    // TODO - Don't need the full path, just the count of hops to destination.
    val fullPath = pathsBetweenValvesMap.getValue(Pair(source, destination))
    val hopsToDestination = fullPath.size - 1

    val nodeRemainingMinutes = 30 - minute - hopsToDestination

    val destinationFlow = valves.getValue(destination).flow
    val score = nodeRemainingMinutes * destinationFlow
    println("Travel time to ${destination} from ${source} = $hopsToDestination. $nodeRemainingMinutes * ${destinationFlow} = $score")
    
    // get second item
    if (path.size > 1) {
        val newMinute = minute + hopsToDestination + 1
        val secondNodeScore = getScore(newMinute, destination, path.drop(1), valves, pathsBetweenValvesMap)
        return score + secondNodeScore
    }
    
    return score
}

private fun permutationBasedSolution(
    flowValves: List<Valve>,
    valves: Map<String, Valve>,
    pathsBetweenValvesMap: MutableMap<Pair<String, String>, List<String>>
): Int {
    println("Get all possible permutations")
    val sortedFlowValves = flowValves.sortedBy { it.flow }

    val flowValveNames = sortedFlowValves.map { it.name }
    
    val permutations = getAllValveWalkPermutations(flowValveNames)
    println("Got all possible permutations")
    var maxScore = 0
    permutations.forEach {
        val score = simulatePath(valves, it, pathsBetweenValvesMap)
        if (score > maxScore) {
            maxScore = score
        }
    }
    return maxScore
}

fun simulatePath(
    valves: Map<String, Valve>,
    flowValveOrder: List<String>,
    pathsBetweenValvesMap: MutableMap<Pair<String, String>, List<String>>
): Int {

    val stringPath = flowValveOrder.map { it }.joinToString(" -> ")
    println("Simulating path: $stringPath")

    var currentValve = valves.getValue("AA")

    val openValves = mutableListOf<Valve>()
    var minute = 1
    
    var pressureReleased = 0
    flowValveOrder.forEach {
        println("Visit valve: $it")
        printValveState(minute, openValves)
        
        val pathToNode = pathsBetweenValvesMap.getValue(Pair(currentValve.name, it)).drop(1)
        println("Path to node: $pathToNode")

        // TODO -> Could calculate minutes directly from path when not printing output.        
        pathToNode.forEach {
            println("You move to valve ${it}")
            currentValve = valves.getValue(it)
            minute++

            printValveState(minute, openValves)
            if (minute == 30) {
            }
        }
        if (minute >= 30) {
            return pressureReleased
        }
        
        println("You open valve ${it}")
        openValves.add(currentValve)

        val remainingMinutes = 30 - minute
        val totalForNode = valves.getValue(it).flow * remainingMinutes
        println("Has $remainingMinutes for node $it.name. Total by end = $totalForNode")
        pressureReleased += totalForNode
        
        minute++
        
        printValveState(minute, openValves)
        if (minute >= 30) {
            return pressureReleased
        }
    }
    
    while(minute++ < 30) {
        printValveState(minute, openValves)
    }
    
    return pressureReleased
}

private fun printValveState(minute: Int, openValves: List<Valve>) {
    println("== Minute $minute ==")
    if (openValves.isEmpty()) {
        println("No valves are open.")
    } else if (openValves.size == 1) {
        println("Valve ${openValves.first().name} is open, releasing ${openValves.first().flow} pressure.")
    } else {
        val totalFlowRate = openValves.map { it.flow }.sum()
        println("Values ${openValves.map { it.name }} are open, releasing $totalFlowRate pressure.")
    }
    println()
}


// TODO - Copied this from SO, write my own / understand and simplify it.
fun getAllValveWalkPermutations(valves: List<String>): Set<List<String>> {

    val set = valves.toSet()
    if (set.isEmpty()) return emptySet()

    fun <T> _allPermutations(list: List<T>): Set<List<T>> {
        if (list.isEmpty()) return setOf(emptyList())

        val result: MutableSet<List<T>> = mutableSetOf()
        for (i in list.indices) {
            _allPermutations(list - list[i]).forEach{
                item -> result.add(item + list[i])
            }
        }
        return result
    }

    return _allPermutations(set.toList())
}

fun getPathsBetweenValuesMap(valves: Map<String, Valve>): MutableMap<Pair<String, String>, List<String>> {
    val valvePaths = mutableMapOf<Pair<String, String>, List<String>>()
    valves.values.forEach { source ->
        valves.values.forEach() { destination ->
            val pathBetweenNodes = pathBetweenNodes(valves, source, destination)
            valvePaths.put(Pair(source.name, destination.name), pathBetweenNodes)
        }
    }
    return valvePaths
}

// TODO THis is going to need to be some sort of recursion, calculating future values / paths. 
//private fun calculateNodeValue(
//    valves: List<Valve>,
//    currentValve: Valve,
//    maxMinutes: Int,
//    minute: Int
//) {
//    valves.forEach {
////            println("Calculate value of moving from ${currentValve.name} to ${it.name}")
//        if (currentValve.name == it.name) {
////                println("Don't move to self.")
//            return@forEach
//        }
//
//        val remainingMinutes = maxMinutes - minute - 1 // Minus 1 for the time to turn valve on. 
//
//        val pathBetweenNodes = pathBetweenNodes(valves, currentValve, it)
//
//        val minsToDest = (pathBetweenNodes.size) - 1
//
//        val nodeRemainingMinutes = remainingMinutes - minsToDest
//
//        val score = nodeRemainingMinutes * it.flow
//        println("Travel time to ${it.name} = $minsToDest. $nodeRemainingMinutes * ${it.flow} = $score")
//
//    }
//}

// Dijkstra shows up again.
fun pathBetweenNodes(valves: Map<String, Valve>, currentValve: Valve, destination: Valve): List<String> {
    
    // convert values to 'nodes' for graph traversal
//    println("Calculate path from ${currentValve.name} to ${destination.name}")
    
    val visitedMap = valves.keys.map {
        it to false
    }.toMap().toMutableMap()
    
    if (currentValve.name == destination.name) {
        return listOf(currentValve.name)
    }
    
    val queue = LinkedList<Pair<Valve, MutableList<String>>>(
        listOf(Pair(currentValve, mutableListOf(currentValve.name)))
    )
    
    while (!queue.isEmpty()) {
        val queueItem = queue.remove()
        val (node, path) = queueItem
//        println("Visiting Valve: $node. Current path = $path")
        if (visitedMap.getOrDefault(node.name, false)) {
//            println("Already visited node")
            continue
        }
        node.tunnelNodes.forEach {
            val newPath = path.toMutableList()
            newPath.add(it.name)
            
            queue.add(Pair(it, newPath))
            
            if (it.name == destination.name) {
                return newPath
            }
        }

        visitedMap.put(node.name, true)
    }
    throw IllegalStateException("Destination not found from source")
}

fun parseValves(input: String): Map<String, Valve> {
    val valveNodes = mutableMapOf<String, Valve>()
    return input.lines().map {
        val (valveInput, tunnelInput) = it.split(';')
        val name = valveInput.substring(6, 8)
        val flow = valveInput.substring(23).toInt()

        val valve = Valve(name, flow)
        valveNodes.put(name, valve)
        Pair(valve, tunnelInput)
    }.map {
        val (valve, tunnelInput) = it
        val tunnelValueNames = tunnelInput.substring(tunnelInput.indexOf("valve"))
            .replace(",", "")
            .split(' ')
            .drop(1)
        tunnelValueNames.forEach {
            val node = valveNodes.get(it) ?: throw IllegalStateException("Node $it does not exist")
            valve.tunnelNodes.add(node)
        }
        valve.name to valve
    }.toMap()
}

class Valve(val name: String, val flow: Int = 0) {
    var open = false
    val tunnelNodes: MutableList<Valve> = mutableListOf()
    override fun toString(): String {
        val tunnelNodeNames = tunnelNodes.map { it.name }
        return "Valve(name='$name', flow=$flow, tunnelNodes=$tunnelNodeNames)"
    }
}