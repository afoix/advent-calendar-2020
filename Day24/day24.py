state = set()
with open ('Day24/input.txt') as input:
    for line in input:
        ew = 0
        ns = 0
        i = 0
        while i < len(line.strip()):
            c = line[i]
            if c == 'e':
                ew -= 1
            elif c == 'w':
                ew += 1
            elif c == 'n' or c == 's':
                if c == 'n':
                    ns -= 1
                elif c == 's':
                    ns += 1
                i += 1
                c = line[i]
                if c == 'e':
                    ew -= 0.5
                elif c == 'w':
                    ew += 0.5
            i += 1
        tile = (ew, ns)
        if tile in state:
            state.remove(tile)
        else:
            state.add(tile)
print(len(state))

def adjacent_positions(x, y):
    result = set()
    result.add((x-1, y))
    result.add((x+1, y))
    result.add((x-0.5, y+1))
    result.add((x+0.5, y+1))
    result.add((x-0.5, y-1))
    result.add((x+0.5, y-1))
    return result

def generate_all_candidate_positions(state):
    result = set(state)
    for (x, y) in state:
        result = result.union(adjacent_positions(x, y))
    return result

def count_adjacent(state, position):
    (x, y) = position
    result = 0
    for adj_position in adjacent_positions(x, y):
        if adj_position in state:
            result += 1
    return result

def step(state):
    all_candidate_positions = generate_all_candidate_positions(state)
    newState = set()
    for position in all_candidate_positions:
        is_currently_black = (position in state)
        black_tiles_adjacent = count_adjacent(state, position)
        if black_tiles_adjacent == 2 or (is_currently_black and black_tiles_adjacent == 1):
            newState.add(position)
    return newState

for i in range(100):
    print("Day {}: {}".format(i, len(state)))
    state = step(state)

print(len(state))
