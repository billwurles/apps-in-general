__author__ = 'Will Burles 14038690'
#Setting up the variables:

valid = False
valid2 = False
valid3 = False

mark = 0 #the mark that the user just entered
modules=0 #how many modules have had marks submitted
total=0 #the total of all marks
lowest=101 #the lowest grade - set to an impossibly high value so the first value is always lower

#the grade boundaries:
first=0
two1=0
second=0
third=0
fail=0

def rangeCheck(val,lo,hi,msg): #defines a function that checks if a number is within an acceptable range
    if val<lo:
        print('You entered',msg,'less than',lo,' please try again') #prints an error that tells the user what range they should be above, and contains a variable describing what they should enter
        return False #if the user inputs a number lower than the lowest value the function returns False
    elif val>hi:
        print('You entered',msg,'more than',hi,' please try again') #prints an error that tells the user what range the should be below, and contains a variable describing what they should enter
        return False #if the user inputs a number higher than the highest value the function returns False
    else:
        return True #if the user enters a valid value, the function returns true

errorMsg='You did not enter a valid number, please try again'


while valid==False:
    valid2=False

    try:
        mark = int(input("Enter your mark between 0 - 100: "))
        range=rangeCheck(mark,0,100,'a mark')
        if not range: #if range check returns False then the loop goes around again
            valid=False



        elif range: #if the range check is true than the program continues
            modules+=1 # Records how many modules the user has completed


            if mark<lowest: #if the mark entered is lower than the lowest, that number is discarded and the new lowest is saved
                lowest=mark

            if mark<40: #separates the fails
                fail+=1
                total=total+mark
            elif mark<50: #seperates the thirds
                third+=1
                total=total+mark
            elif mark<60: #seperates the seconds
                second+=1
                total=total+mark
            elif mark<70: #seperates the 2:1s
                two1+=1
                total=total+mark
            else:
                first+=1
                total=total+mark#the rest must be firsts
            print(modules,total,lowest) #used in testing to check the total, lowest and module counter were working correctly


            while valid2==False:
                valid2=True #prevents the loop from starting again

                if modules<=15:
                    cont=input('Do you want to enter another grade? Y/N: ')

                    if cont=='N' or cont=='n':
                        valid=True #prevents the main loop from starting again, moving onto the next part of the code

                    elif cont=='Y' or cont=='y': #Keeps the main loop going so that more grades can be entered
                        valid=False

                    else:
                        print('That was not a valid answer, try again.')
                        valid2=False #if the user enters anything other than 'y' or 'n', they are prompted again
                else:
                    valid=True #if 16 modules have already been entered, the user isn't asked if they want to add more, and the main loop is stopped

    except ValueError: #if the user enters anything other than an integer, an error is printed and the loop is started again
        print(errorMsg)

print("",first,"Firsts\n",two1,"2:1s\n",second,"Seconds\n",third,"Thirds\n",fail,"Fails") #tells the user what grade boundaries they fall under

total=total-lowest #discards the lowest grade
modules-=1 #subtracts one from the modules counter

if modules<16: #if the user has remaining modules than they can enter their desired average grade
    while valid3==False:
        try:
            print('Your average grade is: ',(total/modules)) #tells the user their current average grade
            desAvg = int(input ("Enter your desired average grade: ")) #takes the users desired average grade
            range = rangeCheck(desAvg,0,100,'an average') #calls the range check function to make sure users are within range

            if not range:
                valid3=False #if rangecheck is false the loop goes around again

            elif range: #if range check is true the code continus
                desMark = desAvg*modules #calculates how many marks in total the user needs for their desired average

                modRem = 15 - modules #calculates how many modules the user has remaining
                result = (desMark-total)+(modRem*desAvg) #calculates how many more marks the user needs to reach their desired average

                if desMark<total:
                    print("You've already exceeded that grade, aim higher")
                    valid3=False #if the user enters a desired average lower than their current grade, they are asked to enter another average
                else:
                    print("To achieve that you need",result,"more marks to achieve it on your",modRem,"remaining modules")
                    valid3=True #prints out how many more marks the user needs and how many modules they have left, then ends the loop


        except ValueError:
            print(errorMsg) #if the user enters anything other than an integer, an error is printed and the loop starts again

else: #if the user has already completed 16 modules their final grade is printed
    print("Your final grade average is",total/modules)

