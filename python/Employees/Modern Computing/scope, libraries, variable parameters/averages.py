__author__ = '14038690'


def average(*rest):
    x = len(rest)
    avr=0
    for y in range(x):
        avr=avr+int(rest[y])
        print(avr)
    return avr/x
    print(x)


print('The average of the numbers is:',average(1,2,3,4,5))