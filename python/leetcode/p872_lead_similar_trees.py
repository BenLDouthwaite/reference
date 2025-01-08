from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def leaf_similar(self, root1: Optional[TreeNode], root2: Optional[TreeNode]) -> bool:
        r1_leaf_nodes = self.leaf_nodes(root1)
        r2_leaf_nodes = self.leaf_nodes(root2)
        return r1_leaf_nodes == r2_leaf_nodes

    def leaf_nodes(self, node: TreeNode):
        if not node: return []
        if not node.left and not node.right:
            return [node.val]
        left_nodes = self.leaf_nodes(node.left)
        right_nodes = self.leaf_nodes(node.right)

        leaf_nodes = [] + left_nodes + right_nodes
        return leaf_nodes
