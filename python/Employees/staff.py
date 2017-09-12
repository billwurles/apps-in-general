__author__ = 'Will'
from employees import *
import os

class Staff:

    def __init__(self):
        self._employeeList=[]

    def addEmployee(self, name, number, yearsWorked, salary):
        self._employeeList.append(Employee(name,number))
        x = len(self._employeeList)
        x-=1
        self._employeeList[x].setYearsWorked(yearsWorked)
        self._employeeList[x].setSalary(salary)

    def findEmployee(self, number):
        for n in range(len(self._employeeList)):
            if number == self._employeeList[n].getNumber():
                return n

        return -1

    def removeEmployee(self, pos):
        del self._employeeList[pos]

    def getCurrentSalary(self, pos):
        return self._employeeList[pos].getSalary

    def increaseSalary(self, pos, amount):
        self._employeeList[pos].setSalary(amount+self._employeeList[pos].getSalary())

    def displayAllEmployees(self):
        print('Name\t\t\tNumber\t\tYears Worked\tSalary\t\tHoliday Entitlement\n')
        for n in range(len(self._employeeList)):
            name = self._employeeList[n].getName()
            number = self._employeeList[n].getNumber()
            years = self._employeeList[n].getYearsWorked()
            salary = self._employeeList[n].getSalary()
            holiday = self._employeeList[n].getHolidayEntitlement()
            if len(name) < 3 :
                print(name,'\t','\t','\t','\t',number,'\t','\t',years,'years','\t','\t','£',salary,'\t','\t',holiday,'days')
            elif len(name) < 13:
                print(name,'\t','\t','\t',number,'\t','\t',years,'years','\t','\t','£',salary,'\t','\t',holiday,'days')
            else:
                print(name,'\t',number,'\t','\t',years,'years','\t','\t','£',salary,'\t','\t',holiday,'days')
        print('\n')


    def saveTable(self):
        print('\nSaving...')
        try:
            os.remove('Employees Table.txt')
            table=open('Employees Table.txt','a')
            for n in range(len(self._employeeList)):
                x=self._employeeList[n].getName()
                x=x+'_'
                x=x+str(self._employeeList[n].getNumber())
                x=x+'_'
                x=x+str(self._employeeList[n].getYearsWorked())
                x=x+'_'
                x=x+str(self._employeeList[n].getSalary())
                x=x+'_'
                x=x+str(self._employeeList[n].getHolidayEntitlement())
                x=x+'\n'
                table.write(x)
            table.close()
            print('List saved.\n')
        except IOError as err:
            print('There was an error saving the file:\n',err)
        finally:
            table.close()

    def loadTable(self):
        print('\nLoading...')
        self._employeeList=[]
        try:
            table = open('Employees Table.txt','r')
            for lines in table:
                list = lines.split('_')
                x=list.pop()
                name=str(list[0])
                number=int(list[1])
                years=int(list[2])
                salary=int(list[3])
                self.addEmployee(name,number,years,salary)
            table.close()
            self.displayAllEmployees()
        except IOError as err:
            print('There was an error loading the file:\n',err)
        finally:
            table.close()