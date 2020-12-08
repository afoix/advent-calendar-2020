import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalculateAccumulatorValueAfterProgramTermiantes {

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

        Integer nextColor = 0;
        Set<Integer> colorsThatEndTheProgram = new HashSet<Integer>();
        for (int i = 0; i < instructions.size(); i++) {
            if(colorInstructions(instructions, i, nextColor)) {
                colorsThatEndTheProgram.add(nextColor);
            }
            nextColor++;
        }

        // Find the instruction of color 0 that if we change it it goes to an instruction which is a color in colorsThatEndTheProgram
        Instruction instructionWeWantToChange = null;
        for (Instruction instruction :
                instructions) {
           if (!instruction.hasColor(0))
               continue;

           Set<Integer> colorsOfInstructionWeWouldGetIfWeChangeIt = null;
           if (instruction.getType().equals("jmp")) {
               colorsOfInstructionWeWouldGetIfWeChangeIt = instructions.get(instruction.getPosition() + 1).getColors();
           }

           if (instruction.getType().equals("nop")) {
               colorsOfInstructionWeWouldGetIfWeChangeIt = instructions.get(instruction.getPosition() + instruction.getValue()).getColors();
           }

           if (colorsOfInstructionWeWouldGetIfWeChangeIt == null)
               continue;

            if (setsIntersect(colorsThatEndTheProgram, colorsOfInstructionWeWouldGetIfWeChangeIt)) {
                instructionWeWantToChange = instruction;
                break;
            }
        }

        // Patch the broken instruction
        instructions.set(277, new Instruction(277, "nop", 0));


        // Execute the commands and do the actions
        int accumulator = 0;
        int position = 0;

        while(position < instructions.size()) {
            Instruction instruction = instructions.get(position);

            switch (instruction.getType()){
                case "nop":
                    break;
                case "jmp":
                    break;
                case "acc":
                    accumulator+=instruction.getValue();
                    break;
            }
            position = instruction.positionAfter(position);

        }

        // This now is only printing all the
        System.out.println(String.format("The total of the accumulator is: %d", accumulator));
    }

    private static boolean setsIntersect(Set<Integer> setA, Set<Integer> setB) {
        for (Integer i : setA) {
            if (setB.contains(i))
                return true;
        }

        return false;
    }

    static boolean colorInstructions(List<Instruction> instructions, Integer position, Integer color) {
        while (position < instructions.size() && !instructions.get(position).hasColor(color)) {
            Instruction instruction = instructions.get(position);
            instruction.addColor(color);
            position = instruction.positionAfter(position);
        }
        return (position >= instructions.size());
    }

}
