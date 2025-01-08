from typing import List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

    def __str__(self):
        return f"{self.val}"

class Solution:
    def good_nodes(self, root: TreeNode) -> int:
        def visit(node: TreeNode, largest: int):
            if not node: return 0
            if node.val >= largest:
                return 1 + visit(node.left, node.val) + visit(node.right, node.val)
            else:
                return visit(node.left, largest) + visit(node.right, largest)
        return visit(root, root.val)
