
vowels = 'aeiouAEIOU'
def max_vowels(s: str, k: int) -> int:
    ss = s[:k]
    curr_vowels = len([c for c in ss if c in vowels])
    max_vowels = curr_vowels
    for i in range(0, len(s) - k):
        if s[i] in vowels:
            curr_vowels -= 1
        if s[i + k] in vowels:
            curr_vowels += 1
        if curr_vowels > max_vowels:
            max_vowels = curr_vowels
    return max_vowels