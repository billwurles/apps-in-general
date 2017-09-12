__author__ = '14038690'
from tkinter import *

def converter(miles):
    return miles*1.609

def clickConv():
    try:
        km.set(converter(miles.get()))
    except ValueError:
        messagebox.showerror(message='Error: You can only enter numbers')


root=Tk()
root.title('Miles to KM Converter')

Label(root, text='Enter Miles to be converted').grid(row=0,column=0)
miles=DoubleVar()
Entry(root,textvariable=miles).grid(row=0,column=1)

Label(root, text='Kilometers:').grid(row=1,column=0)
km=DoubleVar()

button=Button(root,text='Convert',command=clickConv)
button.grid(row=2,column=0)

Label(root,textvariable=km).grid(row=1,column=1)

quitBtn=Button(root,text='Exit',command=root.destroy)
quitBtn.grid(row=2,column=1)
root.mainloop()

