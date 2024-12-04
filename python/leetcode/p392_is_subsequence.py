def is_subsequence(s: str, t: str) -> bool:
    n = len(s)
    if n == 0:
        return True
    s_ptr = 0
    for c in t:
        if s[s_ptr] == c:
            s_ptr += 1
            if s_ptr == n:
                return True
    return False