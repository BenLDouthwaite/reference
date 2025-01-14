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
    def lowest_common_ancestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        def check_lca(node: TreeNode) -> TreeNode:

            if not node:
                return node
            if node == p or node == q:
                return node

            l = check_lca(node.left)
            r = check_lca(node.right)

            if l and r:
                return node
            return l or r

        return check_lca(root)
