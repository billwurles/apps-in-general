__author__ = '14038690'

def addLetter(guessIn,current,word):
    for x in range (len(word)):
        y = word[x]
        z = current[x]
        if y == guessIn:
            current.replace(current[x],guess)
    return current



for y in range (20):
    x = input('word: ')
    addLetter(x,'______','badger')