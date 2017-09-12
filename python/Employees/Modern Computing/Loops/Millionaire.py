__author__ = '14038690'
x = 1
while x <= 1:
    invest = int(input('How much money are you investing? '))

    year = 0
    interest = invest

    while interest < (invest * 2):
        year+=1
        interest*=1.05
    print('It took ',year, 'years to double your investment to £',interest)
