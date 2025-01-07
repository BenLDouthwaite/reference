from typing import Optional


class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
    def __str__(self):
        return f"{self.val}"

    def addNext(self, val):
        next_node = ListNode(val)
        self.next = next_node
        return next_node

    def debug_print(self):
        node = self
        print(self, end='')
        while node.next:
            node = node.next
            print(' ->', node, end='')
        print()

    @classmethod
    def init_list(cls, vals):
        if not vals:
            return None
        head = ListNode(vals[0])
        node = head
        for i in range(1, len(vals)):
            node = node.addNext(vals[i])
        return head

class Solution:
    def oddEvenList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        slow, fast = head, head

        while fast and fast.next and fast.next.next:
            # Look ahead
            pre_fast, fast = fast.next, fast.next.next

            # Swap the items as needed
            pre_fast.next, fast.next, slow.next = fast.next, slow.next, fast

            # Position for the next round
            slow, fast = fast, pre_fast

        return head
