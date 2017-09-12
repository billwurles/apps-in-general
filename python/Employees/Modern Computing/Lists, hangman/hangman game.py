__author__ = '14038690'

import hangman
def underscores(word):
    out=''
    for x in range(len(word)):
        out=out+'_'
    return out
def showStatus(soFar,pos,guesses):
    print(soFar)
    drawHangmanGraph(pen,pos)
    drawHangman(pos)
    print(guesses)
def getGuess():
    ok=True
    while ok:
        guess=input('Enter a letter: ')
        if len(guess)>1:
            print('You can only enter one letter')
            ok = True
        elif guess not in guesses:
            guesses.append(guess)
            return guess
            ok = False
        elif guess in guesses:
            print('You have already guessed that letter')
            ok = True
def addLetter(guessIn,current,word):
    assert len(current) == len(word)
    assert guess in correct
    newSoFar=''
    for x in range (len(word)):
        y = word[x]
        z = current[x]

        if y == guessIn:
            newSoFar+=guessIn
        else:
            newSoFar+=z
    return newSoFar

correct=getWord()
print(correct)
sofar=underscores(correct)
hangman=0
#inithangman(pen)

guesses=[]
dead='You lose'
success='You Won!'

while ((hangman < 6)and(sofar != correct)):
    showStatus(sofar,hangman,guesses)
    guess=getGuess()
    if guess in correct:
        sofar = addLetter(guess,sofar,correct)
    else:
        hangman+=1

if hangman>=6:
    print(dead)
    showStatus(sofar,hangman,guesses)
else:
     print(success)