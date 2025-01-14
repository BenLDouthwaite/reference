from unittest import TestCase

from leetcode.p1466_reorder_routes_lead_to_city_zero import Solution


class TestSolution(TestCase):
    def test_min_reorder(self):
        solution = Solution()
        self.assertEqual(3, solution.min_reorder(6, [[0,1],[1,3],[2,3],[4,0],[4,5]]))
        self.assertEqual(2, solution.min_reorder(5, [[1,0],[1,2],[3,2],[3,4]]))
        self.assertEqual(6, solution.min_reorder(10, [[0,1],[2,1],[3,2],[0,4],[5,1],[2,6],[5,7],[3,8],[8,9]]))
