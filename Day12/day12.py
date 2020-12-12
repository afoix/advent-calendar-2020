offsets = [(1, 0), (0, 1), (-1, 0), (0, -1)]
direction = 0
position = (0, 0)

def move(offset, distance):
    global position
    (x, y) = position
    (dx, dy) = offset
    x += dx * distance
    y += dy * distance
    position = (x, y)

with open("Day12/input.txt") as input:
    for line in input:
        cmd = line[0]
        dist = int(line[1:])
        
        if cmd == "N":
            move(offsets[3], dist)
        elif cmd == "S":
            move(offsets[1], dist)
        elif cmd == "E":
            move(offsets[0], dist)
        elif cmd == "W":
            move(offsets[2], dist)
        elif cmd == "L":
            while dist > 0:
                direction = (direction + 3) % 4
                dist -= 90
        elif cmd == "R":
            while dist > 0:
                direction = (direction + 1) % 4
                dist -= 90
        elif cmd == "F":
            move(offsets[direction], dist)

(x, y) = position
print (abs(x) + abs(y))