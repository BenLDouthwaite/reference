from typing import List, Optional

from collections import deque

from thonny.plugins.statement_boxes import print_tree


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

    @classmethod
    def build(cls, values: List[int]):
        if not values:
            return None
        root = TreeNode(values.pop(0))

        build_stack = deque()
        node = root
        while values:
            l = TreeNode(values.pop(0))
            r = TreeNode(values.pop(0))

            node.left, node.right = l, r

            build_stack.append(l)
            build_stack.append(r)

            node = build_stack.popleft()

        return root


class Solution:
    def delete_node(self, root: Optional[TreeNode], key: int) -> Optional[TreeNode]:
        node, parent = root, None
        while node and node.val != key:
            if key < node.val:
                node, parent = node.left, node
            elif key > node.val:
                node, parent = node.right, node
        if not node:
            return root

        # print(f"Found key {key} at node {node}. Parent = {parent}")

        node_to_replace = None
        left = node.left
        right = node.right

        if not left and not right:
            # Leaf node being deleted
            if node == root:
                # Edge case, deleting root as the only node
                return None

        if left and not right:
            node_to_replace = left

        elif right:
            smallest_left_in_right_branch = right
            while smallest_left_in_right_branch.left:
                smallest_left_in_right_branch = smallest_left_in_right_branch.left

            smallest_left_in_right_branch.left = left
            node_to_replace = right

        if parent:
            if node.val < parent.val:
                parent.left = node_to_replace
            if node.val > parent.val:
                parent.right = node_to_replace
        else:
            return node_to_replace

        return root
