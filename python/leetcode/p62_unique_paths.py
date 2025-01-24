class Solution:
    def unique_paths(self, m: int, n: int) -> int:
        paths_count = [[0 for x in range(n)] for y in range(m)]
        for y in reversed(range(m)):
            for x in reversed(range(n)):
                if x + 1 == n or y + 1 == m:
                    paths_count[y][x] = 1
                else:
                    paths_count[y][x] = paths_count[y][x + 1] + paths_count[y + 1][x]
        return paths_count[0][0]
