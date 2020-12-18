import re
sum = 0

parensTerm = re.compile(r'\(([^\(\)]+)\)')
singleTerm = re.compile(r'(\d+)\s*$')
additionTerm = re.compile(r'(\d+) \+ (\d+)')
multiplicationTerm = re.compile(r'(\d+) \* (\d+)')

def evaluate(line):
    parensMatch = parensTerm.search(line)
    while parensMatch is not None:
        line = line[:parensMatch.start(0)] + str(evaluate(parensMatch.group(1))) + line[parensMatch.end(0):]
        parensMatch = parensTerm.search(line)

    singleTermMatch = singleTerm.match(line)
    if singleTermMatch is not None:
        return int(singleTermMatch.group(1))

    additionMatch = additionTerm.search(line)
    while additionMatch is not None:
        operand1 = int(additionMatch.group(1))
        operand2 = int(additionMatch.group(2))
        line = line[:additionMatch.start(0)] + str(operand1 + operand2) + line[additionMatch.end(0):]
        additionMatch = additionTerm.search(line)

    multiplicationMatch = multiplicationTerm.search(line)
    while multiplicationMatch is not None:
        operand1 = int(multiplicationMatch.group(1))
        operand2 = int(multiplicationMatch.group(2))
        line = line[:multiplicationMatch.start(0)] + str(operand1 * operand2) + line[multiplicationMatch.end(0):]
        multiplicationMatch = multiplicationTerm.search(line)
    
    return int(line)

with open("Day18/input.txt") as input:
    for line in input:
        sum += evaluate(line.strip())

print (sum)

