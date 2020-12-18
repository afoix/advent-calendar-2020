import re
sum = 0

parensTerm = re.compile(r'\(([^\(\)]+)\)')
singleTerm = re.compile(r'(\d+)\s*$')
simpleTerm = re.compile(r'(\d+) (\+|\*) (\d+)(.*)')

def evaluate(line):
    parensMatch = parensTerm.search(line)
    while parensMatch is not None:
        line = line[:parensMatch.start(0)] + str(evaluate(parensMatch.group(1))) + line[parensMatch.end(0):]
        parensMatch = parensTerm.search(line)

    singleTermMatch = singleTerm.match(line)
    if singleTermMatch is not None:
        return int(singleTermMatch.group(1))

    simpleTermMatch = simpleTerm.match(line)
    operand1 = int(simpleTermMatch.group(1))
    operation = simpleTermMatch.group(2)
    operand2 = int(simpleTermMatch.group(3))
    remainder = simpleTermMatch.group(4)

    if operation == '+':
        value = operand1 + operand2
    else:
        value = operand1 * operand2
    
    return evaluate(str(value) + remainder)

with open("Day18/input.txt") as input:
    for line in input:
        sum += evaluate(line.strip())

print (sum)

