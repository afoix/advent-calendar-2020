import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseListAndCheckValidaPostion {

    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day2/policiesAndPasswords.txt";

    public static void main(String args[]) throws IOException {
        // Scan and parse the line
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));

        String line = reader.readLine();
        Integer counterResult = 0;

        while (line != null) {
            // Take the line and check if password is OK
            Pattern pattern = Pattern.compile("(\\d*)-(\\d*) (\\w): (\\w*)");
            Matcher matcher = pattern.matcher(line);
            matcher.find();

            Integer min = Integer.decode(matcher.group(1));
            Integer max = Integer.decode(matcher.group(2));
            char letter = matcher.group(3).charAt(0);
            String password = matcher.group(4);

            boolean letterAtMin = letter == password.charAt(min - 1);
            boolean letterAtMax = letter == password.charAt(max - 1);

            if (letterAtMin ^ letterAtMax) {
                // If the password is OK and increment the counter
                counterResult++;
            }

            // Continue loop though the others
            line = reader.readLine();
        }

        System.out.println(counterResult);


    }

}
