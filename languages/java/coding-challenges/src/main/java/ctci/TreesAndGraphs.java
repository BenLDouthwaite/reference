package ctci;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class TreesAndGraphs {

    // 4.1 Route Between Nodes (basic BFS)
    public static boolean routeExistsBetweenNodes(GraphNode start, GraphNode target) {

        Set<GraphNode> markedNodes = new HashSet<>();

        Queue<GraphNode> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {

            GraphNode node = queue.poll();
            System.out.println("Visiting Node: " + node.name);

            if (node == target) {
                System.out.println("Found Target. Path Found");
                return true;
            }
            for (GraphNode adjacent : node.adjacent) {
                if (!markedNodes.contains(adjacent)) {
                    markedNodes.add(adjacent);
                    queue.offer(adjacent);
                }
            }
        }

        return false;
    }
}
