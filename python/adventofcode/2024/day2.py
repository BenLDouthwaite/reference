def is_safe(levels) -> bool:
    diffs = [levels[i + 1] - levels[i] for i in range(len(levels) - 1)]
    return all(1 <= x <= 3 for x in diffs) or all(-3 <= x <= -1 for x in diffs)

def is_safe_with_single_removal(levels) -> bool:
    return any(is_safe(levels[:i] + levels[i + 1:]) for i in range(len(levels)))

with open("./puzzleInputs/aoc_puzzle_input_2024_day2.txt") as file:
    lines = [line.rstrip() for line in file]
    report_list = [[*map(int, report.split())] for report in lines]
    print(sum(map(is_safe, report_list))) # p1
    print(sum(map(is_safe_with_single_removal, report_list))) # p2
