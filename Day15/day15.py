input = [1,0,15,2,10,13]
lastSpokenAt = {}
lastLastSpokenAt = {}

for i in range(len(input)):
    lastSpokenAt[input[i]] = i + 1 # turns are 1-based, not 0-based

lastSpoken = input[-1]
turn = len(input) + 1

while turn <= 2020:
    if lastSpoken not in lastLastSpokenAt:
        lastSpoken = 0
    else:
        lastSpoken = lastSpokenAt[lastSpoken] - lastLastSpokenAt[lastSpoken]
    if lastSpoken in lastSpokenAt:
        lastLastSpokenAt[lastSpoken] = lastSpokenAt[lastSpoken]
    lastSpokenAt[lastSpoken] = turn
    turn += 1

print(lastSpoken)

while turn <= 30000000:
    if lastSpoken not in lastLastSpokenAt:
        lastSpoken = 0
    else:
        lastSpoken = lastSpokenAt[lastSpoken] - lastLastSpokenAt[lastSpoken]
    if lastSpoken in lastSpokenAt:
        lastLastSpokenAt[lastSpoken] = lastSpokenAt[lastSpoken]
    lastSpokenAt[lastSpoken] = turn
    turn += 1

print(lastSpoken)
