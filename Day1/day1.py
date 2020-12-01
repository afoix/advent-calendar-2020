path = "numbersList.txt"

with open(path) as f:
    numbers = [int(line) for line in f]

for i in range(0, len(numbers)):
    for j in range(i+1, len(numbers)):
        if numbers[i] + numbers[j] == 2020:
            print(numbers[i] * numbers[j])

for i in range(0, len(numbers)):
    for j in range(i+1, len(numbers)):
        for k in range(j+1, len(numbers)):
            if numbers[i] + numbers[j] + numbers[k] == 2020:
                print(numbers[i] * numbers[j] * numbers[k])
