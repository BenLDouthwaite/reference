from collections import defaultdict
from typing import Optional, List


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
    def path_sum(self, root: Optional[TreeNode], targetSum: int) -> int:

        path_sums = defaultdict(int)

        # Default value on diff
        path_sums[0] = 1

        def visit(node: TreeNode, prefix_sum: int) -> int:

            if not node:
                return 0

            path_sum = prefix_sum + node.val

            diff = path_sum - targetSum

            paths_to_here_with_diff = path_sums[diff]

            # Need to update this once we've already got the path diffs
            path_sums[path_sum] += 1

            l = visit(node.left, path_sum)
            r = visit(node.right, path_sum)

            return paths_to_here_with_diff + l + r
        return visit(root, 0)
