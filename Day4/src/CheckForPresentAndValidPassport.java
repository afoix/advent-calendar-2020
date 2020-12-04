import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckForPresentAndValidPassport {
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



    private static boolean isOutOfRange(String value, int min, int max) {
        int intValue = Integer.parseInt(value);

        if (intValue < min || intValue > max) {
            return true;
        }
        return false;
    }
    private static boolean validatePassport(HashMap<String, String> currentPassport) {
        String[] requiredField =  new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};

        // Check if passport is valid
        for(String field : requiredField) {
            if(!currentPassport.containsKey(field)) {
                return false;
            }
        }
        //    byr (Birth Year) - four digits; at least 1920 and at most 2002.
        if(isOutOfRange(currentPassport.get("byr"), 1920, 2002)) {
            return false;
        }

        //    iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        if(isOutOfRange(currentPassport.get("iyr"), 2010, 2020)) {
            return false;
        }
        //    eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
        if(isOutOfRange(currentPassport.get("eyr"), 2020, 2030)) {
            return false;
        }
        //    hgt (Height) - a number followed by either cm or in:
        Pattern patternHeight = Pattern.compile("^(\\d+)(cm|in)$");
        Matcher matcherHeight = patternHeight.matcher(currentPassport.get("hgt"));
        if(!matcherHeight.find()) {
            return false;
        }

        //    If cm, the number must be at least 150 and at most 193.
        if(matcherHeight.group(2).equals("cm")) {
            if (isOutOfRange(matcherHeight.group(1),150, 193)) {
                return false;
            }
        }

        //  If in, the number must be at least 59 and at most 76.
        if(matcherHeight.group(2).equals("in")) {
            if (isOutOfRange(matcherHeight.group(1),59, 76)) {
                return false;
            }
        }

        // hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
        Pattern patternHairColor = Pattern.compile("^#[0-9a-f]{6}$");
        Matcher matcherHairColor = patternHairColor.matcher(currentPassport.get("hcl"));

        if(!matcherHairColor.find()){
            return false;
        }

        // ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        if (!isValidEyeColor(currentPassport.get("ecl"))) {
            return false;
        }

        // pid (Passport ID) - a nine-digit number, including leading zeroes
        Pattern patternIdPassport = Pattern.compile("^\\d{9}$");
        Matcher matcher = patternIdPassport.matcher(currentPassport.get("pid"));
       if(!matcher.find()) {
           return false;
       }
        return true;
    }

    private static boolean isValidEyeColor(String eyeColorFromPassport) {
        String[] colorEyes =  new String[]{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
        for (String color : colorEyes){
            if(color.equals(eyeColorFromPassport)){
                return true;
            }
        }
        return false;
    }
}
