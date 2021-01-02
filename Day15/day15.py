# Starting numbers
input = [1,0,15,2,10,13]

# lastSpokenAt is a map from each number to 'what the turn number was the last time this number was spoken'
lastSpokenAt = {}

# lastLastSpokenAt is a map from each number to 'what the turn number was the time before the last time this number was spoken'
lastLastSpokenAt = {}

# Start on turn 1
turn = 1

def speak(number):
    global turn, lastSpokenAt, lastLastSpokenAt, lastSpoken
    # If this number has been spoken before, move its entry from lastSpokenAt into lastLastSpokenAt
    if number in lastSpokenAt:
        lastLastSpokenAt[number] = lastSpokenAt[number]
    
    # Store this number into lastSpokenAt
    lastSpokenAt[number] = turn

    lastSpoken = number
    turn += 1

def playOneTurn():
    # "If that was the first time the number has been spoken..."
    # (i.e. the number is has no entry in the 'the time before last time' map):
    if lastSpoken not in lastLastSpokenAt:
        # "...the current player says 0."
        speak(0)

    # "Otherwise, the number had been spoken before; ..."
    else:
        # "...the current player announces how many turns apart the number is from when it was previously spoken."
        speak(lastSpokenAt[lastSpoken] - lastLastSpokenAt[lastSpoken])

# Initialize with the starting numbers
for number in input:
    speak(number)

# Part 1: what will be the 2020th number spoken?
while turn <= 2020:
    playOneTurn()

print(lastSpoken)

# Part 2: what will be the 30000000th number spoken?
while turn <= 30000000:
    playOneTurn()

print(lastSpoken)
