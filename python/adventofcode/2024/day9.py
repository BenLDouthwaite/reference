raw_input = "2333133121414131402"

raw_input = open("./puzzleInputs/aoc_puzzle_input_2024_day9.txt").read()

disk_map = [*map(int, list(raw_input))]
file = True # toggle
prev = None

memory = []
val = 0
for i in range(len(disk_map)):
    if file:
        memory += disk_map[i] * [str(val)]
    else:
        memory += disk_map[i] * ['.']
        val += 1
    file = not file

initial_memory = memory.copy()

ptr_l = 0
ptr_r = len(memory) - 1

while ptr_l < ptr_r:
    if memory[ptr_l] != '.':
        ptr_l += 1
    else:
        memory[ptr_l], memory[ptr_r] = memory[ptr_r], memory[ptr_l]
        ptr_r -= 1

checksum_total = 0
for i in range(len(memory)):
    if memory[i] == '.':
        break
    else:
        checksum_total += i * int(memory[i])

print(checksum_total)
# assert checksum_total == 6259790630969

# p2
memory = initial_memory.copy()

ptr_l = 0
ptr_r = len(memory) - 1

free_space = 0
while True:
    if memory[ptr_r] == '0':
        break

    ptr_l = memory.index('.')

    while ptr_l < ptr_r and memory[ptr_r] == '.':
        ptr_r -= 1

    blocks_count = 1
    while memory[ptr_r - blocks_count] == memory[ptr_r]:
        blocks_count += 1

    free_space = 0
    while free_space < blocks_count and ptr_l < ptr_r:
        while ptr_l < ptr_r and memory[ptr_l] != '.':
            ptr_l += 1

        free_space = 1
        while memory[ptr_l + free_space] == memory[ptr_l]:
            free_space += 1

        if free_space >= blocks_count:
            memory[ptr_l: ptr_l + blocks_count], memory[ptr_r - blocks_count + 1: ptr_r + 1] = memory[ptr_r - blocks_count + 1: ptr_r + 1], memory[ptr_l: ptr_l + blocks_count]
            ptr_r -= blocks_count
            break
        else:
            ptr_l += 1
    else:
        ptr_r -= blocks_count # Didn't find space, ignore that block and move on.

p2_checksum_total = 0
for i in range(len(memory)):
    if memory[i] == '.':
        continue
    else:
        p2_checksum_total += i * int(memory[i])

print(p2_checksum_total)