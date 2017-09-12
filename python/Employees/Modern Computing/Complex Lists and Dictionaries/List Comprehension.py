__author__ = '14038690'


list=[]
for x in range(5):
    y=int(input('Enter a number: '))
    list.insert(x,y)
multList=[]

for y in range(len(list)):
    x=list[y]*6
    multList.insert(y,x)
print(multList)