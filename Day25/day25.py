with open('Day25/input.txt') as input:
    card_public_key = int(input.readline().strip())
    door_public_key = int(input.readline().strip())

def transform_subject_number(subject_number, loop_size):
    value = 1
    for _ in range(loop_size):
        value *= subject_number
        value = value % 20201227
    return value

card_loop_size = 0
value = 1
while value != card_public_key:
    value *= 7
    value = value % 20201227
    card_loop_size += 1

#card_public_key = transform_subject_number(7, card_loop_size)
#door_public_key = transform_subject_number(7, door_loop_size)

encryption_key = transform_subject_number(door_public_key, card_loop_size)
print(encryption_key)
