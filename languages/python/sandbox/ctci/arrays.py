import string

# CTCI 1.1
def uniquecharacters(input):
    charFound = [False for i in range(len(string.ascii_lowercase))]
    for char in input:
        if charFound[ord(char) -97]:
            return False
        charFound[ord(char) - 97] = True
    return True
