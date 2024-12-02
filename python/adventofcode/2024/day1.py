from math import factorial

with (open("./puzzleInputs/aoc_puzzle_input_2024_day1.txt") as file):
    # https://docs.python.org/3/whatsnew/3.5.html#whatsnew-pep-448 "Additional Unpacking Generalizations
    # Learned trick from : https://old.reddit.com/r/adventofcode/comments/1h3vp6n/2024_day_1_solutions/lztuvx8/
    values = [*(map(int, file.read().split()))]
    l1, l2 = sorted(values[0::2]), sorted(values[1::2])

    # p1
    print(sum([abs(l1[i] - l2[i]) for i in range(len(l1))]))

    # P2
    # Count instances in list into map / dict
    l2_counts = dict()
    for i in l2:
        l2_counts[i] = l2_counts.get(i, 0) + 1 # Get or default 0
    print(sum([i * l2_counts.get(i, 0) for i in l1]))
