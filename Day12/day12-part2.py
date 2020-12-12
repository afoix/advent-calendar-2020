offsets = [(1, 0), (0, 1), (-1, 0), (0, -1)]
position = (0, 0)
waypoint_position = (10, -1)

def move(position, offset, distance):
    (x, y) = position
    (dx, dy) = offset
    x += dx * distance
    y += dy * distance
    position = (x, y)
    return position

with open("Day12/input.txt") as input:
    for line in input:
        cmd = line[0]
        dist = int(line[1:])
        
        if cmd == "N":
            waypoint_position = move(waypoint_position, offsets[3], dist)
        elif cmd == "S":
            waypoint_position = move(waypoint_position, offsets[1], dist)
        elif cmd == "E":
            waypoint_position = move(waypoint_position, offsets[0], dist)
        elif cmd == "W":
            waypoint_position = move(waypoint_position, offsets[2], dist)
        elif cmd == "L":
            while dist > 0:
                (x, y) = waypoint_position
                ry = -x
                rx = y
                waypoint_position = (rx, ry)
                dist -= 90
        elif cmd == "R":
            while dist > 0:
                (x, y) = waypoint_position
                rx = -y
                ry = x
                waypoint_position = (rx, ry)
                dist -= 90
        elif cmd == "F":
            position = move(position, waypoint_position, dist)

(x, y) = position
print (abs(x) + abs(y))