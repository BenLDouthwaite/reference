
def is_page_valid(page_list):
    prev_page = None
    # TODO Update to use indexes instead
    for page in page_list:
        page_valid = is_page_step_valid(page, prev_page)
        if not page_valid:
            return False
        prev_page = page
    return True


def is_page_step_valid(page, prev_page):
    if prev_page is not None:
        if prev_page in rules.get(page, []):
            return False
    return True

# Old Faithful hack variant of bubblesort
def sort_page_list(page_list):
    for j in range(len(page_list) - 1):
        for i in range(len(page_list) - 1 - j):
            # If "Is bigger than" in traditional sort
            if page_list[i] in rules.get(page_list[i + 1], []):
                page_list[i], page_list[i + 1] = page_list[i + 1], page_list[i]
    return page_list


# with open("./puzzleInputs/aoc_day5_example.txt") as file:
with open("./puzzleInputs/aoc_puzzle_input_2024_day5.txt") as file:
    (ordering_rules, page_lists) = file.read().split("\n\n")
    ordering_rules = ordering_rules.splitlines()
    page_lists = [*map(lambda page_string: [*map(int, page_string.split(","))], page_lists.splitlines())]

    rules = dict()
    for rule in ordering_rules:
        l, r = rule.split("|")
        rules[int(l)] = rules.get(int(l), []) + [int(r)]

    valid_page_lists = [page_list for page_list in page_lists if is_page_valid(page_list)]
    total_mid_pages = sum([page_list[len(page_list) // 2] for page_list in valid_page_lists])
    print(total_mid_pages)
    assert total_mid_pages == 7074

    # p2 - Get invalid pages - sort them based on ordering rules - sum middle values
    invalid_page_lists = [page_list for page_list in page_lists if not is_page_valid(page_list)]
    for page_list in invalid_page_lists: sort_page_list(page_list)

    total_mid_pages_p2 = sum([page_list[len(page_list) // 2] for page_list in invalid_page_lists])
    print(total_mid_pages_p2)
    assert total_mid_pages_p2 == 4828
