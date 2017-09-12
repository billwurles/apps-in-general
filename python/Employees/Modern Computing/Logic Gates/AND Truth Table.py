__author__ = '14038690'
print('A | B | C | AND|OR')
for i in range(2):
    for j in range(2):
        for k in range(2):
            print(i, '|',j,'|',k, '|', i&j&k,' |',i|j|k)