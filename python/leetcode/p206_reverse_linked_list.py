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
    def reverse_list(self, head: Optional[ListNode]) -> Optional[ListNode]:

        if not head: return None

        reversed_head = None
        while head:
            next_node = head.next # Keep log of the next node to check in the original list

            head.next = reversed_head # Update the existing node to our reversed list
            reversed_head = head # The current node is now the head of our reversed list

            head = next_node # Update next node to check

        return reversed_head