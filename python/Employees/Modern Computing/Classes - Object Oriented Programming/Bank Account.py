__author__ = '14038690'

class BankAccount:
    def __init__(self,balance,number):
        self._balance=balance
        self._number=number
        self._rate=0.05

    def deposit(self,amount):
        self._balance = self._balance + amount

    def withdraw(self,amount):
        if self.canWithdraw(amount):
            self._balance = self._balance - amount
        else:
            print("You haven't got the cash")

    def calcInterest(self):
        interest = self._balance*self._rate
        return interest

    def getBalance(self):
        print('The balance of account',self._number,'is',self._balance)

    def canWithdraw(self,amount):
        if amount < self._balance:
            return True


def makeAccount():
    newNum = input('Enter Account Number: ')
    newBal = int(input('Enter balance: '))
    return BankAccount(newBal,newNum)


acc = makeAccount()
acc.getBalance()

acc.deposit(50)
acc.getBalance()

acc.withdraw(75)
acc.getBalance()

acc2=makeAccount()
acc2.getBalance()

x=acc2.calcInterest()
acc.deposit(x)
acc.getBalance()