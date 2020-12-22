from datetime import datetime

with open('Day22/input.txt') as input:
    input.readline()
    deck1 = [int(input.readline().strip()) for _ in range(25)]
    input.readline()
    input.readline()
    deck2 = [int(input.readline().strip()) for _ in range(25)]

gameSize = len(deck1) + len(deck2)

def score(deck):
    return sum([(gameSize-i)*v for (i, v) in enumerate(deck)])

def play(deck1, deck2):
    while len(deck1) > 0 and len(deck2) > 0:

        card1 = deck1.pop(0)
        card2 = deck2.pop(0)

        if card1 > card2:
            outcome = 1
            deck1 = deck1 + [card1, card2]
        else:
            outcome = 2
            deck2 = deck2 + [card2, card1]
    if outcome == 1:
        return deck1
    else:
        return deck2

print(score(play(list(deck1), list(deck2))))

gameIndex = 0

def playRecursive(deck1, deck2, depth):
    global gameIndex
    gameIndex += 1
    myIndex = gameIndex
    #print ('=== Game {} ==='.format(myIndex))
    seenStates = set()
    while len(deck1) > 0 and len(deck2) > 0:
        state = (tuple(deck1), tuple(deck2))
        if state in seenStates:
            return (deck1, 1)
        seenStates.add(state)
        #print("\n-- Round {} (Game {}) --".format(len(seenStates), myIndex))
        #print("Player 1's deck: {}".format(", ".join(map(str, deck1))))
        #print("Player 2's deck: {}".format(", ".join(map(str, deck2))))

        card1 = deck1.pop(0)
        card2 = deck2.pop(0)

        #print("Player 1 plays: {}".format(card1))
        #print("Player 2 plays: {}".format(card2))

        if len(deck1) >= card1 and len(deck2) >= card2:
            # recurse
            #print ("Playing a sub-game to determine the winner...\n")

            (_, outcome) = playRecursive(list(deck1[:card1]), list(deck2[:card2]), depth + 1)
            #print ("\n...anyway, back to game {}.".format(myIndex))
        else:
            if card1 > card2:
                outcome = 1
            else:
                outcome = 2                

        if outcome == 1:
            deck1.append(card1)
            deck1.append(card2)
            winner = deck1
        else:
            deck2.append(card2)
            deck2.append(card1)
            winner = deck2
        #print ("Player {} wins round {} of game {}!".format(outcome, len(seenStates), myIndex))
    #print ("The winner of game {} is player {}!".format(myIndex, outcome))
    return (winner, outcome)

#print("Starting play at {}".format(datetime.now()))
(winner, outcome) = playRecursive(deck1, deck2, 0)
print(score(winner))
#print("Finished at {}".format(datetime.now()))