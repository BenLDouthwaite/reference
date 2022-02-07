package ctci;

import org.w3c.dom.Node;

public class GraphNode {
    public String name;
    public GraphNode[] adjacent;

    public GraphNode(String name) {
        this.name = name;
        this.adjacent = new GraphNode[]{};
    }

    public GraphNode(String name, GraphNode[] adjacent) {
        this.name = name;
        this.adjacent = adjacent;
    }

    public void setAdjacent(GraphNode[] adjacent) {
        this.adjacent = adjacent;
    }
}
