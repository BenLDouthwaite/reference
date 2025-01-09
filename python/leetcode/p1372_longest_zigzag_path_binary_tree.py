from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def __str__(self):
        return f"{self.val}"

    def debug_print(self):
        print(self.val)
        if self.left:
            self.left.debug_print()
        if self.right:
            self.right.debug_print()

class Solution:

    def longest_zig_zag(self, root: Optional[TreeNode]) -> int:
        # 'dir' is the direction to get to the parent node
        def visit(node: TreeNode, length: int, direction: int) -> int:
            if not node: return 0

            if not node.left and not node.right:
                return length

            if direction == -1: # left
                l = visit(node.left, 1, -1)
                r = visit(node.right, length + 1, 1)
            elif direction == 1: # right
                l = visit(node.left, length + 1, -1)
                r = visit(node.right, 1, 1)
            else: # Root
                l = visit(node.left, length + 1, -1)
                r = visit(node.right, length + 1, 1)

            return max(length, l, r)

        return visit(root, 0, 0)