from collections import deque
from typing import List


class Solution:
    def nearestExit(self, maze: List[List[str]], entrance: List[int]) -> int:
        height, width = len(maze), len(maze[0])
        (e_y, e_x) = entrance
        c_x, c_y = e_x, e_y

        search_stack = deque()
        def bfs()-> int:
            while search_stack:
                (x, y, distance) = search_stack.popleft()
                if (x, y) != (e_x, e_y): # Not entrance
                    if x == 0 or x == width - 1 or y == 0 or y == height - 1:
                        return distance
                # maze[y][x] = 'V' # Flag visited
                for (p_x, p_y) in [(x + d_x, y + d_y) for (d_x, d_y) in [(0, 1), (1, 0), (-1, 0), (0, -1)]]:
                    if 0 <= p_x < width and 0 <= p_y < height:
                        if maze[p_y][p_x] == '.':
                            maze[p_y][p_x] = 'V'  # Flag visited
                            search_stack.append((p_x, p_y, distance + 1))
            else:
                return -1

        maze[c_y][c_x] = 'V' # Flag visited
        search_stack.append((c_x, c_y, 0))
        return bfs()
