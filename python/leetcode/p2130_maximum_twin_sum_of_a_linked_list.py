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
    def pair_sum(self, head: Optional[ListNode]) -> int:

        slow, fast = head, head
        pair_sums = {}
        i = 0

        # Get first pairs from slow pointer until middle of linked list
        while fast and fast.next:
            pair_sums[i] = slow.val

            fast = fast.next.next
            slow = slow.next

            i += 1

        # Continue moving slow pointer from the middle to get twin pair values
        while slow:
            i -= 1
            pair_sums[i] = pair_sums[i] + slow.val

            slow = slow.next

        return max([pair_sum for pair_sum in pair_sums.values()])