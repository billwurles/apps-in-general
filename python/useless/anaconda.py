__author__ = 'Will Burles'

print("1. Burger")
print("2. Chips")
print("3. Pizza")
choice = int(input("What would you like?"))

if choice < 4:
    if choice == 1:
        cost = 20
    elif choice == 2:
        cost = 5
    elif choice == 3:
        cost = 25

    count = int(input("How many do you want?"))
    print("That will cost",cost*count)
#else:
#    assert choice == 3
#    cost = 25

else:
    print("Choose a correct option")

count = int(input("How many do you want?"))
print("That will cost",cost*count, "pounds")