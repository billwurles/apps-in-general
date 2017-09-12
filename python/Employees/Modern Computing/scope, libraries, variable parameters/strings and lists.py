__author__ = '14038690'

def nuLine(*x):
    count=len(x)
    r=0
    for y in range(count):
        r = x[y]
        r+='\n'
        print(r)

nuLine('hello','what','am','I','writing')