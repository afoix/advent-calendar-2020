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

del rules[8]
del rules[11]

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

# Rule 0 is 8 11 which are our two modified rules
# Resolved: <42>+ and then balancing <42> <31>
# Which overall means: as long as there's at least 1 more 42 than 31, it's OK

resolved42 = resolve(42)
resolved31 = resolve(31)
endsWith31 = re.compile(resolved31 + '(?=c*$)')
total = 0
for message in messages:
    replacements = 0
    prev = message
    while True:
        folded = endsWith31.sub('c', prev)
        if folded == prev:
            break
        prev = folded
        replacements += 1
    if replacements == 0: continue
    whole = re.compile('^(' + resolved42 + '){' + str(replacements + 1) + ',}c+$')
    if whole.match(prev):
        total += 1

print (total)

