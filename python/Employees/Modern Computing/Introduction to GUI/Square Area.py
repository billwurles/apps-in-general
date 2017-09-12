from tkinter import *

def calcArea(w,h):
    return w*h

def clickArea():
    area.set(calcArea(height.get(),width.get()))

root=Tk()
root.title('Rectangle Area')

Label(root, text='Width').grid(row=0, column=0)
width = DoubleVar()
Entry(root,textvariable=width).grid(row=0,column=1)

Label(root, text='Height').grid(row=1,column=0)
height=DoubleVar()
Entry(root, textvariable=height).grid(row=1,column=1)

Label(root, text='Area').grid(row=2, column=0)
area=DoubleVar()

Label(root, textvariable=area).grid(row=2,column=1)

button=Button(root, text='Calculate area',command=clickArea)
button.grid(row=3,column=0,columnspan=2)

root.mainloop
