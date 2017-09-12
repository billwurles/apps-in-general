import PracticeTest

loop=0
players=[]
while loop!=4:
    print('would you like this add a player, delete a player, display data or quit')
    loop=int(input('1/2/3/4: '))
    if loop == 1:
        name=input('What is the players name: ')
        team=input('What is the players team: ')
        players.append(Player(name,team))
    if loop == 2:
        name = input('What is the name of the player you want to delete: ')
        players = deletePlayer(name,players)
    if loop == 3:
        displayData(players)

print('Goodbye')
