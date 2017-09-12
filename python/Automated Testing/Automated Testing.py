import math
import random

def pythagoras(x,y):
    """Returns the length of the hypotenuse of a triangle with other side lengths x and y.
    >>> pythagoras(10,20)
    22.360679774997898
    """
    return math.sqrt(x**2 + y**2)

def distance(x1,y1,x2,y2) -> float:
    """Returns the distance between coordinates x1,y1 and x2,y2.
    >>> distance(12,20,30,43)
    29.206163733020468
    """
    ox = x2 - x1
    oy = y2 - y1
    return pythagoras(ox,oy)

import unittest
class TestTower(unittest.TestCase):
    def setUp(self):
        s=Tower('one',10,10,1,1,1)
        z=Tower('two',10,11,100,100,100)
        f=Tower('three',10,20,40,40,40)
        r=Tower('four',100,100,1,1,-10)

    def test_canAttack(self):
        s.canAttack(f)
        check = False
        f.canAttack(z)
        check = True
        
    def test_intact(self):
        r.intact()
        check = True
        f.intact()
        check=False
        
    def test_hit(self):
        s.hit(50)
        
    def test_attack(self):
        f.attact(z)
        

class Tower:
    """Represents a guard tower which is located at a particular place and can attack any other tower within a given range."""
    def __init__(self, name, x, y, sight, strength, health):
        self.name = name
        self.x = x
        self.y = y
        self.sight = sight
        self.strength = strength
        self.health = health

    def canAttack(self,target):
        """Returns true if this tower ca+ attack target (another tower).

        A tower can attack another tower if the distance to the other tower is less than or equal to its sight."""
        dist = distance(self.x, self.y, target.x, target.y)
        return (dist <= self.sight)

    def intact(self):
        """Returns true if this tower is still intact.

        A tower is destroyed if its health is reduced to 0. If the tower is not destroyed, it is intact."""
        return self.health <= 0

    def hit(self,damageTaken):
        """Called when this tower is damaged.

        Applies damage to the tower's health and prints a message if it has been destroyed. If the tower is already destroyed its health does not continue to go down."""
        if not self.intact():
            return
        self.health -= damageTaken
        if not self.intact():
            print(self.name,"crumbles!")

    def attack(self,target):
        """Has this tower try to attack target (another tower).

        If this tower is destroyed or cannot attack the other tower, nothing happens. Otherwise, it tries to hit the other tower. The chance of missing is greater when the other tower is further away."""
        if not self.canAttack(target):
            return
        if not self.intact():
            return
        dist = distance(self.x, self.y, target.x, target.y)
        chance = (dist/self.sight)*100
        roll = random.randint(1,100)
        print(self.name,"attacks",target.name,end=" ")
        if roll < chance:
            print("for",self.strength,"points of damage.")
            target.hit(self.strength)
        else:
            print("but missed.")
        
    def display(self):
        print(self.name,'\n',self.x,self.y,'\n',self.sight,'\n',self.strength,'\n',self.health)

import doctest
doctest.testmod()
