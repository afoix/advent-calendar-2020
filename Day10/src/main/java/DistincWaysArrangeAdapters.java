import org.ejml.simple.SimpleMatrix;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DistincWaysArrangeAdapters {

    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day10/adapters.txt";

    // Main function and reading all the line from file
    public static void main(String args[]) throws FileNotFoundException {

        // Scan the input
        Scanner scanner = new Scanner(new FileReader(PATH_TO_YOUR_FILE));

        List<Integer> adapters = new ArrayList<>();
        adapters.add(0);

        while (scanner.hasNextInt()) {
            adapters.add(scanner.nextInt());
        }
         adapters.sort(Integer::compareTo);
        adapters.add(adapters.get(adapters.size() - 1) + 3);

        // Make an adjacency matrix
        SimpleMatrix adjacencyMatrix = new SimpleMatrix(adapters.size(), adapters.size());
        for (int fromAdapterIndex = 0; fromAdapterIndex < adapters.size() - 1; fromAdapterIndex++) {
            Integer fromAdapter = adapters.get(fromAdapterIndex);
            for (int toAdapterIndex = fromAdapterIndex + 1; toAdapterIndex < adapters.size(); ++toAdapterIndex) {
                Integer toAdapter = adapters.get(toAdapterIndex);
                if (toAdapter <= fromAdapter + 3) {
                    adjacencyMatrix.set(fromAdapterIndex, toAdapterIndex, 1.0);
                }
            }
        }

        Long totalPaths = 0L;
        SimpleMatrix pathsMatrix = new SimpleMatrix(adjacencyMatrix);
        for (int pathLength = 0; pathLength < adapters.size(); ++pathLength) {
            // Get all paths of length 'pathLength' between index 0 (the power socket) and index (adapters.size() - 1) which is the device
            Double pathsOfLength = pathsMatrix.get(0, adapters.size() - 1);
            // Add it to the total
            totalPaths += pathsOfLength.longValue();
            // Multiply pathsMatrix by adjancecyMatrix to get the next numbers
            pathsMatrix = pathsMatrix.mult(adjacencyMatrix);
        }

        System.out.println(String.format("The total number of paths is: %d", totalPaths));
    }

}
