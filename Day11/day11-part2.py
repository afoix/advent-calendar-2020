with open('Day11/seatsOccupiedPattern.txt') as input:
    seats = [[char for char in line.strip()] for line in input]

(maxX, maxY) = (len(seats[0]), len(seats))

def countOccupiedSeatsVisibleFrom(seats, x, y):
    
    occupied = 0

    for dx in [-1, 0, 1]:
        for dy in [-1, 0, 1]:
            # Do not count the seat in the middle
            if dx == 0 and dy == 0: continue

            # Trace a ray along (dx, dy), testing the seat at each position
            distance = 1
            while True:
                # Calculate the position we are looking at this time around
                (ix, iy) = (x + distance * dx, y + distance * dy)

                # Stop tracing if we hit the edge of the area
                if ix < 0 or iy < 0: break
                if ix >= maxX or iy >= maxY: break

                # If this is floor, keep tracing past it
                if seats[iy][ix] == '.':
                    distance += 1
                    continue

                # It's a seat - if it is occupied, count it, then stop tracing
                if seats[iy][ix] == '#': 
                    occupied += 1
                break

    return occupied

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

            occupied = countOccupiedSeatsVisibleFrom(seats, x, y)

            # If the seat is occupied, and has more than 5 occupied adjacent seats,
            # it becomes empty
            if seats[y][x] == '#' and occupied >= 5:
                step[y][x] = 'L'
                any_changes = True

            # If the seat is empty, and has no occupied adjacent seats, 
            # it becomes occupied
            elif seats[y][x] == 'L' and occupied == 0:
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