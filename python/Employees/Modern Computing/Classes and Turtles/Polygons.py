__author__ = '14038690'

from lib import *

ok = True
shapes=[]
y=0
while ok:
    x = input('Would you like to enter a shape: Y/N')
    if x == 'n' or x == 'N':
        ok = False
    elif x =='Y' or x=='y':
        sides=int(input('Enter the number of sides: '))
        length=int(input('Enter the length of the sides: '))
        posX=int(input('Enter the x coordinate: '))
        posY=int(input('Enter the y coordinate: '))

        shapes.append(Polygon(sides,length))
        shapes[y].setPos(posX,posY)
        y+=1

for y in range (len(shapes)):
    shapes[y].displayInfo()
    print('')

w=createScreen()
s=createShape()

for r in range (len(shapes)):
    shapes[r].drawShape(s,w)

w.mainloop()
