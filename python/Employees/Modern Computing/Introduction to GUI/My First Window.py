__author__ = '14038690'

from tkinter import *

def click():

root = Tk()
root.title('Hello World')
lab = Label(root,text='Hello There')
lab.pack()
button=Button(root, text='Click Me')
button.pack()
root.mainloop
