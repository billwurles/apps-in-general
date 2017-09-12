import math

def pythagoras(x,y):
    """Returns the length of the hypotenuse of a triangle with other side lengths x and y."""
    return math.sqrt(x**2 + y**2)

def distance(x1,y1,x2,y2) -> float:
    """Returns the distance between coordinates x1,y1 and x2,y2."""
    ox = x2 - x1
    oy = y2 - y1
    return pythagoras(ox,oy)
