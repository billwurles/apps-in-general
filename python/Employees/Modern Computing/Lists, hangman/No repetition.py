__author__ = '14038690'

def repetitionCheck(wordIn,wordList=[]):
    '''checks if you have repeated words, returns true if no repetition, false if words are repeated'''
    if wordIn not in wordList:
        wordList.append(wordIn)
        return wordList
    else:
        return wordList
        #print("You have already entered that word")

f='stop'
x=0
list=[]
while x!=f:
    x=input('go ')
    r=repetitionCheck(x)
    if not r:
        print('You repeated')
    else:
        print(list)