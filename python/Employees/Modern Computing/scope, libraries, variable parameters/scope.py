__author__ = '14038690'
def firstFn():
    global x
    x = 6
    print("from first",x)
def secondFn():
    print("from second",x)
x=4
firstFn()

secondFn()
print("outside func",x)