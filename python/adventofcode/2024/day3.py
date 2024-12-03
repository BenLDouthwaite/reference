import re

with open("./puzzleInputs/aoc_puzzle_input_2024_day3.txt") as file:
    input = file.read()

    # p1
    p1 = sum([int(a) * int(b) for a, b in re.findall(r'mul\((\d{1,3}),(\d{1,3})\)', input)])
    print(p1)

    # p2
    commands = re.findall(r"mul\(\d{1,3},\d{1,3}\)|do\(\)|don't\(\)", input)
    p2_total = 0
    enabled = True
    for command in commands:
        if command == "do()":
            enabled = True
        elif command == "don't()":
            enabled = False
        else:
            if enabled:
                a, b = re.findall(r'\d{1,3}', command)
                p2_total += int(a) * int(b)
    print(p2_total)

    assert p1 == 182619815
    assert p2_total == 80747545


