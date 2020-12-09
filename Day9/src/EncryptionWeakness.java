
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class EncryptionWeakness {

    // Path to file
    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day9/numbers.txt";
    private static final Integer PREAMBLE = 25;
    // Main function and reading all the line from file
    public static void main(String args[]) throws FileNotFoundException {

        // Scan the input
        Scanner scanner = new Scanner(new FileReader(PATH_TO_YOUR_FILE));

        // Make a list for storing the last PREAMBLE numbers we have seen
        List<Long> history = new ArrayList<>();

        // Read the first PREAMBLE numbers
        for (int i = 0; i < PREAMBLE; i++) {
            // Read number from the scanner and added to the list
            history.add(scanner.nextLong());
        }
        
        Long nextNumber = 0L;
        
        // While there are still more numbers, loop:
        while(scanner.hasNextLong()){
            // Read one number
            nextNumber = scanner.nextLong();

            // Check if there are two numbers in the list that add up to the new number
            if (!hasSummingTwoNumbers(history, nextNumber)) {
                break;
            }

            // Add the number to the end of the list
            history.add(nextNumber);

            // Remove the oldest number from the front of the list
            history.remove(0);

        }

        final Long targetNumber = nextNumber;

        scanner = new Scanner(new FileReader(PATH_TO_YOUR_FILE));
        history.clear();
        while(scanner.hasNextLong())
            history.add(scanner.nextLong());

        // Remove all the numbers at the end that are bigger than the target number
        while(history.get(history.size() - 1) > targetNumber)
            history.remove(history.size() - 1);

        Long answer = null;
        for (int setSize = 2; setSize <= history.size(); setSize++) {
            answer = searchForSummingSet(history, targetNumber, setSize);
            if (answer != null)
                break;
        }

        System.out.println(String.format("The encryption weakness is: %d", answer));
    }

    private static Long searchForSummingSet(List<Long> numbers, Long targetNumber, int setSize) {
        for (int startPosition = 0; startPosition < numbers.size() - setSize + 1; startPosition++) {
            Collection<Long> subset = new ArrayList<>();

            Long total = 0L;
            for (int i = 0; i < setSize; i++) {

                Long nextNumber = numbers.get(startPosition + i);
                total += nextNumber;
                subset.add(nextNumber);
                if (total > targetNumber)
                    break;
            }

            if (total.equals(targetNumber)) {
                Long minNumber = subset.stream().min(Long::compare).get();
                Long maxNumber = subset.stream().max(Long::compare).get();
                return minNumber + maxNumber;
            }
        }

        return null;
    }

    private static boolean hasSummingTwoNumbers(List<Long> history, Long nextNumber) {
        for (int firstNumberIndex = 0; firstNumberIndex < PREAMBLE; firstNumberIndex++) {
            for (int secondNumberIndex = firstNumberIndex+1; secondNumberIndex < PREAMBLE; secondNumberIndex++) {
                Long firstNumber = history.get(firstNumberIndex);
                Long secondNumber = history.get(secondNumberIndex);
                if (firstNumber+secondNumber == nextNumber) {
                    return true;
                }
            }

        }
        return false;
    }

}
