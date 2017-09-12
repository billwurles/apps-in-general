__author__ = '14038690'

days = 1
total = 0
while days <= 7:
    hours = int(input("How many hours worked?"))
    total = total + hours
    days +=1

print("You worked ",total,' hours this week')