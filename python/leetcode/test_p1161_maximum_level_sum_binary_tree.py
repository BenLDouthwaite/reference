from unittest import TestCase

from leetcode.p1161_maximum_level_sum_binary_tree import Solution, TreeNode


class TestSolution(TestCase):
    def test_max_level_sum(self):
        solution = Solution()

        root = TreeNode(1)

        l1 = TreeNode(7)
        l1_l2 = TreeNode(7)
        l1_r2 = TreeNode(-8)

        r1 = TreeNode(0)

        root.left, root.right = l1, r1

        l1.left, l1.right = l1_l2, l1_r2

        self.assertEqual(2, solution.max_level_sum(root))