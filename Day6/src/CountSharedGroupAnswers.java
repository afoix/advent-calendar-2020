import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public class CountSharedGroupAnswers {
    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day6/surveyAnswers.txt";

    public static void main(String args[]) throws IOException {
        // Scan and parse the line
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));

        Integer totalSumAnswers = 0;

        while (true) {
            Collection<String> answersGroup = readAnswersGroup(reader);
            if (answersGroup.isEmpty()) {
                break;
            }

            int uniqueAnswersInGroup = calculateSharedAnswersInGroup(answersGroup);
            totalSumAnswers += uniqueAnswersInGroup;
        }

        System.out.println(String.format("The total sum of answers is: %d", totalSumAnswers));

    }

    private static int calculateSharedAnswersInGroup(Collection<String> answersGroup) {
        // HashSet is a set that only can have unique elements, it does not have any concept of order
        HashSet<Character> sharedCharacters = new HashSet<>();

        for (String currentString : answersGroup) {
            HashSet<Character> currentStringCharacters = new HashSet<>();

            for (int characterPosition = 0; characterPosition < currentString.length(); characterPosition++) {
                Character currentCharacter = currentString.charAt(characterPosition);
                currentStringCharacters.add(currentCharacter);
            }
            if (sharedCharacters.isEmpty()) {
                sharedCharacters.addAll(currentStringCharacters);
            } else {
                sharedCharacters.retainAll(currentStringCharacters);
            }

            if (sharedCharacters.isEmpty()) {
                break;
            }

        }
        return sharedCharacters.size();
    }

    private static Collection<String> readAnswersGroup(BufferedReader reader) throws IOException {
        Collection<String> currentAnswersGroup = new ArrayList<>();

        while (true) {
            String line = reader.readLine();
            if (line == null || line.length() == 0) {
                break;
            }
            currentAnswersGroup.add(line);
        }
        return currentAnswersGroup;
    }

}
