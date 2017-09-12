'''
Created on 25 Oct 2012

@author: p0068193
'''

name = input("enter your name ").lower()
num1 = int(input("enter the first number "))
num2 = int(input("enter the second number"))

valid = True
if name[0] in ['a', 'b', 'c', 'd','e', 'f', 'g', 'h', 'i']:
    multiplier1 = 4
elif name[0] in ['j', 'k', 'l','m', 'n', 'o', 'p', 'q', 'r']:
    multiplier1 = 7
elif name[0] in ['s', 't','u', 'v', 'w', 'x', 'y', 'z']:
    multiplier1 = 9
else:
    valid = False
    print("error, invalid input")

if name[len(name)-1] in ['a', 'b', 'c', 'd','e', 'f', 'g', 'h', 'i']:
    multiplier2 = 2
elif name[len(name)-1] in ['j', 'k', 'l','m', 'n', 'o', 'p', 'q', 'r']:
    multiplier1 = 3
elif name[len(name)-1] in ['s', 't','u', 'v', 'w', 'x', 'y', 'z']:
    multiplier1 = 4
else:
    valid = False
    print("error, invalid input")

answer = ""
answer += str(12 * multiplier1 + num1*5)
answer+= str(46 * multiplier2 + num2*6)
answer = int(answer) - (multiplier1 * 2 + multiplier2 * 5)
answer = str(answer) + name[0]
answer += name[len(name)-1]

print( answer)




    
    
