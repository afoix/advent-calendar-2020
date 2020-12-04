import re

required = set(["byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"])

def outOfRange(str, min, max):
    return int(str) < min or int(str) > max

def isValidPartOne(passport):
    return required.issubset(passport.keys())

def isValidPartTwo(passport):
    if not required.issubset(passport.keys()):
        return False
    if outOfRange(passport["byr"], 1920, 2002):
        return False
    if outOfRange(passport["iyr"], 2010, 2020):
        return False
    if outOfRange(passport["eyr"], 2020, 2030):
        return False
    heightMatch = re.match("(\d+)(cm|in)", passport["hgt"])
    if heightMatch is None:
        return False
    (minHeight, maxHeight) = {
        "cm": (150, 193),
        "in": (59, 76)
    }[heightMatch.group(2)]
    if outOfRange(heightMatch.group(1), minHeight, maxHeight):
        return False
    if re.match("^#[0-9a-f]{6}$", passport["hcl"]) is None:
        return False
    if not passport["ecl"] in ["amb", "blu", "brn", "gry", "grn", "hzl", "oth"]:
        return False
    if re.match("^[0-9]{9}$", passport["pid"]) is None:
        return False
    
    return True

passports = []
fields = {}
regex = re.compile("([a-z]{3}):(\S+)")
with open('Day4/passports.txt') as file:
    for line in file:
        if len(line.strip()) == 0:
            passports.append(fields)
            fields = {}
        else:
            matches = regex.findall(line)
            for (key, value) in matches:
                fields[key] = value
passports.append(fields)

validPartOne = 0
validPartTwo = 0
for passport in passports:
    if isValidPartOne(passport):
        validPartOne += 1
    if isValidPartTwo(passport):
        validPartTwo += 1

print(validPartOne)
print(validPartTwo)
