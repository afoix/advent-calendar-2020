import math

with open('Day13/input.txt') as input:
    earliest = int(input.readline().strip())
    buses = input.readline().strip().split(',')

busNumbers = [int(id) for id in buses if id != 'x']
modulos = [-earliest % busNumber for busNumber in busNumbers]

shortestWait = min(modulos)
nextBus = busNumbers[modulos.index(shortestWait)]

print(shortestWait * nextBus)

setOfR = [busNumber - buses.index(str(busNumber)) for busNumber in busNumbers]
setOfM = busNumbers

print ("Paste this into WolframAlpha :)")
print ("ChineseRemainder[{{{}}}, {{{}}}]".format(",".join([str(r) for r in setOfR]), ",".join([str(m) for m in setOfM])))
