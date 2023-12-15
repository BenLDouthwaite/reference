import aocUtils.readText
import java.util.*

private const val DAY = "day16"

fun main() {
//    val proboscideaVolcanium = proboscideaVolcanium(readText(DAY, "exampleInput.txt"))
//    println(proboscideaVolcanium)
//    check(proboscideaVolcanium == 1651)
//
//    println(proboscideaVolcanium(readText(DAY)))
    
//    check(proboscideaVolcaniumP2(readText(DAY, "exampleInput.txt")) == 1707)

    println(proboscideaVolcaniumP2(readText(DAY)))
}

fun proboscideaVolcaniumP2(input: String): Int {
    val valves = parseValves(input)
    val hopsBetweenFlowValves = getHopsBetweenValvesMap(valves)
    val flowValvesMap = valves.filter { (_, v) -> v.flow != 0 }
    val availableFlowNodes = flowValvesMap.keys.toList()

    val pairScore = getPairScoreInit(
        availableFlowNodes,
        valves,
        hopsBetweenFlowValves
    )
    return pairScore
}

fun proboscideaVolcanium(input: String): Int {

    val valves = parseValves(input)
    val hopsBetweenFlowValves = getHopsBetweenValvesMap(valves)
    val flowValvesMap = valves.filter { (_, v) -> v.flow != 0 }
    val availableFlowNodes = flowValvesMap.keys.toList()
    return getBestScore(1, listOf("AA"), availableFlowNodes, flowValvesMap, hopsBetweenFlowValves)
}

fun getPairScoreInit(
    valvesToVisit: List<String>,
    valves: Map<String, Valve>,
    hopsBetweenValves: Map<Pair<String, String>, Int>
): Int {
    val startingNode = "AA"
    val startingPath = listOf(startingNode)

    // massive hack 
    
    println("Need to check for $valvesToVisit")
    val max = valvesToVisit.map { manValve ->
        val filteredValves = valvesToVisit.filter { it != manValve }
        filteredValves.map { eleValve ->
            val doubleFilteredValves = filteredValves.filter { it != eleValve }
            println("Checking for man start at $manValve, ele start at $eleValve")
            getPairScore(
                1,
                startingPath,
                manValve,
                hopsBetweenValves.getValue(Pair(startingNode, manValve)), // hack, updated when given a target
                startingPath,
                eleValve,
                hopsBetweenValves.getValue(Pair(startingNode, eleValve)),
                doubleFilteredValves,
                valves,
                hopsBetweenValves
            )
        }.maxBy { it.second }
    }.maxBy { it.second }

    println("Best path = ${max.first}, with score ${max.second}")
    return max.second
}

