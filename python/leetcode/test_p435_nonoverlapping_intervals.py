from unittest import TestCase

from leetcode.p435_nonoverlapping_intervals import Solution


class TestSolution(TestCase):
    def test_erase_overlap_intervals(self):
        solution = Solution()
        self.assertEqual(1 , solution.eraseOverlapIntervals(intervals = [[1,2],[2,3],[3,4],[1,3]]))
        self.assertEqual(0 , solution.eraseOverlapIntervals(intervals = [[1,2],[2,3]]))
        self.assertEqual(9 , solution.eraseOverlapIntervals(intervals = [[-3035,30075],[1937,6906],[11834,20971],[44578,45600],[28565,37578],[19621,34415],[32985,36313],[-8144,1080],[-15279,21851],[-27140,-14703],[-12098,16264],[-36057,-16287],[47939,48626],[-15129,-5773],[10508,46685],[-35323,-26257]]))

