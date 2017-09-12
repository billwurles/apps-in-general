__author__ = '14038690'

def hiLo(*x):
    count = len(x)
    hi=x[0]
    lo=x[0]
    for y in range(count):
        if x[y] > hi:
            hi = x[y]
        elif x[y] < lo:
            lo = x[y]
    print('The highest was',hi,'The lowest was',lo)

hiLo(1,2,3,45,76,4,21,34,6,43,23,)