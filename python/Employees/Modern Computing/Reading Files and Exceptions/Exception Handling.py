__author__ = '14038690'

try:
    num=(int(input('Enter a number: ')))
    for n in range(1,5):
        print(num/n)


except ZeroDivisionError:
    print('zero division')
except ArithmeticError:
    print('arithmetic')
except ValueError as err:
    print('value error:',str(err))
print('continue')