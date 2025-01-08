from unittest import TestCase

from leetcode.p437_path_sum_3 import Solution, TreeNode


class TestSolution(TestCase):
    def test_path_sum(self):
        solution = Solution()

        root = TreeNode(10)
        l1 = TreeNode(5)
        l1_l2 = TreeNode(3)
        l1_r2 = TreeNode(2)
        l1_l2_l3 = TreeNode(3)
        l1_l2_r3 = TreeNode(-2)
        l1_r2_r3 = TreeNode(1)

        r1 = TreeNode(-3)
        r1_r2 = TreeNode(11)

        root.left, root.right = l1, r1
        l1.left, l1.right = l1_l2, l1_r2
        l1_l2.left, l1_l2.right = l1_l2_l3, l1_l2_r3
        l1_r2.right = l1_r2_r3

        r1.right = r1_r2

        self.assertEqual(3, solution.path_sum(root, 8))

        root = TreeNode(1)
        self.assertEqual(0, solution.path_sum(root, 0))