from collections import deque
from typing import List


class Solution:
    def minCostClimbingStairs(self, cost: List[int]) -> int:
        # Pass in N
        n = len(cost)
        i_scores = dict()
        i_scores[n] = 0
        for i in range(n, 1, -1):
            score = i_scores[i]

            step_down_1_index = i - 1
            step_down_2_index = i - 2

            step_down_1_cost = cost[step_down_1_index]
            step_down_2_cost = cost[step_down_2_index]

            prev_sd1_c = i_scores.get(step_down_1_index, float('inf'))
            i_scores[step_down_1_index] = min(score + step_down_1_cost, prev_sd1_c)
            i_scores[step_down_2_index] = score + step_down_2_cost

        return min(i_scores[0], i_scores[1])


    def minCostClimbingStairs_bfs(self, cost: List[int]) -> int:
        n = len(cost)
        i_scores = dict()
        call_stack = deque()
        def bfs():
            min_score = float('inf')

            while call_stack:
                index, steps, score = call_stack.popleft()

                if index <= 1:
                    if score < min_score:
                        min_score = score
                    continue # Don't add more to queue

                step_down_1_index = index - 1
                step_down_2_index = index - 2

                step_down_1_cost = cost[step_down_1_index]
                step_down_2_cost = cost[step_down_2_index]

                s1_cost = score + step_down_1_cost
                if s1_cost < i_scores.get(step_down_1_index, float('inf')):
                    i_scores[step_down_1_index] = s1_cost
                    call_stack.append((step_down_1_index, steps + 1, s1_cost))

                s2_cost = score + step_down_2_cost
                if s2_cost < i_scores.get(step_down_2_index, float('inf')):
                    i_scores[step_down_2_index] = s2_cost
                    call_stack.append((step_down_2_index, steps + 2, s2_cost))
            return min_score
        call_stack.append((n, 0, 0))
        return bfs()
