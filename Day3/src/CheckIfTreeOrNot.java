import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckIfTreeOrNot {

    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day3/treeMap.txt";

    public static void main(String args[]) throws IOException {
        // Variables for read the file
        List<String> lines = getTreeMap();

        // variables necessary for the calculations
        Integer increaseX = 3;
        Integer increaseY = 1;
        Integer counterTree = countTreesOnSlope(lines, increaseX, increaseY);

        System.out.print(String.format("The counter tree total is: %s ", counterTree));

    }

    public static Integer countTreesOnSlope(List<String> lines, Integer increaseX, Integer increaseY) {
        Integer positionX = 0;
        Integer positionY = 0;
        Integer counterTree = 0;

        final Integer length = lines.get(0).length();

        // Do the operations for move the position (X is the module of the current position and the total length of the String)
        while (positionY < lines.size() ) {
            if (lines.get(positionY).charAt(positionX) == '#') {
                counterTree++;
            }

            // Need to update X and Y
            positionX = (positionX+increaseX)%length;
            positionY = positionY+increaseY;

        }
        return counterTree;
    }

    public static List<String> getTreeMap() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(PATH_TO_YOUR_FILE));
        List<String> lines = new ArrayList<>();

        while (scanner.hasNext()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }
}
