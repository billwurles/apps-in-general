__author__ = '14038690'

#filename: lib.py
import turtle

def createScreen(col,width,height):
    turtle.setup(width,height)
    window=turtle.Screen()
    window.bgcolor(col)
    return window
def createShape(shape,col,pen):
    shape=turtle.Turtle(shape)
    shape.color(col)
    shape.pensize(pen)
    return(shape)

def drawSquare(x,size):
    x.penup()
    x.goto(-200,0)
    x.pendown()
    for y in range (5):
        for r in range (4):
            x.forward(size)
            x.right(90)
        x.penup()
        x.forward(size*1.5)
        x.pendown()

def encryptor(word,dist):
    encrypt=''
    ltr=0
    x=len(word)
    for y in range (x):
        r = word[y]
        asc=ord(r)
        asc=asc+dist
        ltr=chr(asc)
        encrypt=encrypt+ltr
    return encrypt

def decryptor(word,dist):
    decrypt=''
    ltr=0
    x=len(word)
    for y in range(x):
        r = word[y]
        asc=ord(r)
        asc=asc-dist
        ltr=chr(asc)
        decrypt=decrypt+ltr
    return decrypt

def gcd(x,y):
    r = x % y
    while r != 0:
        x = y
        y = r
        r = x % y
    return y

def lcm(x,y):
    r=(x*y)/gcd(x,y)
    return r

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