import re

mem = dict()

memLine = re.compile(r'^mem\[(\d+)\] = (\d+)')

with open("Day14/input.txt") as input:
    for line in input:
        if line.startswith("mask"):
            maskAnd = 0
            maskOr = 0
            maskStr = line[len("mask ="):].strip()[::-1]
            for i in range(36):
                if maskStr[i] == 'X':
                    maskAnd |= 1 << i
                elif maskStr[i] == '1':
                    maskOr |= 1 << i
        else:
            match = memLine.match(line)
            address = int(match.group(1))
            value = int(match.group(2))
            mem[address] = (value & maskAnd) | maskOr
print(sum(mem.values()))