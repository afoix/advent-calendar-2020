with open('Day3/treemap.txt') as f:
    map = [line.strip() for line in f]
slopeWidth = len(map[0])

def countSlope(deltaX, deltaY):
    result = 0
    positionX = 0
    for positionY in range(0, len(map), deltaY):
        if map[positionY][positionX % slopeWidth] == '#':
            result += 1
        positionX += deltaX
    return result

print(countSlope(3, 1))

print(countSlope(1,1) * countSlope(3, 1) * countSlope(5, 1) * countSlope(7, 1) * countSlope(1, 2))