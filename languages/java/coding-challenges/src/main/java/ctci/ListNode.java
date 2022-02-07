package ctci;

import java.util.Arrays;

class ListNode {
    ListNode next = null;
    int data;

    public ListNode(int data) {
        this.data = data;
    }

    void appendToTail(int d) {
        ListNode end = new ListNode(d);
        ListNode n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    // convenience utility for testing
    void appendToTail(int... list) {
        Arrays.stream(list).forEach(this::appendToTail);
    }

}
