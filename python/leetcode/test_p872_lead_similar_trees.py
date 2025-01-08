from unittest import TestCase

from leetcode.p872_lead_similar_trees import Solution, TreeNode


class TestSolution(TestCase):
    def test_leaf_similar(self):
        solution = Solution()
        root = TreeNode(3)
        l1 = TreeNode(5)
        l1_l2 = TreeNode(6)
        l1_r2 = TreeNode(2)
        l1_r2_l3 = TreeNode(7)
        l1_r2_r3 = TreeNode(4)

        r1 = TreeNode(1)
        r1_l2 = TreeNode(9)
        r1_r2 = TreeNode(8)

        root.left, root.right = l1, r1
        l1.left, l1.right = l1_l2, l1_r2
        l1_r2.left, l1_r2.right = l1_r2_l3, l1_r2_r3
        r1.left, r1.right = r1_l2, r1_r2

        solution.leaf_similar(root, root)