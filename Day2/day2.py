import re

valid = 0
new_valid = 0
with open("policiesAndPasswords.txt") as f:
    regex = re.compile("^(\d+)-(\d+) (.): (.+)$")
    for line in f:
        match = regex.search(line)
        min = int(match.group(1))
        max = int(match.group(2))
        ch = match.group(3)
        pwd = match.group(4)

        count = 0
        for c in pwd:
            if c == ch:
                count += 1

        if (count >= min and count <= max):
            valid += 1

        if ((pwd[min-1] == ch) ^ (pwd[max-1] == ch)):
            new_valid += 1

print(valid)
print(new_valid)