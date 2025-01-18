from collections import deque
from typing import List


class Solution:
    def letter_combinations(self, digits: str) -> List[str]:
        phone_digit_to_letters = {
            '2': ['a', 'b', 'c'],
            '3': ['d', 'e', 'f'],
            '4': ['g', 'h', 'i'],
            '5': ['j', 'k', 'l'],
            '6': ['m', 'n', 'o'],
            '7': ['p', 'q', 'r', 's'],
            '8': ['t', 'u', 'v'],
            '9': ['w', 'x', 'y', 'z']
        }

        if len(digits) == 0:
            return []

        search_stack = deque()
        results = []
        def bfs():
            while search_stack:
                result_string, remaining_digits = search_stack.popleft()

                if remaining_digits:
                    digit = remaining_digits[0]
                    for c in phone_digit_to_letters[digit]:
                        if remaining_digits:
                            search_stack.append((result_string + c, remaining_digits[1:]))
                else:
                    results.append(result_string)

        search_stack.append(("", digits))
        bfs()
        return results
