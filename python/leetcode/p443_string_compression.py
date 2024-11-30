from typing import List


def compress(chars: List[str]) -> int:
    ptr = 0
    while True:
        if ptr == len(chars):
            break
        c = chars[ptr]
        count = 1
        ptr += 1

        while ptr < len(chars) and chars[ptr] == c:
            count += 1
            chars.pop(ptr)

        if count > 1:
            for digit in str(count):
                chars.insert(ptr, digit)
                ptr += 1

    val = "".join(chars)
    return len(val)