fun getPairScore(
    minute: Int,
    manCurrentPath: List<String>,
    manTargetValve: String?,
    manHopsToTarget: Int,
    eleCurrentPath: List<String>,
    eleTargetValve: String?,
    eleHopsToTarget: Int,
    valvesToVisit: List<String>,
    valves: Map<String, Valve>,
    hopsBetweenValves: Map<Pair<String, String>, Int>
): Pair<Pair<List<String>, List<String>>, Int> {
    val manSource = manCurrentPath.last()
    
//    println("M $manCurrentPath -> $manTargetValve. E $eleCurrentPath -> $eleTargetValve")

    var manTargetValve = manTargetValve
    var eleTargetValve = eleTargetValve
    var manHopsToTarget = manHopsToTarget
    var eleHopsToTarget = eleHopsToTarget
    var valvesToVisit = valvesToVisit
    var manCurrentPath = manCurrentPath.toMutableList()
    var eleCurrentPath = eleCurrentPath.toMutableList()
    
    var score = 0
    for (m in minute .. 26) {
//        println("Minute: $m")
        if (manTargetValve != null) {
            if (manHopsToTarget == 0) {
                manCurrentPath.add(manTargetValve)
                val valveScore = (26 - m) * valves.getValue(manTargetValve).flow
                score += valveScore
                manTargetValve = null
            } else if (manHopsToTarget > 0) {
                manHopsToTarget--
            }
        }
        
        if (eleTargetValve != null) {
            if (eleHopsToTarget == 0) {
                eleCurrentPath.add(eleTargetValve)
                val valveScore = (26 - m) * valves.getValue(eleTargetValve).flow
                score += valveScore
                eleTargetValve = null
            } else if (eleHopsToTarget > 0) {
                eleHopsToTarget--
            }
        }
        
        // Man only
        if (!valvesToVisit.isEmpty() && manTargetValve == null && eleTargetValve != null) {
            val pairScores = valvesToVisit.map {

                var filteredValves = valvesToVisit
                val manLastValve = manCurrentPath.last()
                manHopsToTarget = hopsBetweenValves.getValue(Pair(manLastValve, it))
                filteredValves = filteredValves.filter { valve -> valve != it }
                
                getPairScore(
                    m + 1,
                    manCurrentPath,
                    it,
                    manHopsToTarget,
                    eleCurrentPath,
                    eleTargetValve,
                    eleHopsToTarget,
                    filteredValves,
                    valves,
                    hopsBetweenValves
                )
            }
//            println("\nPair scores: $pairScores")
            val maxPairScore = pairScores.maxBy { it.second }
//            println("Max pair score = $maxPairScore")
            val pairScore = Pair(maxPairScore.first, score + maxPairScore.second)
//            println("Including Score and max pair score. Result: $pairScore\n")
            return pairScore
        }
        
        // Ele only 
        if (!valvesToVisit.isEmpty() && manTargetValve != null && eleTargetValve == null) {
            val pairScores = valvesToVisit.map {
                
                var filteredValves = valvesToVisit
                val eleLastValve = eleCurrentPath.last()
                eleHopsToTarget = hopsBetweenValves.getValue(Pair(eleLastValve, it))
                filteredValves = filteredValves.filter { valve -> valve != it }

                getPairScore(
                    m + 1,
                    manCurrentPath,
                    manTargetValve,
                    manHopsToTarget,
                    eleCurrentPath,
                    it,
                    eleHopsToTarget,
                    filteredValves,
                    valves,
                    hopsBetweenValves
                )
            }
            
//            println("\nPair scores: $pairScores")
            val maxPairScore = pairScores.maxBy { it.second }
//            println("Max pair score = $maxPairScore")
            val pairScore = Pair(maxPairScore.first, score + maxPairScore.second)
//            println("Including Score and max pair score. Result: $pairScore\n")
            return pairScore
        }

        if (!valvesToVisit.isEmpty() && (manTargetValve == null && eleTargetValve == null)) {

            val manLastValve = manCurrentPath.last()
            val eleLastValve = eleCurrentPath.last()
            
            // If Only a single option remaining, test both man + ele trying it
            if (valvesToVisit.size == 1) {
                val targetValve = valvesToVisit.first()
                manHopsToTarget = hopsBetweenValves.getValue(Pair(manLastValve, targetValve))
                eleHopsToTarget = hopsBetweenValves.getValue(Pair(eleLastValve, targetValve))

                val filteredValves = valvesToVisit.filter { it != targetValve }

                val pairScores = listOf(
                    getPairScore( // Man
                        m + 1,
                        manCurrentPath,
                        targetValve,
                        manHopsToTarget, 
                        eleCurrentPath,
                        eleTargetValve,
                        eleHopsToTarget,
                        filteredValves,
                        valves,
                        hopsBetweenValves
                    ),
                    getPairScore( // Ele
                        m + 1,
                        manCurrentPath,
                        manTargetValve,
                        manHopsToTarget, 
                        eleCurrentPath,
                        targetValve,
                        eleHopsToTarget,
                        filteredValves,
                        valves,
                        hopsBetweenValves
                    )
                )
                val maxPair = pairScores.maxBy { it.second }
                return Pair(maxPair.first, score + maxPair.second)
            } else {
                // More than 1 itme remianing, check all of them
                val maxPairs = valvesToVisit.map { manValve ->

                    manHopsToTarget = hopsBetweenValves.getValue(Pair(manLastValve, manValve))
                    val filteredValves = valvesToVisit.filter { it != manValve }
                    if (filteredValves.isEmpty()) {
                        val pScore = getPairScore(
                            m + 1,
                            manCurrentPath,
                            manValve,
                            manHopsToTarget, // hack, updated when given a target
                            eleCurrentPath,
                            eleTargetValve,
                            eleHopsToTarget,
                            filteredValves,
                            valves,
                            hopsBetweenValves
                        )
                        pScore
                    } else {
                        val pScores = filteredValves.map { eleValve ->
                            val doubleFilteredValves = filteredValves.filter { it != eleValve }
                            eleHopsToTarget = hopsBetweenValves.getValue(Pair(eleLastValve, eleValve))
                            val pScore = getPairScore(
                                m + 1,
                                manCurrentPath,
                                manValve,
                                manHopsToTarget, // hack, updated when given a target
                                eleCurrentPath,
                                eleValve,
                                eleHopsToTarget,
                                doubleFilteredValves,
                                valves,
                                hopsBetweenValves
                            )
                            pScore
                        }
                        pScores.maxBy { it.second }
                    }
                }
                val maxPair = maxPairs.maxBy { it.second }
                return Pair(maxPair.first, score + maxPair.second)
            }
            
//            val pairScores = valvesToVisit.map {
//                
//                var manTarget: String?
//                var filteredValves = valvesToVisit
//                val manLastValve = manCurrentPath.last()
//                manTarget = it
//                manHopsToTarget = hopsBetweenValves.getValue(Pair(manLastValve, manTarget))
//                filteredValves = filteredValves.filter { valve -> valve != manTarget }
//
//                var eleTarget: String? = eleTargetValve
//                var pairScore: Pair<Pair<List<String>, List<String>>, Int>
//                if (!filteredValves.isEmpty()) {
//                    val eleLastValve = eleCurrentPath.last()
//                    val pairScores = filteredValves.map {
//                        eleTarget = it
//                        eleHopsToTarget = hopsBetweenValves.getValue(Pair(eleLastValve, it))
//                        filteredValves = filteredValves.filter { valve -> valve != eleTarget }
//
//                        getPairScore(
//                            m + 1,
//                            manCurrentPath,
//                            manTarget,
//                            manHopsToTarget,
//                            eleCurrentPath,
//                            eleTarget,
//                            eleHopsToTarget,
//                            filteredValves,
//                            valves,
//                            hopsBetweenValves
//                        )
//                    }
//                    val maxPairScore = pairScores.maxBy { it.second }
//                    pairScore = maxPairScore
//                } else {
//                    pairScore = getPairScore(
//                        m + 1,
//                        manCurrentPath,
//                        manTarget,
//                        manHopsToTarget,
//                        eleCurrentPath,
//                        eleTarget,
//                        eleHopsToTarget,
//                        filteredValves,
//                        valves,
//                        hopsBetweenValves
//                    )
//                }
//                pairScore
//                
//            }
//            val maxPairScore = pairScores.maxBy { it.second }
//            val pairScore = Pair(maxPairScore.first, score + maxPairScore.second)
//            return pairScore
        }
        
        if (valvesToVisit.isEmpty() && manTargetValve == null && eleTargetValve == null) {
            val pairScorePaths = Pair(manCurrentPath, eleCurrentPath)
            val pairScore = Pair(pairScorePaths, score)
            return pairScore
        }
    }
    
    // TODO Print the path for both the man and the elephant to help debugging 
    val pairScorePaths = Pair(listOf<String>("MAN"), listOf<String>("ELE"))
    val pairScore = Pair(pairScorePaths, score)
    return pairScore
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