from unittest import TestCase

from leetcode.p1372_longest_zigzag_path_binary_tree import Solution, TreeNode


class TestSolution(TestCase):
    def test_longest_zig_zag(self):
        solution = Solution()

        root = TreeNode(1)

        r1 = TreeNode(2)
        r1_l2 = TreeNode(3)
        r1_r2 = TreeNode(4)
        r1_r2_l3 = TreeNode(5)
        r1_r2_r3 = TreeNode(6)
        r1_r2_l3_r4 = TreeNode(7)
        r1_r2_l3_r4_r5 = TreeNode(8)


        root.right = r1
        r1.left, r1.right = r1_l2, r1_r2
        r1_r2.left, r1_r2.right = r1_r2_l3, r1_r2_r3
        r1_r2_l3.right = r1_r2_l3_r4
        r1_r2_l3_r4.right = r1_r2_l3_r4_r5

        self.assertEqual(3, solution.longest_zig_zag(root))
