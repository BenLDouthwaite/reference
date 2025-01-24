
class Solution:
    def longest_common_subsequence(self, text1: str, text2: str) -> int:
        dp = [[0 for x in range(len(text1))] for y in range(len(text2))]

        for a_i, a_char in enumerate(text1):
            for b_i, b_char in enumerate(text2):
                if a_char == b_char:
                    dp_d = dp[b_i - 1][a_i - 1] if a_i > 0 and b_i > 0 else 0
                    dp[b_i][a_i] = dp_d + 1
                else:
                    dp[b_i][a_i] = max(dp[b_i - 1][a_i], dp[b_i][a_i - 1])
        return dp[b_i][a_i]