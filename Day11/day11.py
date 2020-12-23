with open('Day11/seatsOccupiedPattern.txt') as input:
    seats = [[char for char in line.strip()] for line in input]

(maxX, maxY) = (len(seats[0]), len(seats))

def countOccupiedSeatsAdjacentTo(seats, x, y):
    
    adjacent = 0

    for dx in [-1, 0, 1]:
        for dy in [-1, 0, 1]:
            # Do not count the seat in the middle
            if dx == 0 and dy == 0: continue

            # Do not try to count positions that are outside the edges of the seating area
            if y + dy < 0 or x + dx < 0: continue
            if y + dy >= maxY or x + dx >= maxX: continue

            # If the seat is occupied, increment the counter
            if seats[y+dy][x+dx] == '#': 
                adjacent += 1

    return adjacent

def stepSimulation(seats):
    # Make a copy of the previous state
    step = [line.copy() for line in seats]

    # Keep track of whether anything actually changed in this step
    any_changes = False

    # Loop over all the positions
    for y in range(maxY):
        for x in range(maxX):
            # Skip floor
            if seats[y][x] == '.': continue

            adjacent = countOccupiedSeatsAdjacentTo(seats, x, y)

            # If the seat is occupied, and has more than 4 occupied adjacent seats,
            # it becomes empty
            if seats[y][x] == '#' and adjacent >= 4:
                step[y][x] = 'L'
                any_changes = True

            # If the seat is empty, and has no occupied adjacent seats, 
            # it becomes occupied
            elif seats[y][x] == 'L' and adjacent == 0:
                step[y][x] = '#'
                any_changes = True

    # If nothing changed, indicate that by returning None
    if not any_changes:
        return None

    # Otherwise, return the new state
    return step

# Main loop: Keep stepping the simulation until it no longer changes (returns None)
while True:
    step = stepSimulation(seats)
    if step is None: break
    seats = step

# Count the total number of occupied seats
total = sum([sum([1 for char in row if char == '#']) for row in seats])

print (total)