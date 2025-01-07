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

    def print(self):
        node = self
        print(self, end='')
        while node.next:
            node = node.next
            print(' ->', node, end='')
        print()

    @classmethod
    def init_list(cls):
        pass


class Solution:
    # TODO Experiment with a 2-pointer alternative

    def deleteMiddle(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if head is None:
            return None

        node = head
        len = 1
        while node.next:
            node = node.next
            len += 1

        node = head
        for i in range(len // 2 - 1):
            node = node.next

        if node.next and node.next.next:
            node.next = node.next.next
        elif node.next:
            node.next = None
        else:
            return None # ??

        return head