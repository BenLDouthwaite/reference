from typing import Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right

class Solution:
    def max_depth(self, root: Optional[TreeNode]) -> int:
        if not root: return 0
        l = self.max_depth(root.left)
        r = self.max_depth(root.right)
        return 1 + max([l, r])


