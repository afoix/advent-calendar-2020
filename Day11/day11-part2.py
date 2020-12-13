with open('Day11/input.txt') as input:
    seats = [[char for char in line.strip()] for line in input]

while True:
    step = [line.copy() for line in seats]
    any_changes = False
    for y in range(len(step)):
        row = step[y]
        for x in range(len(row)):
            if row[x] == '.': continue
            adjacent = 0
            for dx in [-1, 0, 1]:
                for dy in [-1, 0, 1]:
                    if dx == 0 and dy == 0: continue
                    i = 1
                    while True:
                        if y + i*dy < 0 or x + i*dx < 0: break
                        if y + i*dy >= len(step) or x + i*dx >= len(row): break
                        if seats[y+i*dy][x+i*dx] == '.':
                            i += 1
                            continue
                        if seats[y+i*dy][x+i*dx] == '#': adjacent += 1
                        break
            if row[x] == '#':
                if adjacent >= 5:
                    row[x] = 'L'
                    any_changes = True
            elif row[x] == 'L':
                if adjacent == 0:
                    row[x] = '#'
                    any_changes = True
    if not any_changes: break
    seats = step

total = sum([sum([1 for char in row if char == '#']) for row in seats])
print (total)