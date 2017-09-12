### Number Checker

#x=int(input("Enter a number: "))
#y=int(input("Do it again: "))
#
#if(x==y):
#    print("That was the same number")
#elif(x>y):
#    print(x," is a larger number than ",y)
#else:
#    print(y," is a larger number than ",x)
#



### Appointment checker
#
#a1=int(input("Enter time of first appointment: "))
#d1=int(input("Enter duration "))
#a2=int(input("Enter time of second appointment: "))
#d2=int(input("Enter duration "))
#
#x=a1+d1
#y=a2+d2
#if a1<a2:
#    if
#    a2<=x:
#        print("Your appointments overlap")
#    elif a2==a1:
#        print("Your appointments overlap")
#    else:
#        print("You have no overlaps")
#else:
#    if a1<=y:
#        print("Your appointments overlap")
#    elif a2==a1:
#        print("Your appointments overlap")
#    else:
#        print("You have no overlaps")




### What is your water?

#temp=int(input("Enter water temperature: "))
#
#if temp<100 and temp>0:
#    print("The water is water")
#elif temp<1:
#    print("The water is freezing")
#else:
#    print("The water is boiling")



### Grades

##grade=int(input("Enter your grade: "))
##valid = True
##
##if grade<0 or grade>100:
##    valid = False
##
##if valid == False:
##    print("invalid grade")
##else:
##    if grade>70 :
##        print("You got an A")
##    elif grade<70 and grade>=60 :
##        print("You got an B+")
##    elif grade<60 and grade>=50 :
##        print("You got an B")
##    elif grade<50 and grade>=40 :
##        print("You got an C")
##    elif grade<40 and grade>=30 :
##        print("You have to resit")
##    elif grade<30 :
##        print("You got anf Fail")




### Boolean expressions

#a=int(input("go"))
#b=int(input("yes"))

#if a and b <5:
#    print("nice")
#elif a or b <5:
#    print("cool")
#else:
#    print("damn")


### Days in a month

##month = int(input("Enter the month number! "))
##error = False
##
##if month>12 or month<1:
##    error = True
##else:
##    if month == 1 or month == 3 or month == 5 or month == 7 or month == 8 or month == 10 or month == 12:
##        print("There are 31 days in that month")
##    elif month == 4 or month == 6 or month == 9 or month == 11:
##        print("There are 30 days in that month")
##    else:
##        print("There are 28 days in the month, or 29 on a leap year")
##
##if error == True:
##    print("That is not a valid date")

### Is it a leap year?

##year=int(input("Enter the year: "))
##
##rem = year % 4
##rem1 = year % 100
##rem2 = year % 400
##
##
##if rem == 0 and rem1 != 0 or rem2 == 0:
##    print("It's a leap year")
##else:
##    print("It's not a leap year")



### What is the time?

seconds = int(input("Enter some seconds: "))

minutes = seconds // 60
minrem = seconds % 60

hours = minutes // 60
hourem = minutes % 60

days = hours // 24
dayrem = hours % 24


print("That is ",seconds," seconds")
print("That is ",minutes," minutes")
print("That is ",hours," hours")
print("That is ",days," days")
print("Or it's ",days," days, ",dayrem," hours, ",hourem," minutes and ",minrem," seconds.")

























    

