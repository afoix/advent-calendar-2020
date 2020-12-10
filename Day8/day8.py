import re
with open('Day8/input.txt') as file:
    instrRegex = re.compile(r'^(\w{3})\s+(.*)$')
    matches = [instrRegex.match(line) for line in file]
    instructions = [{'code': match.group(1), 'value': int(match.group(2))} for match in matches]

for i, e in enumerate(instructions):
    e['addr'] = i
    e['colors'] = set()

ip = 0
acc = 0
visited = set()
while not ip in visited and ip < len(instructions):
    visited.add(ip)
    i = instructions[ip]
    if i['code'] == 'acc':
        acc += i['value']
        ip += 1
    elif i['code'] == 'nop':
        ip += 1
    elif i['code'] == 'jmp':
        ip += i['value']
print(acc)

terminators = set()
for i in range(len(instructions)):
    ip = i
    while ip < len(instructions) and not i in instructions[ip]['colors']:
        instructions[ip]['colors'].add(i)
        if instructions[ip]['code'] == 'jmp':
            ip += instructions[ip]['value']
        else:
            ip += 1
    if ip >= len(instructions):
        terminators.add(i)

for addr, instruction in enumerate(instructions):
    if not 0 in instruction['colors']:
        continue
    c = instruction['code']
    v = instruction['value']
    if c == 'jmp':
        if len(terminators.intersection(instructions[addr+1]['colors'])) > 0:
            instruction['code'] = 'nop'
            break
    if c == 'nop':
        if len(terminators.intersection(instructions[addr+v]['colors'])) > 0:
            instruction['code'] = 'jmp'
            break

ip = 0
acc = 0
while ip < len(instructions):
    i = instructions[ip]
    if i['code'] == 'acc':
        acc += i['value']
        ip += 1
    elif i['code'] == 'nop':
        ip += 1
    elif i['code'] == 'jmp':
        ip += i['value']

print(acc)