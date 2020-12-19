import re
ruleRegex = re.compile("(\d+):(.*)")
rules = {}
messages = []
with open("Day19/input.txt") as input:
    for line in input:
        if len(line.strip()) == 0:
            break
        match = ruleRegex.match(line)
        ruleID = int(match.group(1))
        ruleContent = match.group(2)
        rules[ruleID] = ruleContent
    
    for line in input:
        messages.append(line.strip())

ruleIDRegex = re.compile(" \d+")
def resolve(ruleID):
    if rules[ruleID].startswith(' "'):
        return rules[ruleID][2]
    resolved = rules[ruleID]
    while True:
        match = ruleIDRegex.search(resolved)
        if match is None:
            if ' |' in resolved:
                return '(' + resolved.replace(' |', '|') + ')'
            else:
                return resolved
        referencedID = int(match.group(0).strip())
        subResolved = resolve(referencedID)
        newResolved = ""
        if (match.start() > 0):
            newResolved = resolved[:match.start()]
        newResolved += subResolved
        if match.end() < len(resolved):
            newResolved += resolved[match.end():]
        resolved = newResolved

resolvedRule = resolve(0)
ruleZero = re.compile('^' + resolvedRule + '$')
matchingMessages = [line for line in messages if ruleZero.match(line) is not None]
print(len(matchingMessages))
