window = []
with open('Day9/input.txt') as file:
    for i in range(25):
        window.append(int(file.readline()))
    while True:
        line = file.readline().strip()
        if line == '': break
        value = int(line)

        anySums = False
        for x in window:
            anySums = anySums or any([x+y == value for y in window])
        if not anySums:
            break

        window.pop(0)
        window.append(value)

print(value)

with open('Day9/input.txt') as file:
    allNumbers = [int(line) for line in file]

allNumbers = [number for number in allNumbers if number < value]

for setSize in range(2, len(allNumbers)):
    for start in range(0, len(allNumbers) - setSize + 1):
        set = allNumbers[start:(start+setSize)]
        if sum(set) == value:
            print(min(set)+max(set))
