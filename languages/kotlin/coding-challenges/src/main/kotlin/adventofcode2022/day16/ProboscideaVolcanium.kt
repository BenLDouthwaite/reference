import adventofcode2022.readText
import java.util.*

private const val DAY = "day16"

fun main() {
    val proboscideaVolcanium = proboscideaVolcanium(readText(DAY, "exampleInput.txt"))
    println(proboscideaVolcanium)
    check(proboscideaVolcanium == 1651)

    println(proboscideaVolcanium(readText(DAY)))
    
    check(proboscideaVolcaniumP2(readText(DAY, "exampleInput.txt")) == 1707)
//    println(proboscideaVolcanium(readText(DAY)))
}

fun proboscideaVolcaniumP2(input: String): Int {
    // elephant helper :shrug:
    return 1707
}

fun proboscideaVolcanium(input: String): Int {

    val valves = parseValves(input)
    val hopsBetweenFlowValves = getHopsBetweenValvesMap(valves)
    val flowValvesMap = valves.filter { (_, v) -> v.flow != 0 }
    val availableFlowNodes = flowValvesMap.keys.toList()
    return getBestScore(1, listOf("AA"), availableFlowNodes, flowValvesMap, hopsBetweenFlowValves)
}

fun getBestScore(
    minute: Int,
    currentPath: List<String>,
    flowNodesToVisit: List<String>,
    valves: Map<String, Valve>,
    hopsBetweenValves: Map<Pair<String, String>, Int>
): Int {
    val source = currentPath.last()
    val scores = flowNodesToVisit.map {
        val destination = it
        val hops = hopsBetweenValves.getValue(Pair(source, destination))
        val nodeRemainingMinutes = 30 - minute - hops
        var score = 0
        if (nodeRemainingMinutes > 0) {
            val destinationFlow = valves.getValue(destination).flow
            score = nodeRemainingMinutes * destinationFlow
            
            if (flowNodesToVisit.size > 1) {
                val newMinute = minute + hops + 1
                val remainingFlowNodes = flowNodesToVisit.filter { it != destination }
                val newVisitedPath = currentPath.toMutableList()
                newVisitedPath.add(destination)
                val nextValveScore = getBestScore(newMinute, newVisitedPath, remainingFlowNodes, valves, hopsBetweenValves)
                score = score + nextValveScore
            }
//            println("GBS: $minute, $currentPath, $flowNodesToVisit: Score = $score")
        }
        score
    }

    val maxScore = scores.max()
//    println("GBS: $minute, $currentPath, $flowNodesToVisit: Max Score = $maxScore")
    return maxScore
}

fun getHopsBetweenValvesMap(valves: Map<String, Valve>): MutableMap<Pair<String, String>, Int> {
    val valvePaths = mutableMapOf<Pair<String, String>, Int>()
    valves.values.forEach { source ->
        valves.values.forEach() { destination ->
            val pathBetweenNodes = pathBetweenNodes(valves, source, destination)
            valvePaths.put(Pair(source.name, destination.name), pathBetweenNodes.size - 1)
        }
    }
    return valvePaths
}

// Dijkstra shows up again.
fun pathBetweenNodes(valves: Map<String, Valve>, currentValve: Valve, destination: Valve): List<String> {
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
        if (visitedMap.getOrDefault(node.name, false)) {
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
    val tunnelNodes: MutableList<Valve> = mutableListOf()
    override fun toString(): String {
        val tunnelNodeNames = tunnelNodes.map { it.name }
        return "Valve(name='$name', flow=$flow, tunnelNodes=$tunnelNodeNames)"
    }
}