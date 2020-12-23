import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class seatsEndedUpOccupied {

    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day11/seatsOccupiedPattern.txt";

    public static void main(String args[]) throws IOException {
        // Variables for read the file
        List<String> lines = getSeatMap();

        // variables necessary for the calculations
        // Integer increaseX = 3;
        // Integer increaseY = 1;
        // Integer counterSeats = countTreesOnSlope(lines, increaseX, increaseY);

        Integer counterSeats = 0;

        System.out.print(String.format("The sits that ended up occupied are: %s ", counterSeats));

    }

    public static List<String> getSeatMap() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(PATH_TO_YOUR_FILE));
        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }
}
