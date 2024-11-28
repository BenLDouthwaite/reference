def merge_alternately(word1: str, word2: str) -> str:
    word1_len = len(word1)
    word2_len = len(word2)
    merged_characters = []
    for i in range(0, max(word1_len, word2_len)):
        if i < word1_len:
            merged_characters.append(word1[i])
        if i < word2_len:
            merged_characters.append(word2[i])
    return ''.join([char for char in merged_characters])