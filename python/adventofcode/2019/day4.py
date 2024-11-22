
# 1048 = p1
passing = []

def is_valid(val):
    decreasing = False
    consecutive_digits = False
    exactly_2_consecutive = False
    for i in range(1, len(val)):
        if int(val[i - 1]) > int(val[i]):
            decreasing = True
        # Part 1
        # if int(val[i - 1]) == int(val[i]):
        #     consecutive_digits = True

        # Part 2- need an update to check there's at least 1 pair of consecutive digits, triplets don't count

    if val[0] == val[1] and val[1] != val[2]:
            exactly_2_consecutive = True
    for i in range(1, len(val) - 2):
        v0, v1, v2, v3 = val[i-1:i+3]
        if v0 != v1 and v1 == v2 and v2 != v3:
            exactly_2_consecutive = True
    if val[5] == val[4] and val[4] != val[3]:
        exactly_2_consecutive = True

    # part1

    # return (not decreasing) and consecutive_digits

    return (not decreasing) and exactly_2_consecutive

for value in range(246515, 739105):
    if is_valid(str(value)):
        passing.append(value)

print(len(passing))