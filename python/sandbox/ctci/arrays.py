import string


# CTCI 1.1
def unique_characters(input):
    char_found = [False for i in range(len(string.ascii_lowercase))]
    for char in input:
        if char_found[ord(char) - 97]:
            return False
        char_found[ord(char) - 97] = True
    return True
