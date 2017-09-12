#Will Burles - 26/9/14 -


###NUMBER MULTIPLIER

#x=int(input("Pick a number, any number: ")) #number input
#x*=3 #Multiplies the number by 3
#print("As if by magic, your number is now ",x) #displays the number



###Packs of Crisps

#people=int(input("How many people will there be? ")) #gets number of people from user
#packs1 = people // 6 #finds out how many whole packs are needed
#packs2 = people % 6 #finds out the number of people without a pack
#if(packs2 >0):
#   packs1 +=1 #if there are more than 0 people who need crisps, then get one more pack

#print("You need", packs1, "Packs of Crisps") #prints how many packs you need




###Are you old?

#name=input("What is your first name? ")
#surname=input("What is your surname? ")
#age=int(input("How old are you? ")

#print(name," ",surname,"you are ",age,"and very close to the end")




### Fav Quote

#print('''"My favourite quote is:
#"I\'m afraid I can\'t do that dave"
#From the movie I saw in 2001''')



###Phrase Modulator

#phrase=input("What is your favourite phrase? ")



#print(phrase.upper())
#print(phrase.lower())
#print(phrase.swapcase())
#print(phrase.capitalize())
#print(phrase.title())
#print(phrase.strip())

#phrase1= phrase.replace("a",'spam')
#phrase1= phrase1.replace("e",'spam')
#phrase1= phrase1.replace("i",'spam')
#phrase1= phrase1.replace("o",'spam')
#phrase1= phrase1.replace("u",'spam')
#print(phrase1)




### TURTLE
import turtle

y=2000

win = turtle.Screen()
x = turtle.Turtle()
win.bgcolor("black")
x.speed(0)

y = 1
while y != 0:

    x.color("Cornflower Blue")
    x.forward(250)
    x.color("Medium Spring Green")
    x.left(90)
    x.pensize(2)
    x.forward(250)
    x.color("Light Slate Blue")
    x.pensize(3)
    x.left(90)
    x.forward(250)
    x.color("Light Coral")
    x.left(90)
    x.pensize(4)
    x.forward(250)
    x.color("Pale Violet Red")
    x.left(80)


win.mainloop()

    


### Change Giving

#initChange = float(input("How much change do you have? "))

#a = initChange // 2
#b = initChange % 2
#c = b // 1
#d = b % 1
#e = d // .50
#f = d % .50
#g = f // .20
#h = f % .20
#i = h // .10
#j = h % .10
#k = j // .05
#l = j % .05
#m = l // .02
#n = l % .02
#o = n // .01
#
#print("You had £",initChange,"and now have ",int(a)," £2's, ",int(c)," £1's ",int(e)," 50p's ",int(g)
#      ," 20ps ",int(i)," 10p's ",int(k)," 5p's ",int(m)," 2p's ",int(o)," 1p's")



