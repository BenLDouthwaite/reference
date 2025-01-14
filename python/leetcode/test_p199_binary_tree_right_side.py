from unittest import TestCase

from leetcode.p199_binary_tree_right_side import Solution, TreeNode


class TestSolution(TestCase):
    def test_right_side_view(self):
        solution = Solution()

        root = TreeNode(1)

        l1 = TreeNode(2)
        l1_r2 = TreeNode(5)

        r1 = TreeNode(3)
        r1_r2 = TreeNode(4)

        root.left, root.right = l1, r1

        l1.right = l1_r2
        r1.right = r1_r2

        self.assertEqual([1,3,4], solution.right_side_view(root))