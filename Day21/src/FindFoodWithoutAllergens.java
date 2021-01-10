import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFoodWithoutAllergens {

    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day21/listOfIngredients.txt";

    public static void main(String args[]) throws FileNotFoundException {

        // Scan the input
        Scanner scanner = new Scanner(new FileReader(PATH_TO_YOUR_FILE));

        Pattern pattern = Pattern.compile("([\\w ]+)(?:\\(contains (.*)\\))?");

        HashMap<String, Set<String>> allergenPossibleIngredients = new HashMap<>();
        HashMap<String, Integer> ingredientsTimesAppear = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            matcher.find();

            String ingredients = matcher.group(1);
            String allergens = matcher.group(2);

            String[] ingredientsArray = ingredients.split(" ");
            String[] allergensArray = allergens.split(", ");

            for (String ingredient : ingredientsArray) {
                ingredientsTimesAppear.putIfAbsent(ingredient, 0);
                ingredientsTimesAppear.put(ingredient, ingredientsTimesAppear.get(ingredient) + 1);
            }

            for (String allergen : allergensArray) {
                Set<String> ingredientsSet = new HashSet<String>(Arrays.asList(ingredientsArray));
                if (allergenPossibleIngredients.containsKey(allergen)) {
                    allergenPossibleIngredients.get(allergen).retainAll(ingredientsSet);
                } else {
                    allergenPossibleIngredients.put(allergen, ingredientsSet);
                }
            }

        }

        Map<String, String> ingredientAllergens = new HashMap<>();

        while (allergenPossibleIngredients.size() > ingredientAllergens.size()) {
            for (Map.Entry<String, Set<String>> entry : allergenPossibleIngredients.entrySet()) {
                if (entry.getValue().size() == 1) {
                    ingredientAllergens.put(entry.getValue().iterator().next(), entry.getKey());
                } else {
                    entry.getValue().removeAll(ingredientAllergens.keySet());
                }
            }
        }

        int total = 0;

        for (Map.Entry<String, Integer> entry : ingredientsTimesAppear.entrySet()) {
            if (ingredientAllergens.containsKey(entry.getKey())) {
                continue;
            }

            total += entry.getValue();
        }

        System.out.println(String.format("The total of ingredients is: %d", total));

    }

}
