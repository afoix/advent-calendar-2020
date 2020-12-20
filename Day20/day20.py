import re
import numpy
import math
from enum import IntEnum

class Transform(object):
    def __init__(self, rot, flipX, flipY):
        self.rot = rot
        self.flipX = flipX
        self.flipY = flipY

    def apply(self, tile):
        if self.rot > 0:
            tile = numpy.rot90(tile, k=self.rot)
        if self.flipX:
            tile = numpy.fliplr(tile)
        if self.flipY:
            tile = numpy.flipud(tile)
        return tile

class Direction(IntEnum):
    PLUS_ROW = 0
    PLUS_COL = 1
    MINUS_ROW = 2
    MINUS_COL = 3

class Tile(object):
    def __init__(self, id, content):
        self.id = id

        self.content = numpy.matrix([[ch for ch in line] for line in content])

    def couldBeAdjacent(self, other, myTransform, otherTransform):
        selfTransformedContent = myTransform.apply(self.content)
        otherTransformedContent = otherTransform.apply(other.content)

        result = []

        if numpy.all(selfTransformedContent[0] == otherTransformedContent[9]):
            result.append(Direction.MINUS_ROW)
        
        if numpy.all(selfTransformedContent[9] == otherTransformedContent[0]):
            result.append(Direction.PLUS_ROW)

        if numpy.all(selfTransformedContent.T[0] == otherTransformedContent.T[9]):
            result.append(Direction.MINUS_COL)

        if numpy.all(selfTransformedContent.T[9] == otherTransformedContent.T[0]):
            result.append(Direction.PLUS_COL)

        return result

    def getTrimmedTransformed(self, transform):
        transformedContent = transform.apply(self.content)
        return transformedContent[1:9,1:9]


tiles = []

with open("Day20/input.txt") as input:
    while True:
        line = input.readline().strip()
        if len(line) == 0: break
        match = re.fullmatch(r'Tile (\d+):', line)
        tileID = int(match.group(1))
        tile = []
        for i in range(10):
            tile.append(input.readline().strip())
        tiles.append(Tile(tileID, tile))
        input.readline()

allTransforms = []
for flipX in [False, True]:
    for flipY in [False, True]:
        for rot in range(4):
            allTransforms.append(Transform(rot, flipX, flipY))

offsets = [(0, 1), (1, 0), (0, -1), (-1, 0)]

def placeRecursive(placed, remaining):
    if len(remaining) == 0: return placed
    for (latestTile, x, y, latestTransform) in placed[::-1]:
        for tile in remaining:
            for transform in allTransforms:
                sides = latestTile.couldBeAdjacent(tile, latestTransform, transform)
                if len(sides) == 0: continue
                for side in sides:
                    (dx, dy) = offsets[int(side)]
                    if not any([x2 == x + dx and y2 == y + dy for (_, x2, y2, _) in placed]):
                        # This tile could be placed here
                        newPlaced = list(placed)
                        newPlaced.append((tile, x + dx, y + dy, transform))
                        newRemaining = list(remaining)
                        newRemaining.remove(tile)
                        print("Placed {}, remaining {}".format(len(newPlaced), len(newRemaining)))
                        finalPlaced = placeRecursive(newPlaced, newRemaining)
                        if len(finalPlaced) > 0:
                            return finalPlaced
                        # If finalPlaced was empty it means we couldn't solve after placing this tile in this position,
                        # so continue looping
    return []

firstTile = tiles[0]
tiles.remove(firstTile)

placed = [(firstTile, 0, 0, Transform(0, False, False))]
solution = placeRecursive(placed, tiles)

xMin = 0
xMax = 0
yMin = 0
yMax = 0
for (_, x, y, _) in solution:
    if x < xMin: xMin = x
    if y < yMin: yMin = y
    if x > xMax: xMax = x
    if y > yMax: yMax = y

solutionByPosition = {(x, y): tile.id for (tile, x, y, _) in solution}
cornerTiles = []
for x in [xMin, xMax]:
    for y in [yMin, yMax]:
        cornerTiles.append(solutionByPosition[(x, y)])
print (math.prod(cornerTiles))

# Part 2
image = numpy.full((8*(xMax - xMin + 1), 8*(yMax-yMin+1)), None)
for (tile, x, y, transform) in solution:
    tileContent = tile.getTrimmedTransformed(transform)
    x -= xMin
    y -= yMin
    image[8*y:8*(y+1), 8*x:8*(x+1)] = tileContent

boolean_image = numpy.vectorize(lambda x: x == '#')(image)

seamonster_raw = [
    "                  # ",
    "#    ##    ##    ###",
    " #  #  #  #  #  #   "
]

seamonster = [[ch != '#' for ch in line] for line in seamonster_raw]

(height, width) = numpy.shape(image)
(sm_height, sm_width) = numpy.shape(seamonster)
for transform in allTransforms:
    transformed_image = transform.apply(boolean_image)
    seamonsters = 0
    for y in range(0, height - sm_height + 1):
        for x in range(0, width - sm_width + 1):
            region = transformed_image[y:y+sm_height, x:x+sm_width]
            overlapped = region | seamonster
            is_seamonster = numpy.all(overlapped)
            if is_seamonster:
                seamonsters += 1
                region = region & seamonster
                transformed_image[y:y+sm_height, x:x+sm_width] = region
    if seamonsters > 0:
        break

print (sum(sum(transformed_image)))
