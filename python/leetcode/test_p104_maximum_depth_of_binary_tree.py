from unittest import TestCase

from leetcode.p104_maximum_depth_of_binary_tree import Solution, TreeNode


class TestSolution(TestCase):
    def test_max_depth(self):
        solution = Solution()
        root = TreeNode(3)
        l1 = TreeNode(9)
        r1 = TreeNode(20)
        r1_l2 = TreeNode(15)
        r1_r2 = TreeNode(7)
        root.left, root.right = l1, r1
        r1.left, r1.right = r1_l2, r1_r2

        res = solution.max_depth(root)
        self.assertEqual(3, res)
