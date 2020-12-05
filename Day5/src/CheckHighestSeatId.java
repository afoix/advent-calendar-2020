import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CheckHighestSeatId {

    // Path to the file to read
    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day5/boardingPasses.txt";

    // Main function and reading all the line from file
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));
        int biggestSeatId = 0;

        while (true){
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            int seatID = calculateSeatId(line);

            if (seatID > biggestSeatId) {
                biggestSeatId = seatID;
            }
        }

        System.out.println(String.format("Biggest seat found: %d", biggestSeatId));
    }

    public static int calculateSeatId(String line) {
        // What's the sit number for this boarding pass
        // Workout row and column
        int minRow = 0;
        int maxRow = 128;
        int minColumn = 0;
        int maxColumn = 8;

        for (int position = 0; position < line.length(); position++) {
            char character = line.charAt(position);
            switch (character) {
                case 'F':
                    maxRow = (minRow+maxRow)/2;
                    break;

                case 'B':
                    minRow = (minRow+maxRow)/2;
                    break;

                case 'L':
                    maxColumn = (minColumn+maxColumn)/2;
                    break;

                case 'R':
                    minColumn = (minColumn+maxColumn)/2;
                    break;
            }

        }

        // Converted row and column to single num
        return minRow*8+minColumn;
    }
}
