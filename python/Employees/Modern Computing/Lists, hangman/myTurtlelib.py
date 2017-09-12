import turtle

def createScreen(col, width, height) :
    turtle.setup(width, height)
    window=turtle.Screen()
    window.bgcolor(col)
    return window
    
def createShape(shape, col, pen):
	shape=turtle.Turtle(shape)
	shape.color(col)
	shape.pensize(pen)
	return(shape)

def drawSquare(shape, size):
    for i in range(4):
        shape.forward(size)
        shape.left(90)

def initTurtle(shape, initx, inity):
    shape.penup()
    shape.goto(initx,inity)
    
