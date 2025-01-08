from unittest import TestCase

from leetcode.p2130_maximum_twin_sum_of_a_linked_list import Solution, ListNode


class TestSolution(TestCase):
    def test_pair_sum(self):
        solution = Solution()
        self.assertEqual(6, solution.pair_sum(ListNode.init_list([5, 4, 2, 1])))
        self.assertEqual(7, solution.pair_sum(ListNode.init_list([4,2,2,3])))
