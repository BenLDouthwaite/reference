from unittest import TestCase

from leetcode.p841_keys_and_rooms import Solution


class TestSolution(TestCase):
    def test_can_visit_all_rooms(self):
        solution = Solution()
        self.assertEqual(True, solution.can_visit_all_rooms([[1],[2],[3],[]]))
        self.assertEqual(False, solution.can_visit_all_rooms([[1,3],[3,0,1],[2],[0]]))
