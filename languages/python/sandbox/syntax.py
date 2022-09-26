# If statements
x = int(input("Please enter an integer: "))
# x = 12
if x < 0:
    x = 0
    print('Negative changed to zero')
elif x == 0:
    print('Zero')
elif x == 1:
    print('Single')
else:
    print('More')

# For loops
words = ['cat', 'window', 'defenestrate']
for w in words:
    print(w, len(w))

# Range function
for i in range(5):
    print(i)

# in keyword
if 'ben' in ('ben', 'john', 'adam'):
    print('yup, ben is there')


# keyword arguments
def parrot(voltage, state='a stiff', action='voom', type='Norwegian Blue'):
    print("-- This parrot wouldn't", action, end=' ')
    print("if you put", voltage, "volts through it.")
    print("-- Lovely plumage, the", type)
    print("-- It's", state, "!")


parrot(1000)  # 1 positional argument
parrot(voltage=1000)  # 1 keyword argument
parrot(voltage=1000000, action='VOOOOOM')  # 2 keyword arguments
parrot(action='VOOOOOM', voltage=1000000)  # 2 keyword arguments
parrot('a million', 'bereft of life', 'jump')  # 3 positional arguments
parrot('a thousand', state='pushing up the daisies')  # 1 positional, 1 keyword

answer = input("Will i meet dave chapelle").strip()
if answer == "yes" or answer == "yup" or answer == "y" or answer == "ye":
    print("yes indeedy")
