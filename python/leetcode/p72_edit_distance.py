
class Solution:


    def add_to_set(self, s: set, element) -> set:
        s.add(element)
        return s


    # ChatGPT implementation - Turns out there's an algorithm called Levenshtein Distance algorithm.
    def minDistance(self, word1: str, word2: str) -> int:

        # Lengths of the two words
        m, n = len(word1), len(word2)

        # Create a DP table to store the edit distances
        dp = [[0] * (n + 1) for _ in range(m + 1)]

        # Initialize the base cases
        for i in range(m + 1):
            dp[i][0] = i  # Cost of deleting all characters from word1
        for j in range(n + 1):
            dp[0][j] = j  # Cost of inserting all characters into word1 to match word2

        # Fill the DP table
        for i in range(1, m + 1):
            for j in range(1, n + 1):
                if word1[i - 1] == word2[j - 1]:
                    dp[i][j] = dp[i - 1][j - 1]  # No cost if characters match
                else:
                    dp[i][j] = 1 + min(
                        dp[i - 1][j - 1],  # Substitution
                        dp[i - 1][j],  # Deletion
                        dp[i][j - 1]  # Insertion
                    )

        # The answer is in the bottom-right corner of the table
        return dp[m][n]

    # My Naive implementation using a mix of dictionaries, sets etc. Super inefficient, but worked enough to pass with the limited inputs.
    def minDistance_naive(self, word1: str, word2: str) -> int:

        if word1 == word2:
            return 0

        a_n = len(word1)
        b_n = len(word2)
        turns = max(a_n, b_n)

        min_changes = None

        score_lists = dict()
        score_lists[0] = {(word1, 0)}

        # while i <= turns:
        for i in range(turns + 1):

            score_min = min(score_lists.keys())
            score_max = max(score_lists.keys())

            next_turn_lists = dict()

            # TODO Early termination - tricky though
            # TODO Need to ensure there's no chance of a lower score before returning.
            # TODO Sort by score, process lowest score first - then can break early

            seen = set()
            for score in range(score_min, score_max + 1):

                words_to_check = score_lists.get(score, [])

                for word, index in words_to_check:

                    if (word, index) in seen:
                        continue
                    else:
                        seen.add((word, index))

                    if word[:index] != word2[:index]:
                        continue

                    if word[:index + 1] == word2[:index + 1]:
                        next_turn_lists[score] = self.add_to_set(next_turn_lists.get(score, set()),(word, index + 1))
                        continue

                    # Delete first
                    list_len = len(word)
                    if word and list_len > 0:
                        if list_len > index:
                            del_word = word[:index] + word[index + 1:]
                        else:
                            del_word = word[:-1]
                        next_turn_lists[score + 1] = self.add_to_set(next_turn_lists.get(score + 1, set()), (del_word, index)) # Don't move forward.
                        if del_word == word2:
                            if not min_changes or score + 1 < min_changes:
                                min_changes = score + 1

                    # # Update first
                    if word and list_len > index and len(word2) > index:
                        upd_word = word[:index] + word2[index] + word[index + 1:]
                        if word[index] == word2[index]:
                            next_turn_lists[score] = self.add_to_set(next_turn_lists.get(score, set()), (upd_word, index + 1))
                        else:
                            next_turn_lists[score + 1] = self.add_to_set(next_turn_lists.get(score + 1, set()), (upd_word, index + 1))
                        if upd_word == word2:
                            if not min_changes or score + 1 < min_changes:
                                min_changes = score + 1

                    # Insert first
                    if len(word2) > index:
                        ins_word = word[:index] + word2[index] + word[index:]
                        next_turn_lists[score + 1] = self.add_to_set(next_turn_lists.get(score + 1, set()), (ins_word, index + 1))
                        if ins_word == word2:
                            if not min_changes or score + 1 < min_changes:
                                min_changes = score + 1

            score_lists = next_turn_lists

        return min_changes









    # Backup
    #
    # def minDistance(self, word1: str, word2: str) -> int:
    #
    #     if word1 == word2:
    #         return 0
    #
    #     a_n = len(word1)
    #     b_n = len(word2)
    #     turns = max(a_n, b_n)
    #
    #     a_chars = list(word1)
    #     b_chars = list(word2)
    #
    #     i = 0
    #     min_changes = None
    #
    #     lists = [(a_chars.copy(), 0)]
    #
    #     # seen = []
    #
    #     # while i <= turns:
    #     for i in range(turns + 1):
    #         # Starting turn 10. List inputs to parse: 59049
    #
    #         print(f"Starting turn {i}. List inputs to parse: {len(lists)}")
    #         score_lists = dict()
    #
    #         for char_list, changes in lists:
    #
    #             if char_list == b_chars:
    #                 # TODO Early termination - tricky though
    #                 # TODO Need to ensure there's no chance of a lower score before returning.
    #                 # TODO Sort by score, process lowest score first - then can break early
    #
    #                 print(f"Hooray, found a match")
    #                 if not min_changes or changes < min_changes:
    #                     min_changes = changes
    #
    #             # TODO If all chars matching so far, and match again, do nothing? / skip
    #             if char_list[:i + 1] == b_chars[:i + 1]:
    #                 print(f"Chars match up to {i}, {char_list[:i]}")
    #                 continue
    #
    #             # Delete first
    #             list_len = len(char_list)
    #             if char_list and list_len > 0:
    #                 del_list = char_list.copy()
    #                 if list_len > i:
    #                     del_list.pop(i)
    #                 else:
    #                     del_list = del_list[:-1]
    #
    #                 score = changes + 1
    #                 score_lists[score] = score_lists.get(score, []) + [(del_list, score)]
    #
    #             # # Update first
    #             if char_list and list_len > i and len(word2) > i:
    #                 w2_c = word2[i]
    #                 upd_list = char_list.copy()
    #                 prev_char = upd_list[i]
    #                 upd_list[i] = w2_c
    #
    #                 if prev_char == w2_c:
    #                     score_lists[changes] = score_lists.get(changes, []) + [(upd_list, changes)]
    #                 else:
    #                     score = changes + 1
    #                     score_lists[score] = score_lists.get(score, []) + [(upd_list, score)]
    #
    #             # Insert first
    #             if len(word2) > i:
    #                 w2_c = word2[i]
    #
    #                 ins_list = char_list.copy()
    #                 ins_list.insert(i, w2_c)
    #
    #                 score = changes + 1
    #                 score_lists[score] = score_lists.get(score, []) + [(ins_list, score)]
    #
    #         score_min = min(score_lists.keys())
    #         score_max = max(score_lists.keys())
    #
    #         # TODO This was the start of handling them in order, but isn't enough
    #         lists = []
    #         for score in range(score_min, score_max + 1):
    #             # print(f"Get values for {score}")
    #             lists += score_lists.get(score, [])
    #
    #         # i += 1
    #
    #         # if min_changes:
    #         #     return min_changes
    #     print(f"Error, failed to find result")
    #     # return -1 # Logic error, shouldn't ever reach here, but better than 'while true'
    #     return min_changes # Logic error, shouldn't ever reach here, but better than 'while true'
