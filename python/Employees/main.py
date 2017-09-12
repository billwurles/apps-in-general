__author__ = 'Will'
from staff import *

database = Staff()

def menuInput():
    valid=['A','R','I','D','S','L','Q','a','r','i','d','s','l','q']
    ok = False
    while not ok:
        choice=input('Enter the command you would like to run: ')
        if choice in valid:
            if len(choice) == 1:
                ok = True
                return choice
        else:
            print('You did not enter a valid option, please try again')

def displayMenu():
    print('A to add an employee\nR to remove an employee\nI to increase an employees salary\nD to display all employees\nS to save the list to a text file (This will delete current list)\nL to load the list from a text file\n')

def numInput(type):
    try:
        if type == 'number':
            x = int(input('Enter the employee number: '))
        if type == 'years':
            x = int(input('Enter how long the employee has worked here: '))
        if type == 'salary':
            x = int(input('Enter the salary of the employee: '))
        return x
    except ValueError:
        print('You did not enter a number, please try again')



choice = 0
while choice != 'q' or choice != 'Q':
    displayMenu()
    choice = menuInput()
    if choice == 'A' or choice == 'a':
        name = input('Enter the name of the Employee: ')
        unique=False
        while not unique:
            number = numInput('number')
            pos = database.findEmployee(number)
            if pos >= 0:
                print('That number is already taken, please try again')
            else:
                unique = True
        years = numInput('years')
        salary = numInput('salary')
        database.addEmployee(name,number,years,salary)

    if choice == 'R' or choice == 'r':
        if len(database._employeeList) == 0:
            print('There are no employees to delete\n')
        else:
            number = numInput('number')
            pos = database.findEmployee(number)
            if pos >= 0:
                database.removeEmployee(pos)
            elif pos == -1:
                print('Error, that employee cannot be found, returning to menu')

    if choice == 'I' or choice == 'i':
        if len(database._employeeList) == 0:
            print('There are no employees to increase\n')
        else:
            number = numInput('number')
            salary = numInput('salary')
            pos = database.findEmployee(number)
            if pos >= 0:
                database.increaseSalary(pos,salary)
            elif pos == -1:
                print('Error, that employee cannot be found, returning to menu')

    if choice == 'D' or choice == 'd':
        if len(database._employeeList) == 0:
            print('There are no employees to display\n')
        else:
            database.displayAllEmployees()

    if choice == 'S' or choice == 's':
        database.saveTable()

    if choice == 'L' or choice == 'l':
        database.loadTable()