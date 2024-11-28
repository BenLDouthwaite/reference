def greatest_common_divisor_of_strings(str1: str, str2: str) -> str:

    word1_len = len(str1)
    word2_len = len(str2)
    lcd = []
    max_len = max(word1_len, word2_len)
    for i in range(0, max_len):
        if i >= word1_len or i >= word2_len:
            break
        c1 = str1[i]
        c2 = str2[i]
        if c1 == c2:
            lcd.append(c1)
        else:
            # Chars don't match, end early
            return ""

    lcd_len = len(lcd)
    # Test possible greatest common divisors, from biggest to smallest
    for i in reversed(range(1, lcd_len + 1)):
        # Possible Greatest Common Divisor
        pos_gcd = ''.join([char for char in lcd[0:i]])

        pos_gcd_len = len(pos_gcd)
        # Ignore any where the possible GCD doesn't fit exactly in both words
        if word1_len % pos_gcd_len != 0 or word2_len % pos_gcd_len != 0:
            continue

        word1_match = True
        for j in range(0, word1_len):
            lcd_char = pos_gcd[(j % pos_gcd_len)]
            if str1[j] != lcd_char:
                word1_match = False

        word2_match = True
        for j in range(0, word2_len):
            lcd_char = pos_gcd[(j % pos_gcd_len)]
            # print(f"Checking index {j}, giving {str2[j]} and {lcd_char}")
            if str2[j] != lcd_char:
                word2_match = False

        if word1_match and word2_match:
            return pos_gcd
    return ""

# If y > x, the first pass flips their order
def gcd_recursive(x: int, y: int) -> int:
    if y == 0:
        return x
    else:
        return gcd_recursive(y, x % y)

def gcd(x: int, y: int) -> int:
    while y != 0:
        (x, y) = (y, x % y)
    return x
