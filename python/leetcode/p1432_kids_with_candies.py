from typing import List


def kids_with_candies(candies: List[int], extraCandies: int) -> List[bool]:
    max_initial_kid_count = max(candies)
    return [kid_count + extraCandies >= max_initial_kid_count for kid_count in candies]
