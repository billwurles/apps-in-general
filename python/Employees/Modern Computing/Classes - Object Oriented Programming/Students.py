__author__ = '14038690'


class Student:
    '''Student has a name, takes a course and registers for a list of modules'''

    def __init__(self, name, course):
        '''creates a student object with a number and a course and an empty list of modules'''
        self._name = name
        self._course = course
        self._modules = []

    def addModules(self):
        number = int(input('Enter number of modules: '))
        for i in range(number):
            self._modules.append(input("Enter module name: "))

    def displayInformation(self):
        print('Student:',s._name,'\tCourse:',s._course)
        print('\tModules:',s._modules)


s = Student("John Smith","Computer Science")
s.addModules()
s.displayInformation()