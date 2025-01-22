from typing import List


class Solution:
    def findMinArrowShots(self, points: List[List[int]]) -> int:
        points.sort(key=lambda point: point[1])
        arrow, arrow_count = None, 0
        for (s, e) in points:
            if not arrow or not s <= arrow <= e:
                arrow = e
                arrow_count += 1
        return arrow_count
