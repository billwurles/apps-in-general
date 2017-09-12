__author__ = '14038690'
def myLinearSearch(item,sequence):
    i = 0
    while i < len(sequence) and sequence[i]!=item:
        i +=1
    return i

def mySentinelSearch(item,sequence):
    sequence.append(item)
    i = 0
    while sequence [i]!=item:
        i +=1
    return i

def myBubbleSort(sequence):
    for i in range(0, len(sequence)-1):
        for j in range (0,len(sequence)-i-1):
            if sequence[j] > sequence [j+1]:
                temp = sequence[j]
                sequence[j]=sequence[j+1]
                sequence[j+1]=temp
    return sequence

def myInsertionSort(sequence):
    for i in range (1,len(sequence)):
        item=sequence[i]
        j=i
        while j > 0 and sequence[j-1] > item:
            sequence[j]=sequence[j-1]
            j-=1
        sequence[j]=item
    return sequence



myList=[56,34,7,23,7,5]
x = int(input('Enter a value '))

myList=myInsertionSort(myList)
print(myList)

if x in myList:
    r = mySentinelSearch(x,myList)
    t = myList.count(x)
    print('yes it is in %s and there are %s of them'%(r,t))

else:
    print('gayeeeeeeeeee')