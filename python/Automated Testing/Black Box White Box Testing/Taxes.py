__author__ = '14038690'
loop = True
taxP=0
tax=0
salary=0
salaryR=0
while loop==True:
    try:
        tax = float(input("enter your tax rate in the range 0 - 20%: "))
        if tax > 20 or tax < 0:
            print("You entered an incorrect value, please try again")
        else:
            salary = float(input("enter your salary: "))
            if salary < 0:
                print("You entered an incorrect value, please try again")
            else:
                loop=False
                taxP = (tax/100)*salary
                salaryR = salary - taxP
    except ValueError:
        print("You didn't enter a number")

print("You have paid £",taxP,", and have £",salaryR,"remaining")
