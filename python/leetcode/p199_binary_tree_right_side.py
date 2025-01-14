from collections import deque
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
    def right_side_view(self, root: Optional[TreeNode]) -> List[int]:
        view_list = []
        search_stack = deque()
        search_stack.append((root, 1))

        def traverse():
            while search_stack:
                node, depth = search_stack.popleft()
                if not node:
                    return  []

                if len(view_list) < depth:
                    view_list.append(node.val)

                if node.right:
                    search_stack.append((node.right, depth + 1))
                if node.left:
                    search_stack.append((node.left, depth + 1))

        traverse()
        return view_list
