def sum_two_smallest_numbers(numbers):

    # Reference solution, if you want to be concise / performance doesn't matter
    # return sum(sorted(numbers)[:2])

    smallest = None
    next_smallest = None
    for n in numbers:
        if smallest is None or n < smallest:
            next_smallest = smallest
            smallest = n
        elif (next_smallest is None) or (n < next_smallest):
            next_smallest = n
    return smallest + next_smallest