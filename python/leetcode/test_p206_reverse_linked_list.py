from unittest import TestCase

from leetcode.p206_reverse_linked_list import Solution, ListNode


class TestSolution(TestCase):
    def test_reverse_list(self):
        solution = Solution()
        head = ListNode.init_list([1,2,3,4,5])
        solution.reverse_list(head) # Trigger, no assertion