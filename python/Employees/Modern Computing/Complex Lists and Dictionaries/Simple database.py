__author__ = '14038690'

teams=[]
x=0
while x < 4:
    y = input('Enter the names of your team: ')
    r=[]
    r.insert(x,y)
    teams.insert(x,r)
    x+=1
for x in range (len(teams)):
    r=[]
    y = int(input('Enter the wins for '+teams[x][0]))
    r.insert(x,y)
    y = int(input('Enter the losses for '+teams[x][0]))
    r.insert(x,y)
    y = int(input('Enter the draws for '+teams[x][0]))
    r.insert(x,y)
    y = int(input('Enter the total score for '+teams[x][0]))
    r.insert(x,y)
    teams[x].insert(1,r)
x = 0
while x < 5:
    x = int(input('Which team would like to see results for? 1/2/3/4 - 5 to exit'))
    if x<5:
        x-=1
        print('You selected',teams[x][0])
        print('\t',teams[x][1][0],'wins')
        print('\t',teams[x][1][1],'losses')
        print('\t',teams[x][1][2],'draws')
        print('\t',teams[x][1][3],'total points')
print('Goodbye')
print(teams)