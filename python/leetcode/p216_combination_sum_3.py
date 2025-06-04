from typing import List


class Solution:
    def combinationSum3(self, k: int, n: int) -> List[List[int]]:

        paths = set()
        def backtrack(path):
            if len(path) == k:
                if sum(path) == n:
                    paths.add(frozenset(path))
                return

            last_digit = path[-1] if len(path) > 0 else 1
            for i in range(last_digit, 10):
                if len(path) < k and i not in path:
                    backtrack(path + [i])
        backtrack([])
        return [list(path) for path in paths]