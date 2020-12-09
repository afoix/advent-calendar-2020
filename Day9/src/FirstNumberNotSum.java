
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FirstNumberNotSum {

    // Path to file
    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day9/numbers.txt";
    private static final Integer PREAMBLE = 25;
    // Main function and reading all the line from file
    public static void main(String args[]) throws FileNotFoundException {

        // Scan the input
        Scanner scanner = new Scanner(new FileReader(PATH_TO_YOUR_FILE));

        // Make a list for storing the last PREAMBLE numbers we have seen
        List<Integer> history = new ArrayList<>();

        // Read the first PREAMBLE numbers
        for (int i = 0; i < PREAMBLE; i++) {
            // Read number from the scanner and added to the list
            history.add(scanner.nextInt());
        }
        
        Integer nextNumber = 0;
        
        // While there are still more numbers, loop:
        while(scanner.hasNextInt()){
            // Read one number
            nextNumber = scanner.nextInt();

            // Check if there are two numbers in the list that add up to the new number
            if (!hasSummingTwoNumbers(history, nextNumber)) {
                break;
            }

            // Add the number to the end of the list
            history.add(nextNumber);

            // Remove the oldest number from the front of the list
            history.remove(0);

        }

        System.out.println(String.format("The first number that is not a sum is: %d", nextNumber));



    }

    private static boolean hasSummingTwoNumbers(List<Integer> history, Integer nextNumber) {
        for (int firstNumberIndex = 0; firstNumberIndex < PREAMBLE; firstNumberIndex++) {
            for (int secondNumberIndex = firstNumberIndex+1; secondNumberIndex < PREAMBLE; secondNumberIndex++) {
                Integer firstNumber = history.get(firstNumberIndex);
                Integer secondNumber = history.get(secondNumberIndex);
                if (firstNumber+secondNumber == nextNumber) {
                    return true;
                }
            }

        }
        return false;
    }

}
