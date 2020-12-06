with open('Day6/surveyAnswers.txt') as file:

    groups = []
    group = []
    for line in file:
        line = line.strip()
        if len(line) == 0:
            groups.append(group)
            group = []
        else:
            group.append(line)
    groups.append(group)

    total_any = 0
    total_all = 0
    for group in groups:
        any = set([c for c in group[0]])
        all = set([c for c in group[0]])
        for i in range(1, len(group)):
            any = any.union([c for c in group[i]])
            all = all.intersection([c for c in group[i]])
        total_any += len(any)
        total_all += len(all)

    print(total_any)
    print(total_all)
