from collections import Counter
def close_strings(word1: str, word2: str) -> bool:
    if len(word1) != len(word2):
        return False
    word1_counter = Counter(word1)
    word2_counter = Counter(word2)

    if word1_counter.keys() != word2_counter.keys():
        return False

    if Counter(word1_counter.values()) != Counter(word2_counter.values()):
        return False  # Total occurrences must match. Can't change any 2,2,2,1 into a 3,2,1,1

    return True
