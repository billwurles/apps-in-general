class Player:

    def __init__(self,name,team):
        self._name=name
        self._team=team
        self._score=0

    def getNick(self):
        return self._name

    def getTeam(self):
        return self._team

    def getScore(self):
        return self._score

    def incrementScore(self):
        self._score+=1

    def changeNick(self,new):
        self._name=new

    def changeTeam(self,new):
        self._team=new

def deletePlayer(name,players):
    for n in range(len(players)):
        if name in players[n].getNick():
            del players[n]
            return players

def displayData(players):
    print(' ')
    for r in range(len(players)):
        print('Name:',players[r].getNick())
        print('Team:',players[r].getTeam())
        print('Score:',players[r].getScore())
        print(' ')
    



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
                
    
