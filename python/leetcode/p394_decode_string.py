def decode_string(s: str) -> str:
    stack = []
    for c in s:
        if c == ']':

            back_stack = []
            while stack and stack[-1] != '[':
                back_stack.insert(0, stack.pop())
            stack.pop()

            count = []
            while stack and stack[-1].isdigit():
                count.insert(0, stack.pop())

            mult = int(''.join(count))
            stack += mult * back_stack
        else:
            stack.append(c)

    return ''.join(stack)
