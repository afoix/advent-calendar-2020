import math

with open('Day13/input.txt') as input:
    # Read the earliest time at which the ferry will arrive at the bus station
    earliest = int(input.readline().strip())
    # Read the buses
    buses = input.readline().strip().split(',')

# Discard the 'x' entries and convert the rest from strings to numbers
busNumbers = [int(id) for id in buses if id != 'x']

# Calculate the time to the next bus for each bus
modulos = [-earliest % busNumber for busNumber in busNumbers]

# Find out which one has the shortest wait
shortestWait = min(modulos)
nextBus = busNumbers[modulos.index(shortestWait)]

print(shortestWait * nextBus)

# Coefficients for the Chinese Remainder Theorem solver on WolframAlpha
setOfR = [busNumber - buses.index(str(busNumber)) for busNumber in busNumbers]
setOfM = busNumbers

print ("Paste this into WolframAlpha :)")
print ("ChineseRemainder[{{{}}}, {{{}}}]".format(",".join([str(r) for r in setOfR]), ",".join([str(m) for m in setOfM])))
