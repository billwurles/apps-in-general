__author__ = '14038690'
loop=1
while loop==1:
    x=int(input("Enter a number: "))

    num = x
    answer = ""

    while num >= 1:
        answer = answer + '1'
        num = num // 2

    print(answer[::-1])