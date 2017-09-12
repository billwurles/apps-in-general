from tkinter import *


class BankAccount:
    """Bank account class with balance and interest rate"""

    def __init__(self, balance, number):
        """Contruct a bank account.All the bank Account has 5% interesta rate"""
        self._balance=balance
        self._rate=0.05
        self._acNumber=number

    def getBalance(self):
        return self._balance

    def getNumber(self):
        return self._acNumber

    def deposit(self, amount):
        """add amount to the bank account balance
        PRE: amount > 0
        POST: amoiunt added to balance
        """
        self._balance+=amount

    def withdraw(self, amount):
        """ withdraw amount from the balance
        PRE: amount > balance
        POST: amount substracted from balance
        """
        self._balance-=amount
        
    def calculateInterest(self):
        """ calculate the interest ofthe money in the bank account
        POST interest is returned
        """
        return self._balance*self._rate 


    def canWithdraw(self,amount):
        return self._balance>amount

def clickWith():
    try:
        if acc.canWithdraw(amount.get()):
            acc.withdraw(amount.get())
        else:
            messagebox.showwarning(message='You do not have enough money in your account')
        balance.set(acc.getBalance())
    except ValueError:
        messagebox.showerror(message='Error, please only enter numbers')
def clickDep():
    try:
        acc.deposit(amount.get())
        balance.set(acc.getBalance())
    except ValueError:
        messagebox.showerror(message='Error, please only enter numbers')
    
acc=BankAccount(1000,123)

root=Tk()
root.title('Bank Account')

Label(root, text='Balance: ').grid(row=0,column=0)
balance=IntVar()
balance.set(acc.getBalance())
Label(root, textvariable=balance).grid(row=0,column=1)

Label(root, text='Amount: ').grid(row=1,column=0)
amount=IntVar()
Entry(root, textvariable=amount).grid(row=1,column=1)

withdraw=Button(root,text='Withdraw',command=clickWith).grid(row=2,column=0)
deposit=Button(root,text='Deposit',command=clickDep).grid(row=2,column=1)
quitBtn=Button(root,text='Exit',command=root.destroy).grid(row=2,column=2)

root.mainloop()
