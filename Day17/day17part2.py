world = {}
with open("Day16/input.txt") as input:
    y = 0
    for line in input:
        x = 0
        for char in line.strip():
            world[(x, y, 0, 0)] = (char == '#')
            x += 1
        y += 1

(minX, minY, minZ, minW) = (0, 0, 0, 0)
(maxX, maxY, maxZ, maxW) = (8, 8, 1, 1)

for i in range(6):
    (minX, minY, minZ, minW) = (minX-1, minY-1, minZ-1, minW-1)
    (maxX, maxY, maxZ, maxW) = (maxX+1, maxY+1, maxZ+1, maxW+1)
    newWorld = {}
    activeCount = 0
    for x in range(minX, maxX):
        for y in range(minY, maxY):
            for z in range(minZ, maxZ):
                for w in range(minW, maxW):
                    pos = (x, y, z, w)
                    state = pos in world and world[pos]
                    active_neighbors = 0
                    for dx in [-1, 0, 1]:
                        for dy in [-1, 0, 1]:
                            for dz in [-1, 0, 1]:
                                for dw in [-1, 0, 1]:
                                    if (dx, dy, dz, dw) == (0, 0, 0, 0): continue
                                    pos = (x+dx, y+dy, z+dz, w+dw)
                                    if pos in world and world[pos]:
                                        active_neighbors += 1
                    if active_neighbors == 3 or (active_neighbors == 2 and state):
                        newWorld[(x, y, z, w)] = True
                        activeCount += 1
    world = newWorld

print(activeCount)