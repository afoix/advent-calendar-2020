import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindMySit {
    // Path to the file to read
    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day5/boardingPasses.txt";

    // Main function and reading all the line from file
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));
        List<Integer> seatIds = new ArrayList<>();

        while (true){
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            int seatID = CheckHighestSeatId.calculateSeatId(line);

            seatIds.add(seatID);
        }

        Collections.sort(seatIds);

        for (int sitIndex = 0; sitIndex < seatIds.size()-1; sitIndex++) {
            int thisSeat = seatIds.get(sitIndex);
            int nextSeat = seatIds.get(sitIndex+1);

            if (nextSeat != thisSeat+1) {
                System.out.println(String.format("Our seat is: %d", thisSeat+1));
            }
        }


    }
}
