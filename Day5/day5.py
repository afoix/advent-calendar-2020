import math

def locateSeat(str):
    xMin=0
    xMax=7
    yMin=0
    yMax=127
    for char in str:
        if char == 'F':
            yMax = math.ceil((yMin + yMax)/2)
        elif char == 'B':
            yMin = math.ceil((yMin + yMax)/2)
        elif char == 'R':
            xMin = math.ceil((xMin + xMax)/2)
        elif char == 'L':
            xMax = math.ceil((xMin + xMax)/2)
    return (yMin, xMin)

def calcSeatId(row, column):
    return row * 8 + column

max = 0
ids = []
with open('Day5/input.txt') as file:
    while line := file.readline():
        (row, column) = locateSeat(line)
        seatId = calcSeatId(row, column)
        ids.append(seatId)
        if (seatId > max):
            max = seatId
    ids.sort()
    for i in range(ids[0]+1, len(ids) - 1):
        if not i in ids:
            print(i)
print(max)