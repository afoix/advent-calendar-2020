import re

mem = dict()

memLine = re.compile(r'^mem\[(\d+)\] = (\d+)')

def setRecursive(value, address, maskFloat):
    # If there are any bits still set in maskFloat:
    if maskFloat != 0:
        # Find the lowest bit that is set
        maskBit = 1
        while maskBit & maskFloat == 0:
            maskBit <<= 1
        # Set all the addresses where the floating bit is a 1
        setRecursive(value, address | maskBit, maskFloat & ~maskBit)
        # Set all the addresses where the floating bit is a 0
        setRecursive(value, address & ~maskBit, maskFloat & ~maskBit)
    else:
        # No floating bits, just set the address directly
        mem[address] = value

with open("Day14/bitmaskDebug.txt") as input:
    for line in input:
        if line.startswith("mask"):
            # Just like before, collect bits that are floating and bits we set to 1
            maskFloat = 0
            maskOr = 0
            maskStr = line[len("mask ="):].strip()[::-1]
            for i in range(36):
                if maskStr[i] == 'X':
                    maskFloat |= 1 << i
                elif maskStr[i] == '1':
                    maskOr |= 1 << i
        else:
            match = memLine.match(line)
            address = int(match.group(1))
            value = int(match.group(2))

            setRecursive(value, address | maskOr, maskFloat)

print(sum(mem.values()))