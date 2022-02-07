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
    public static Node removeDuplicates(Node root) {

        if (root == null) {
            return root;
        }

        Map<Integer, Boolean> valPresent = new HashMap<>();

        Node n = root;
        valPresent.put(n.data, true);

        while (n.next != null) {
            if (valPresent.getOrDefault(n.next.data, false)) {
                // Skip next to next next... will want to loop through again if many duplicates after a valid node.
                n.next = n.next.next;
            } else {
                // Mark that it's been seen, and we can start checking the next node
                valPresent.put(n.next.data, true);
                n = n.next;
            }
        }

        return root;
    }

    // 2.1 Remove Dups - No buffer allowed
    // Duplicates get removed from the front.
    public static Node removeDuplicatesNoBuffer(Node root) {

        if (root == null) {
            return root;
        }

        Node n1 = root;
        Node n2;

        while (n1.next != null) {

            n2 = n1.next;

            boolean skipped = false;
            while (n2.next != null) {
                if (n2.data == n1.data) {
                    // In effect, removing the node being compared in place
                    n1.data = n1.next.data;
                    n1.next = n1.next.next;
                    skipped = true;
                    break;
                }
                n2 = n2.next;
            }
            if (skipped) {
                continue;
            }

            n1 = n1.next;
        }

        return root;
    }
}
