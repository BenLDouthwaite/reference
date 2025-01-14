from collections import deque, defaultdict
from typing import List, Optional


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
    def max_level_sum(self, root: Optional[TreeNode]) -> int:
        depth_count = defaultdict(int)
        search_stack = deque()
        search_stack.append((root, 1))

        def traverse():
            while search_stack:
                node, depth = search_stack.popleft()
                if not node:
                    return  []

                depth_count[depth] = depth_count[depth] + node.val

                if node.right:
                    search_stack.append((node.right, depth + 1))
                if node.left:
                    search_stack.append((node.left, depth + 1))

        traverse()
        return max(depth_count, key=depth_count.get)
