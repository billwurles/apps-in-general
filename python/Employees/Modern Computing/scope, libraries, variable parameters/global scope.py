__author__ = '14038690'
global x
x = 44
def firstFn():
    x = 4
    print('from first',x)

def secondFn():

    print('from second',x)

firstFn()

secondFn()
print('outside function',x)
