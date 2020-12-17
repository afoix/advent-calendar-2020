world = {}
with open("Day16/input.txt") as input:
    y = 0
    for line in input:
        x = 0
        for char in line.strip():
            world[(x, y, 0)] = (char == '#')
            x += 1
        y += 1

(minX, minY, minZ) = (0, 0, 0)
(maxX, maxY, maxZ) = (8, 8, 1)

for i in range(6):
    (minX, minY, minZ) = (minX-1, minY-1, minZ-1)
    (maxX, maxY, maxZ) = (maxX+1, maxY+1, maxZ+1)
    newWorld = {}
    activeCount = 0
    for x in range(minX, maxX):
        for y in range(minY, maxY):
            for z in range(minZ, maxZ):
                pos = (x, y, z)
                state = pos in world and world[pos]
                active_neighbors = 0
                for dx in [-1, 0, 1]:
                    for dy in [-1, 0, 1]:
                        for dz in [-1, 0, 1]:
                            if (dx, dy, dz) == (0, 0, 0): continue
                            pos = (x+dx, y+dy, z+dz)
                            if pos in world and world[pos]:
                                active_neighbors += 1
                if active_neighbors == 3 or (active_neighbors == 2 and state):
                    newWorld[(x, y, z)] = True
                    activeCount += 1
    world = newWorld

print(activeCount)