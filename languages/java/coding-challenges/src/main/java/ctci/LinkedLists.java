package ctci;

import java.util.*;

public class LinkedLists {

    // Primarily to simplify tests.
    protected static List<Integer> getAllValues(Node root) {
        List<Integer> values = new ArrayList<>();
        Node n = root;
        while (n != null) {
            values.add(n.data);
            n = n.next;
        }
        return values;
    }

    // 2.1 Remove Dups
    public static void removeDuplicates(Node root) {

        if (root == null) {
            return;
        }

        Set<Integer> values = new HashSet<>();

        Node n = root;
        values.add(n.data);

        while (n.next != null) {
            if (values.contains(n.next.data)) {
                // Skip next to next next... will want to loop through again if many duplicates after a valid node.
                n.next = n.next.next;
            } else {
                // Mark that it's been seen, and we can start checking the next node
                values.add(n.next.data);
                n = n.next;
            }
        }
    }

    // 2.1 Remove Dups - No buffer allowed
    // Duplicates get removed from the front.
    public static void removeDuplicatesNoBuffer(Node root) {

        if (root == null) {
            return;
        }

        Node current = root;
        Node runner;

        while (current.next != null) {

            runner = current.next;

            while (runner.next != null) {
                if (runner.next.data != current.data) {
                    runner = runner.next;
                } else {
                    runner.next = runner.next.next;
                }
            }

            current = current.next;
        }
    }
}
