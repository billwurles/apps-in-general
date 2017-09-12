__author__ = 'Will'

class Employee:
    def __init__(self,name,num):
        self._name=name
        self._num=num
        self._years=0
        self._salary=0
        self._hols=0

    def getName(self):
        return self._name

    def getNumber(self):
        return self._num

    def getYearsWorked(self):
        return self._years

    def getSalary(self):
        return self._salary

    def getHolidayEntitlement(self):
        self.setHolidayEntitlement()
        return self._hols

    def setYearsWorked(self,yearsWorked):
        self._years = yearsWorked

    def setSalary(self,salary):
        self._salary=salary

    def setHolidayEntitlement(self):
        if self._years < 5:
            self._hols = 22
        elif self._years >= 5 and self._years < 10:
            self._hols = 26
        elif self._years >= 10:
            self._hols = 30

    def printEmployee(self):
        print(self._name,self._num,self._salary,self._years,self._hols)