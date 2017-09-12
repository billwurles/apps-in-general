__author__ = '14038690'
items=[]
while len(items)!= 5:
    x = float(input('Enter 5 numbers'))
    items.append(x)
print(items)
for y in range(len(items)):
    r = items[y]
    print('%.3f'%(r))