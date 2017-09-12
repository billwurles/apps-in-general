__author__ = '14038690'
import turtle

screen = turtle.Screen()
screen.bgcolor("lightyellow")

shape = turtle.Turtle("turtle")
shape.color("green")
shape.pensize(3)

shape.penup()

for n in range (1,2000,3):
    shape.stamp()
    shape.right(30)
    shape.forward(n)