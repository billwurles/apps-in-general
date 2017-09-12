__author__ = '14038690'
earnings = int(input("How much did you earn during the year? "))
if earnings < 12000:
    taxRate = 0.1
else:
    taxRate = 0.2

taxToPay = earnings * taxRate
print ("On earnings of ", earnings, " you would pay ", taxToPay, " tax.")