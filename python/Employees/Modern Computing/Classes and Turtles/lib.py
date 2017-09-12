__author__ = '14038690'
#filename = lib.py
import turtle
import random

def createScreen():
    turtle.setup(800, 800)
    window=turtle.Screen()
    window.bgcolor('lightblue')
    return window

def createShape():
    shape=turtle.Turtle('turtle')
    shape.color('black')
    shape.pensize(3)
    return(shape)

def getHex():
    r=lambda: random.randint(0,255)
    return str('#%02X%02X%02X' % (r(),r(),r()))

class Polygon:
    def __init__(self, sides, length):
        self.sides=sides
        self.length=length
        self.pos=[0,0] #x,y

    def getPerimeter(self):
        return self.length * self.sides

    def setPos(self,x,y):
        self.pos[0]=x
        self.pos[1]=y

    def getShape(self):
        names={3:'Triange',4:'Square',5:'Pentagon',6:'Hexagon',
               7:'Septagon',8:'Octogon',9:'Nonogon',10:'Decagon'}

        if (self.sides in names):
            return names[self.sides]
        else:
            return "I didn't add that name"

    def getAngle(self):
        x = ((self.sides-2)*180)/self.sides
        x = 180-x
        x=int(x)
        return x

    def displayInfo(self):
        print('The shape is a',self.getShape())
        print('There are',self.sides,'sides of',self.length,'mm')
        print('The perimeter is',self.getPerimeter())
        print('The object is at position',self.pos[0],',',self.pos[1])

    def drawShape(self,shape,window):
        x=self.getAngle()
        shape.penup()
        shape.goto(self.pos[0],self.pos[1])
        shape.pendown()
        shape.color(getHex())
        shape.begin_fill()
        for i in range(self.sides):
            shape.forward(self.length)
            shape.left(x)

        shape.end_fill()

