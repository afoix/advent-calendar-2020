import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllNumberSavedInMemory {
    private static final String PATH_TO_YOUR_FILE = "/Users/annafoix/dango-git/advent-calendar-2020/Day14/bitmask.txt";

    public static void main(String args[]) throws FileNotFoundException {

        Scanner scanner =  new Scanner(new File(PATH_TO_YOUR_FILE));

        Pattern pattern = Pattern.compile("^mem\\[(\\d+)\\] = (\\d+)");

        HashMap<Integer, Long> memory = new HashMap<>();

        long maskAnd = 0;
        long maskOr = 0;


        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                // This is the line for setting the memory
                Integer address = Integer.decode(matcher.group(1));
                Long value = Long.decode(matcher.group(2));
                value = (value & maskAnd) | maskOr;
                memory.put(address, value);

            } else {
                // This is the line for setting the mask
                String mask = line.substring("mask =".length()).trim();

                maskAnd = 0;
                maskOr = 0;

                for (int position = 0; position < mask.length(); position++) {
                    switch (mask.charAt(position)) {
                        case '1':
                            maskOr = maskOr | (1L << (mask.length()-position-1));
                            break;

                        case '0':
                            // nothing to do
                            break;

                        case 'X':
                            maskAnd = maskAnd | (1L << (mask.length()-position-1));
                            break;
                    }
                }
            }

        }

        System.out.println(String.format("The total of all numbers in memory is: %d", memory
                .values()
                .stream()
                .reduce(0L, (a,b)->a+b)));
    }
}
