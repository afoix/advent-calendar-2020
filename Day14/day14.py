import re

mem = dict()

memLine = re.compile(r'^mem\[(\d+)\] = (\d+)')

with open("Day14/input.txt") as input:
    for line in input:
        if line.startswith("mask"):

            # This line is changing the mask
            # Break it down into two components:

            maskAnd = 0 # Bits that are unchanged (X in the mask)
            maskOr = 0  # Bits that are being set to 1 (1 in the mask)

            # All other bits will get set to 0

            # Read the mask string itself, reversed so that we process the LSB first
            maskStr = line[len("mask ="):].strip()[::-1]
            
            # Process each bit
            for i in range(36):
                # If it is an X, set the corresponding bit in the maskAnd
                if maskStr[i] == 'X':
                    maskAnd |= 1 << i
                # If it is a 1, set the corresponding bit in the maskOr
                elif maskStr[i] == '1':
                    maskOr |= 1 << i
        else:
            # This line is changing memory - read and parse it with the regex
            match = memLine.match(line)
            address = int(match.group(1))
            value = int(match.group(2))

            # Set the value, using the maskAnd/maskOr to modify the bits as needed
            mem[address] = (value & maskAnd) | maskOr

# Final result
print(sum(mem.values()))