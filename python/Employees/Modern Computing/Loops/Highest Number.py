__author__ = '14038690'
r = 1
while r > 0:
    x = 1
    q = 0
    while x != 0:
        x = int(input('Enter a number, enter 0 to stop: '))
        if x > q:
            q = x

    print(q)