__author__ = '14038690'

import lib

val = 1
highest=0
while val!=0:
    val = lib.getValue(0,200,'Please enter a number, enter 0 to stop:')
    highest = lib.higherValue(val,highest)
print(highest)
