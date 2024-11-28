# Could use placeholder character
# Better to track with indexes
def reverse_vowels(s: str) -> str:
    lc_vowels = ['a', 'e', 'i', 'o', 'u']
    vowels = lc_vowels + [vowel.upper() for vowel in lc_vowels]
    start_ptr = 0 # Starting index / ptr
    end_ptr = len(s) - 1 # End index / ptr
    chars = list(s)
    while start_ptr < end_ptr:
        c = chars[start_ptr]
        if c in vowels:
            c2 = chars[end_ptr]
            while c2 not in vowels:
                end_ptr -= 1
                c2 = chars[end_ptr]
            chars[start_ptr], chars[end_ptr] = chars[end_ptr], chars[start_ptr]
            end_ptr -=1
        start_ptr += 1
    return "".join(chars)

def reverse_vowels_optimised(s: str) -> str:
    vowels = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U']
    vowels_in_string = [c for c in s if c in vowels]
    result = [c if c not in vowels else vowels_in_string.pop() for c in s]
    return "".join(result)

