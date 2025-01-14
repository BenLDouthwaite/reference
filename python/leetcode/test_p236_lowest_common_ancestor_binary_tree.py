from unittest import TestCase

from leetcode.p236_lowest_common_ancestor_binary_tree import Solution, TreeNode


class TestSolution(TestCase):
    def test_lowest_common_ancestor(self):
        solution = Solution()

        root = TreeNode(3)

        l1 = TreeNode(5)
        l1_l2 = TreeNode(6)
        l1_r2 = TreeNode(2)
        l1_r2_l3 = TreeNode(7)
        l1_r2_r3 = TreeNode(4)

        r1 = TreeNode(1)
        r1_l2 = TreeNode(0)
        r1_r2 = TreeNode(8)

        root.left, root.right = l1, r1

        l1.left, l1.right = l1_l2, l1_r2
        l1_r2.left, l1_r2.right = l1_r2_l3, l1_r2_r3

        r1.left, r1.right = r1_l2, r1_r2

        self.assertEqual(root, solution.lowest_common_ancestor(root, l1, r1))
        self.assertEqual(l1, solution.lowest_common_ancestor(root, l1, l1_r2_r3))