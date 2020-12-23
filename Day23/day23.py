input = [5,3,8,9,1,4,7,6,2]

highest = max(input)

class Node(object):
    def __init__(self, value):
        self.value = value
        self.next = None

def make_ring(values):
    cups = [Node(v) for v in values]
    for i in range(len(cups)):
        cups[i].next = cups[(i+1)%len(cups)]
    return (cups[0], {node.value: node for node in cups})

def ring_to_list(ring):
    result = [ring.value]
    cursor = ring.next
    while cursor != ring:
        result.append(cursor.value)
        cursor = cursor.next
    return result

def step(current, lookup, max):
    
    removedCups = current.next
    current.next = current.next.next.next.next
    pickedValues = [removedCups.value, removedCups.next.value, removedCups.next.next.value]

    seeking = (current.value - 1)
    if seeking <= 0:
        seeking = max
    while seeking in pickedValues:
        seeking = (seeking - 1) % max
        if seeking <= 0:
            seeking = max

    destination = lookup[seeking]

    removedCups.next.next.next = destination.next
    destination.next = removedCups

    return current.next

(state, lookup) = make_ring(input)
for i in range(100):
    state = step(state, lookup, highest)

while state.value != 1:
    state = state.next
arr = ring_to_list(state)
print(''.join([str(i) for i in arr[1:]]))

total_cups = 1000000
input += [i for i in range(highest + 1, total_cups + 1)]
highest = max(input)

iterations = 10000000
(state, lookup) = make_ring(input)
for i in range(iterations):
    state = step(state, lookup, highest)

while state.value != 1:
    state = state.next
print(state.next.value * state.next.next.value)