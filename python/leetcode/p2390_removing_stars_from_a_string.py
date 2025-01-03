def remove_stars(s: str) -> str:
    res = []
    for c in s:
        if c != '*':
            res.append(c)
        else:
            res.pop()
    return "".join(res)