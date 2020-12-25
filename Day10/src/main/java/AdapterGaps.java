import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdapterGaps {

    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day10/adapters.txt";

    // Main function and reading all the line from file
    public static void main(String args[]) throws FileNotFoundException {

        // Scan the input
        Scanner scanner = new Scanner(new FileReader(PATH_TO_YOUR_FILE));

        List<Integer> adapters = new ArrayList<>();

        while (scanner.hasNextInt()) {
            adapters.add(scanner.nextInt());
        }

        adapters.sort(Integer::compareTo);
        int gapSize1=0;
        int gapSize3=0;

        for (int adapterIndex = 0; adapterIndex < adapters.size(); adapterIndex++) {
            int jolteagesDifference=0;
            if (adapterIndex > 0) {
                jolteagesDifference = adapters.get(adapterIndex)-adapters.get(adapterIndex-1);
            } else {
                jolteagesDifference = adapters.get(adapterIndex);
            }

            switch (jolteagesDifference){
                case 1:
                    gapSize1++;
                    break;
                case 3:
                    gapSize3++;
                    break;
            }
        }

        // For the build in adapter in the device
        gapSize3++;

        Integer totalGapSize = gapSize1*gapSize3;

        System.out.println(String.format("The total gap size is: %d", totalGapSize));

    }
}
