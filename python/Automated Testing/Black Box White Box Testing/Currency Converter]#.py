__author__ = '14038690'

cur = (input("What is the name of the currency you want to convert? "))
valid = True
pounds=1
rate=0
while valid == True:
    try:
        rate = float(input("What is the conversion rate? "))
    except ValueError:
        print("That was not a valid rate, please enter again")
    valid = False
while pounds!=0:
    pounds=int(input("Enter an amount in pounds to be converted, enter 0 to stop: "))
    print("That would be",pounds*rate,"in",cur)