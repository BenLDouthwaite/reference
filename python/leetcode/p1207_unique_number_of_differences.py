from typing import List
from collections import Counter

def unique_occurrences(arr: List[int]) -> bool:
    values = Counter(arr).values()
    return len(values) == len(set(values))