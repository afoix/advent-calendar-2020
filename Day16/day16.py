import re
import math

rules = {}
with open('Day16/input.txt') as input:
    fieldDefinition = re.compile(r'^(.*): (\d+)-(\d+) or (\d+)-(\d+)')
    while True:
        line = input.readline().strip()
        if len(line) == 0:
            break
        match = fieldDefinition.match(line)
        rules[match.group(1)] = (int(match.group(2)), int(match.group(3)), int(match.group(4)), int(match.group(5)))

    input.readline()
    myTicket = [int(num) for num in input.readline().strip().split(',')]

    input.readline()
    input.readline()
    errorRate = 0
    validTickets = []
    while True:
        line = input.readline().strip()
        if len(line) == 0:
            break
        ticket = [int(num) for num in line.split(',')]

        allFieldsValid = True
        for num in ticket:
            isFieldValid = False
            for min1, max1, min2, max2 in rules.values():
                if (num >= min1 and num <= max1) or (num >= min2 and num <= max2):
                    isFieldValid = True
                    break
            if not isFieldValid:
                errorRate += num
                allFieldsValid = False

        if allFieldsValid:
            validTickets.append(ticket)

print (errorRate)

validTickets.append(myTicket)
ruleFieldPossibilities = {rule: (1 << len(rules)) - 1 for rule in rules.keys()}

for ticket in validTickets:
    for i, num in enumerate(ticket):
        for ruleName, ruleRanges in rules.items():
            (min1, max1, min2, max2) = ruleRanges
            if not ((num >= min1 and num <= max1) or (num >= min2 and num <= max2)):
                ruleFieldPossibilities[ruleName] &= ~(1 << i)

fieldAssignments = {}
assignedMask = 0
while len(ruleFieldPossibilities) > 0:
    anyResolved = False
    for ruleName, mask in ruleFieldPossibilities.items():
        if bin(mask).count("1") == 1:
            anyResolved = True
            fieldIndex = int(math.log2(mask))
            fieldAssignments[ruleName] = fieldIndex
            assignedMask |= mask
    if not anyResolved:
        break
    for ruleName in fieldAssignments.keys():
        if ruleName in ruleFieldPossibilities:
            del ruleFieldPossibilities[ruleName]
    for ruleName, mask in ruleFieldPossibilities.items():
        ruleFieldPossibilities[ruleName] = mask & ~assignedMask

departureFieldIndices = [value for key, value in fieldAssignments.items() if key.startswith('departure')]
print(math.prod([myTicket[i] for i in departureFieldIndices]))