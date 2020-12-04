import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckForValidPassports {
    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day4/passports.txt";

    public static void main(String args[]) throws IOException {
        // Scan and parse the line
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));

        Integer counterValidPassports = 0;
        while(true){
            HashMap<String, String> currentPassport = readPassport(reader);

            if (currentPassport.isEmpty()) {
                break;
            }

            if (validatePassport(currentPassport)) {
                counterValidPassports++;
            }
        }

        System.out.println(String.format("The total of valid passports is: %d", counterValidPassports));
    }

    private static HashMap<String, String> readPassport(BufferedReader reader) throws IOException {
        HashMap<String, String> currentPassport =  new HashMap<String, String>();

        // Parse what I need to read to collect the fields
        Pattern patter = Pattern.compile("(\\w*):(\\S*)");
        while(true){
            String line = reader.readLine();

            if (line == null || line.length()==0){
                break;
            }

            Matcher matcher = patter.matcher(line);

            while(matcher.find()){
                currentPassport.put(matcher.group(1), matcher.group(2));
            }

        }
        return currentPassport;
    }

    private static boolean validatePassport(HashMap<String, String> currentPassport) {
        String[] requiredField =  new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

        // Check if passport is valid
        for(String field : requiredField) {
            if(!currentPassport.containsKey(field)) {
                return false;
            }
        }

        return true;
    }
}
