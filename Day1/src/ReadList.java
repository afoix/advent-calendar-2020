import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ReadList {

    public static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day1/numbersList.txt";

    public static List<Integer> readList() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(PATH_TO_YOUR_FILE));
        List<Integer> numbers = new ArrayList<Integer>();
        while (scanner.hasNextInt()) {
            numbers.add(scanner.nextInt());
        }
        Collections.sort(numbers);

        return numbers;
    }
}