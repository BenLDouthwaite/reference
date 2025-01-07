from unittest import TestCase

from leetcode.p328_odd_even_linked_list import Solution, ListNode


class TestSolution(TestCase):
    def test_odd_even_list(self):
        solution = Solution()
        head = ListNode.init_list([1,2,3,4,5])
        self.assertEqual(head, solution.oddEvenList(head))

        head = ListNode.init_list([2,1,3,5,6,4,7])
        self.assertEqual(head, solution.oddEvenList(head))
