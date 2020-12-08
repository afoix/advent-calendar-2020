import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CalculateAccumulatorValue {

    // Path to file
    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day8/instructions.txt";

    // Main function and reading all the line from file
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));
        List<Instruction> instructions = new ArrayList<Instruction>();

        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            // Parsing to figured out the instructions and the numbers
            Pattern pattern = Pattern.compile("^(\\w{3}) (.*)$");
            Matcher matcher = pattern.matcher(line);
            matcher.find();

            // Find out the rule and the value
            String commandRule = matcher.group(1);
            String numberForRule = matcher.group(2);
            int convertedNumberForRule = Integer.parseInt(numberForRule);

            // Store all the lines
            instructions.add(new Instruction(instructions.size(), commandRule, convertedNumberForRule));

        }

        // Execute the commands and do the actions
        int accumulator = 0;
        int position = 0;
        Set<Integer> alreadyVisitedPositions = new HashSet<>();

        while(!alreadyVisitedPositions.contains(position)) {
            alreadyVisitedPositions.add(position);
            Instruction instruction = instructions.get(position);

            switch (instruction.getType()){
                case "nop":
                    position++;
                    break;
                case "jmp":
                    position+=instruction.getValue();
                    break;
                case "acc":
                    accumulator+=instruction.getValue();
                    position++;
                    break;
            }
        }

        // This now is only printing all the
        System.out.println(String.format("The total of the accumulator is: %d", accumulator));
    }

}
