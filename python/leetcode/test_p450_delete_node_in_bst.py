from unittest import TestCase

from leetcode.p450_delete_node_in_bst import Solution, TreeNode


class TestSolution(TestCase):
    def test_delete_node(self):
        solution = Solution()
        solution.delete_node(TreeNode.build([5, 3, 6, 2, 4, None, 7]), 3)
        solution.delete_node(TreeNode.build([8,4,9,2,6,None,10,1,3,5,7]), 4)
        solution.delete_node(TreeNode.build([0]), 0)
        solution.delete_node(TreeNode.build([5,3,6,2,4,None,7]), 5)

