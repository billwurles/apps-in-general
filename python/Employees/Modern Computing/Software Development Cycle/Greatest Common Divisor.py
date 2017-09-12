__author__ = '14038690'

from lib import *
print("Works out the greatest common divisor and least common multiple")
x=int(input("Enter a number: "))
y=int(input("Enter a number: "))

if x<y:
    hi=y
    lo=x
else:
    hi=x
    lo=y

r = lcm(hi,lo)
t = gcd(hi,lo)
print("The LCM is,",r,"the GCD is",t)

