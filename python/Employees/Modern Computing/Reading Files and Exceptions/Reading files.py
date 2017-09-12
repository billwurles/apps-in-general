__author__ = '14038690'

myFile = open('testfile.txt','w')
try:
    word=[]
    word2=[]


    text=myFile.readline()

    (word,void)=text.split('\n')
    word=word.split(' ')


    text=myFile.readline()

    word2=text.split(' ')


    for i in range(len(word)):
        if i<len(word2):
            print(word[i],'\t',word2[i])
        else:
            print(word[i])

    text=myFile.readline()
except IOError as err:
    print('File error:', str(err))
finally:
    if myFile in locals():
        myFile.close()
myFile.close()