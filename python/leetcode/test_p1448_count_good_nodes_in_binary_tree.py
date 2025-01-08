from unittest import TestCase

from leetcode.p1448_count_good_nodes_in_binary_tree import Solution, TreeNode


class TestSolution(TestCase):
    def test_good_nodes(self):

        solution = Solution()
        # root = TreeNode(3)
        # l1 = TreeNode(1)
        # l1_l2 = TreeNode(3)
        #
        # r1 = TreeNode(4)
        # r1_l2 = TreeNode(1)
        # r1_r2 = TreeNode(5)
        #
        # root.left, root.right = l1, r1
        # l1.left = l1_l2
        # r1.left, r1.right = r1_l2, r1_r2
        #
        # solution.good_nodes(root)

        # T2
        root = TreeNode(2)
        # l1 = TreeNode(1)
        # l1_l2 = TreeNode(3)

        r1 = TreeNode(4)
        r1_l2 = TreeNode(10)
        r1_r2 = TreeNode(8)
        r1_r2_l1 = TreeNode(4)

        root.right = r1
        r1.left, r1.right = r1_l2, r1_r2
        r1_r2.left = r1_r2_l1
        solution.good_nodes(root)