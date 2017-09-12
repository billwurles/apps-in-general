import random,turtle

HANGMANSTEPS = ['''


	+---+
	|   |
            |
	    |
    	    |
            |
      =========''', '''


	+---+
        |   |
    	O   |
    	    |
    	    |
    	    |
      =========''', '''

	+---+
	|   |
    	O   |
        |   |
    	    |
    	    |
      =========''', '''


	+---+
	|   |
    	O   |
       /|   |
    	    |
    	    |
      =========''', '''

	+---+
    	|   |
    	O   |
       /|\  |
            |
            |
      =========''', '''


	+---+

	|   |
	O   |
       /|\  |
       /    |
       	    |
      =========''', '''


	+---+
        |   |
    	O   |
       /|\  |
       / \  |
            |
      =========''']






def inithangman(pen):
    pen.pendown()   
    pen.forward(400)
    pen.penup()
    pen.backward(300)
    pen.left(90)
    pen.pendown()
    pen.forward(300)
    pen.right(90)
    pen.forward(100)
    pen.penup()
   

def drawHangmanGraph(pen,count):
    if count==0:
        pen.pendown()
        pen.right(90)
        pen.forward(20)
    elif count==1:
        pen.penup()
        pen.right(45)
        pen.forward(10)
        pen.pendown()
        pen.circle(10)
        pen.penup()
        pen.backward(10)
        pen.left(45)
        pen.forward(20)
    elif count==2:
        pen.pendown()
        pen.forward(40)
        pen.penup()
    elif count==3:
        pen.backward(20)
        pen.left(90)
        pen.pendown()
        pen.backward(20)
        pen.penup()
    elif count==4:
         pen.forward( 20)
         pen.pendown()
         pen.forward(20)
         pen.penup()
         pen.backward(20)
         pen.right(90)
         pen.forward(20)
    elif count==5:
        pen.right(45)
        pen.pendown()
        pen.forward(20)
        pen.penup()
        pen.backward(20)
    else :
        pen.left(90)
        pen.pendown()
        pen.forward(20)
        
        




words = ['ant',  'baboon',  'badger', 'bat', 'bear', 'beaver',
         'camel', 'cat', 'clam', 'cobra', 'cougar', 'coyote', 'crow',
         'deer', 'dog', 'donkey', 'duck', 'eagle','ferret', 'fox', 'frog',
         'goat', 'goose', 'hawk', 'lion', 'lizard', 'llama', 'mole', 'monkey',
         'moose', 'mouse', 'mule', 'newt', 'otter', 'owl', 'panda', 'parrot',
         'pigeon', 'python', 'rabbit', 'ram', 'rat', 'raven', 'rhino', 'salmon',
         'seal', 'shark', 'sheep', 'skunk', 'sloth', 'snake', 'spider', 'stork',
         'swan', 'tiger', 'toad', 'trout', 'turkey', 'turtle', 'weasel', 'whale',
         'wolf', 'wombat', 'zebra']

def getWord() :
    pos = random. randint(0, len(words))
    return words[pos]


def drawHangman(pos) :
    print(HANGMANSTEPS[pos])
    
