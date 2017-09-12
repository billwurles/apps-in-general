__author__ = '14038690'

def rangeCheck(low,top,val):
    if val>top:
        return False
    elif val<low:
        return False
    else:
        return True

a=int(input("Enter the highest number in the range: "))
b=int(input("Enter the lowest number in the range: "))
c=int(input("Enter the number to check against: "))


out=rangeCheck(a,b,c)

if out == False:
    print('The number is out of range')
elif out == True:
    print('The number is in range')