import string

# CTCI 1.1
def uniqueCharacters(input):
    charFound = [False for i in range(len(string.ascii_lowercase))]
    for char in input:
        if charFound[ord(char) -97]:
            return False
        charFound[ord(char) - 97] = True
    return True

print(uniqueCharacters("test")) # False
print(uniqueCharacters("abcdefg")) # True

# TODO How to setup 'pythonic' unit tests, from terminal
