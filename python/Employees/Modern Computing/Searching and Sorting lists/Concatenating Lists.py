__author__ = '14038690'
ok=True
while ok:
    myList = [1, 2, 3, 4, 5, 4, 3, 2, 1]
    y = int(input('Enter a number'))
    ok = True
    for x in range(len(myList)):
        r = myList[x]
        if y > r and y <= r+1:
            myList.insert(x+1, y)
        if y <= r-1 and y > r:
            myList.insert(x-1,y)
    print(myList)
    p = input('Do you want to insert a value or delete one from the list - I/D')
    if p == 'D' or 'd':
        t = int(input('Where would you like to delete from'))
        del(myList[t])
        print(myList)