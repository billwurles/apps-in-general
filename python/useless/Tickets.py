'''
Created on 25 Oct 2012

@author: p0068193
'''
print("welcome to the ticketing system.  We sell the following ticket types:")
print("standard tickets 24 pounds")
print("premium tickets  36 pounds")
ticketType = int(input("Please enter the number of the ticket type required <1:standard, 2:premium>: "))
while (ticketType != 1 and ticketType !=2):
    ticketType = int(input("sorry, that value was not recognised please try again <1:standard, 2:premium>: "))
haveDiscount = input ("do you have a discount code <y/n>: ")
discountMultiplier = 1
if haveDiscount == "y":
    needDiscount = True
    while needDiscount:
        discount = int(input("please enter a valid discount code or 0 to continue: "))
        if discount == 1234:
            needDiscount = False
            discountMultiplier = 0.9
        elif discount == 9870:
            needDiscount = False
            discountMultiplier = 0.8
        elif discount == 9999:
            needDiscount = False
            discountMultiplier = 0.6
        elif discount == 0:
            needDiscount = False
 
numberOfTickets = int(input("how many tickets do you want? "))
while numberOfTickets <= 0:
    numberOfTickets = int(input("sorry that value was not recognised.  How many tickets do you want? "))
if numberOfTickets == 0:
    print("as you don't require any tickets, the program will now end.  Thank you.")
else:
    if ticketType == 1:
        ticketPrice = 24
    else:
        ticketPrice = 36
    cost = numberOfTickets * discountMultiplier * ticketPrice     
    print("the cost of your purchase is: ", cost, "pounds")

        
         

    
