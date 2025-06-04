from unittest import TestCase

from leetcode.p72_edit_distance import Solution

class TestSolution(TestCase):
    def test_min_distance(self):
        solution = Solution()
        test_cases = [
            # (2, "hor", "ros"),
            (3, "horse", "ros"),
            # (5, "intention", "execution"),
            # (0, "", ""),
            # (1, "", "a"),
            # (1, "par", "pa"),
            # (2, "park", "spak"),
            # (3, "park", "spake"),
            # (3, "spartan", "part"),
            # (2, "tan", "t"),
            # (6, "dinitrophenylhydrazine", "acetylphenylhydrazine"),
            # (6, "plasma", "altruism"),
            # (1, "nitrophenyl", "mitrophenyl"),
            # (2, "nitrophenyl", "metrophenyl"),
            # (3, "nitrophenyl", "metophenyl"),
            # (2, "trop", "tp"), # double deletion
            # (4, "nitrop", "metp"), # double deletion
            # (7, "nitrophenyl", "methyl"),
            # (7, "dinitrophenylhydrazine", "dimethylhydrazine"),
            # (2,
            #  "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdef",
            #   "bcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefg"),
        ]

        for expected, word1, word2 in test_cases:
            with self.subTest(word1=word1, word2=word2):
                self.assertEqual(expected, solution.minDistance(word1=word1, word2=word2))
