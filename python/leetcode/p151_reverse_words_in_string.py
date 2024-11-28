# This was slower than the second attempt.
def reverse_words_initial(s: str) -> str:
    words = [w.strip() for w in s.split()]
    words.reverse()
    return " ".join(words)

def reverse_words(s: str) -> str:
    res = ""
    for w in s.split():
        res = w + " " + res
    return res.rstrip()