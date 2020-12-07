import re

rules = {}

ruleRegex = re.compile(r"^(.*) bags contain (.*)$")
bagsRegex = re.compile(r"(\d+) (\w+ \w+)")
with open('Day7/input.txt') as file:
    for line in file:
        match = ruleRegex.search(line)
        subject = match.group(1)
        rules[subject] = dict([(bag, int(num)) for (num, bag) in bagsRegex.findall(match.group(2))])

result = set()
open = ['shiny gold']
while len(open) > 0:
    bag = open.pop()
    containers = set([container for (container, contents) in rules.items() if bag in contents])
    containers = containers - result
    open += containers
    result = result | containers

print(len(result))

def countBagsInBag(bag):
    return 1 + sum([count * countBagsInBag(subBag) for (subBag, count) in rules[bag].items()])

print(countBagsInBag('shiny gold') - 1)