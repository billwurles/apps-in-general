__author__ = '14038690'

def rangeCheck(val,low,top):
    """Checks if the number is in range"""
    if top!= None and val>top:
        return False
    elif low!= None and val<low:
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

def myProduct(a,b,c,d):
    return a*b*c*d

def myAverage(a,b,c,d):
    return (a+b+c+d)/4

x=getValue(-100,100,'Enter a number between -100 and 100: ')
y=getValue(-100,100,'Enter a number between -100 and 100: ')
z=getValue(-100,100,'Enter a number between -100 and 100: ')
q=getValue(-100,100,'Enter a number between -100 and 100: ')

print(myAverage(x,y,z,q))