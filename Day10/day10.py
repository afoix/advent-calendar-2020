import numpy as np

with open('Day10/input.txt') as input:
    adapters = [int(line) for line in input]

    adapters.sort()

    # add the built in adapter in the device
    deviceAdapter = max(adapters) + 3
    adapters.append(deviceAdapter)

    joltage = 0
    differences = {1:0, 2:0, 3:0}
    for adapter in adapters:
        differences[adapter - joltage] += 1
        joltage = adapter

    print(differences[1]*differences[3])

    adapters.insert(0, 0)

    adj = np.zeros((len(adapters), len(adapters)))
    for fromIndex in range(len(adapters) - 1):
        for toIndex in range(fromIndex+1, min(fromIndex + 4, len(adapters))):
            f = adapters[fromIndex]
            t = adapters[toIndex]
            if t <= f + 3:
                adj[fromIndex][toIndex] = 1

    total = 0
    mat = adj
    for pathLen in range(len(adapters)):
        pathsThisLen = int(mat[0][len(adapters)-1])
        total += pathsThisLen
        mat = mat.dot(adj)

    print (total)