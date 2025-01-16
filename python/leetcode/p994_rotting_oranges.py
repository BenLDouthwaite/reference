from collections import deque
from typing import List


class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        search_stack = deque()
        height = len(grid)
        width = len(grid[0])

        def bfs()-> int:
            while search_stack:
                (x, y, mins) = search_stack.popleft()

                for (p_x, p_y) in [(x + d_x, y + d_y) for (d_x, d_y) in [(0, 1), (1, 0), (-1, 0), (0, -1)]]:
                    if 0 <= p_x < width and 0 <= p_y < height:
                        if grid[p_y][p_x] == 1:
                            grid[p_y][p_x] = 2  # Flag visited
                            search_stack.append((p_x, p_y, mins + 1))
            return mins


        fresh_oranges = 0
        for y in range(height):
            for x in range(width):
                val = grid[y][x]
                if val == 2:
                    search_stack.append((x, y, 0))
                elif val == 1:
                    fresh_oranges += 1
        if not fresh_oranges:
            return 0

        if search_stack:
            mins = bfs()

            fresh_oranges = sum(row.count(1) for row in grid)
            if fresh_oranges:
                return -1  # Some couldn't be reached
            return mins
        else:
            return -1