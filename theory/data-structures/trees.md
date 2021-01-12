# Trees

## Binary Tree

* Each node has 0, 1, or 2 nodes.
* 'Perfect' binary tree is balanced, node has 0 or 2 children.
  * Count of all nodes on bottom =  count of nodes above bottom + 1 
  * At each level (0 indexed) we have 2^n nodes
  * Total nodes in tree = 2^h -1 (h = height, 1 indexed)
* 'Full' binary tree. Node has 0 or 2 children, never 1 child.