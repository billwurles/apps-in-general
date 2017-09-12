__author__ = '14038690'
total = 0
valid = True
while valid == True:
    numberOfTickets = int(input("please enter the number of tickets to be bought: "))
    if numberOfTickets <= 0:
        print("You need at least one ticket")
    else:
        valid == False
for x in range(numberOfTickets):
    age = int(input("what is the next person's age? "))
    if age <= 16:
        cost = 17
    elif age < 65:
        cost = 25
    else:
        cost = 20
    total += cost
print("The cost of all the tickets is: ", total)