__author__ = '14038690'

class Students:
    def __init__(self, name, course):
        self._name = name
        self._course = course
        self._modules = []

    def addModules(self):
        number = int(input('Enter number of modules: '))
        for i in range(number):
            self._modules.append(input("Enter module name: "))

    # def getStudent(self):
    #     x=input('Which student would you like to look at: ')
    #     if x in Students._name:
    #
    def displayInformation(self):
        print('Student:',self._name,'\tCourse:',self._course)
        print('\tModules:',self._modules)

def inputStudent():
    newName=input("Name: ")
    newCourse = input('Course: ')
    newStudent=Students(newName,newCourse)
    newStudent.addModules()



x=input('Which student? ')
x.displayInformation()