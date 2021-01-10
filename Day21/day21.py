import re

allergenToIngredient = {}
all_ingredients = []

lineRegex = re.compile(r'((?:\w+ )+)\s*\(contains (.*)\)')
with open('Day21/listOfIngredients.txt') as input:
    for line in input:
        match = lineRegex.match(line.strip())
        ingredients = match.group(1).strip().split(' ')
        allergens = match.group(2).strip().split(', ')
        all_ingredients += ingredients
        for allergen in allergens:
            if allergen in allergenToIngredient:
                allergenToIngredient[allergen].intersection_update(ingredients)
            else:
                allergenToIngredient[allergen] = set(ingredients)

ingredientToAllergen = {ingredients.pop(): allergen for (allergen, ingredients) in allergenToIngredient.items() if len(ingredients) == 1}
while len(allergenToIngredient) > 0:
    for allergen in ingredientToAllergen.values():
        if allergen in allergenToIngredient:
            del allergenToIngredient[allergen]
    for ingredient in ingredientToAllergen.keys():
        for ingredients in allergenToIngredient.values():
            ingredients.discard(ingredient)
    for allergen in allergenToIngredient.keys():
        if len(allergenToIngredient[allergen]) == 1:
            ingredientToAllergen[allergenToIngredient[allergen].pop()] = allergen

print (len([ingredient for ingredient in all_ingredients if ingredient not in ingredientToAllergen]))
print (','.join([i for (i, a) in 
    sorted(list(ingredientToAllergen.items()), key = lambda ia: ia[1])
    ]))