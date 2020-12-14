import re

mem = dict()

memLine = re.compile(r'^mem\[(\d+)\] = (\d+)')

def setRecursive(value, address, maskFloat):
    if maskFloat != 0:
        maskBit = 1
        while maskBit & maskFloat == 0:
            maskBit <<= 1
        setRecursive(value, address | maskBit, maskFloat & ~maskBit)
        setRecursive(value, address & ~maskBit, maskFloat & ~maskBit)
    else:
        mem[address] = value

maxFloat = 0
with open("Day14/input.txt") as input:
    for line in input:
        if line.startswith("mask"):
            totalFloat = 0
            maskFloat = 0
            maskOr = 0
            maskStr = line[len("mask ="):].strip()[::-1]
            for i in range(36):
                if maskStr[i] == 'X':
                    maskFloat |= 1 << i
                    totalFloat += 1
                elif maskStr[i] == '1':
                    maskOr |= 1 << i
        else:
            match = memLine.match(line)
            address = int(match.group(1))
            value = int(match.group(2))

            setRecursive(value, address | maskOr, maskFloat)

print(sum(mem.values()))