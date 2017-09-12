__author__ = '14038690'
#filename: lib.py

def higherValue(num,high):
    """Finds the higher value of two variables"""
    if num>high:
        high = num
        return high
    else:
        return high

def rangeCheck(low,top,val):
    """Checks if the number is in range"""
    if val>top:
        return False
    elif val<low:
        return False
    else:
        return True

def getValue(low,high,msg):
    '''Gets a valid value within a range'''
    assert low<high
    ok = False
    while not ok:
        try:
            number = int(input(msg))
            if rangeCheck(low,high,number):
                ok = True
            else:
                print('The number is out of range')
                ok = False
        except ValueError:
            print("Please enter a valid number")
    return number