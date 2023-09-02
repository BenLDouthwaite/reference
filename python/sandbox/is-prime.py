import sys


def is_prime(n):
    if n == 2 or n == 3: return True
    if n < 2 or n % 2 == 0: return False
    if n < 9: return True
    if n % 3 == 0: return False
    r = int(n ** 0.5)
    f = 5
    while f <= r:
        '\t', f
        if n % f == 0: return False
        if n % (f + 2) == 0: return False
        f += 6
    return True


def main(n):
    n = int(n)
    print(is_prime(n))


if __name__ == "__main__":
    if len(sys.argv) == 2:
        main(sys.argv[1])
    else:
        print('Incorrect number of arguments ( only file name and single int allowed) ')
