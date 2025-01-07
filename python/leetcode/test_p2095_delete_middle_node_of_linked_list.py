from unittest import TestCase

from leetcode.p2095_delete_middle_node_of_linked_list import ListNode, Solution


class TestSolution(TestCase):
    def test_delete_middle(self):
        solution = Solution()

        head = ListNode(1)
        next = head.addNext(3)
        next = next.addNext(4)
        next = next.addNext(7)
        next = next.addNext(1)
        next = next.addNext(2)
        next = next.addNext(6)

        self.assertEqual(head, solution.deleteMiddle(head))
        head = ListNode(2)
        next = head.addNext(1)

        self.assertEqual(head, solution.deleteMiddle(head))
