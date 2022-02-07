package ctci;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class TreesAndGraphsTest {

    @Test
    public void routeExistsBetweenNodes() {
        // given

        GraphNode a = new GraphNode("a");
        GraphNode b = new GraphNode("b");
        GraphNode c = new GraphNode("c");
        GraphNode d = new GraphNode("d");
        GraphNode e = new GraphNode("e");
        GraphNode f = new GraphNode("f");

        a.setAdjacent(new GraphNode[]{b, d, e});
        b.setAdjacent(new GraphNode[]{e, f});
        c.setAdjacent(new GraphNode[]{b});
        f.setAdjacent(new GraphNode[]{c});

        GraphNode[] nodes = new GraphNode[]{
                a, b, c, d, e, f
        };
        Graph graph = new Graph(nodes);

        // when
        boolean result = TreesAndGraphs.routeExistsBetweenNodes(a, c);

        // then
        assertThat(result).isTrue();
    }
}