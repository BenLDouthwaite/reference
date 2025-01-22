from typing import List


class Solution:
    def eraseOverlapIntervals(self, intervals: List[List[int]]) -> int:
        n = len(intervals)
        if n == 0:
            return 0
        intervals.sort(key=lambda interval: interval[1])
        intervals_retained = 0
        position = None
        for (s, e) in intervals:
            if position is None or s >= position:
                intervals_retained += 1
                position = e
        return n - intervals_retained