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
                    if y + dy < 0 or x + dx < 0: continue
                    if y + dy >= len(step) or x + dx >= len(row): continue
                    if seats[y+dy][x+dx] == '#': adjacent += 1
            if row[x] == '#':
                if adjacent >= 4:
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