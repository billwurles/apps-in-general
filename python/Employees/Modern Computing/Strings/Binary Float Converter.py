__author__ = '14038690'
x = float(input("Enter a number with a decimal place: "))
exp = 0
while x>=2:
    x/=2
    exp+=1

exp+=64

man = x//1

y=0.5
loop = 8
answer=""
while loop > 0:
    if y < man:
        answer + "1"
    else:
        answer + "0"
    y/=2
    loop-=1

print("The exponent is ",exp,", The Mantissa is ",man)