class Solution:
    def numTilings(self, n: int) -> int:

        scores = {
            1: 1,
            2: 2,
            3: 5
        }

        if n in scores.keys():
            return scores[n]

        for i in range(4, n + 1):
            print(scores)
            scores[i] = (scores[i - 1] * 2) + scores[i - 3]

        return scores[n] % (pow(10, 9) + 7)