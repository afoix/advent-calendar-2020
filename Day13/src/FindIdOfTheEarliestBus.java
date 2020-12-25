import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindIdOfTheEarliestBus {

    private static final String PATH_TO_YOUR_FILE = "/Users/annafoix/dango-git/advent-calendar-2020/Day13/bustimes.txt";

    public static void main(String args[]) throws IOException {
        // Scan the input for the instructions
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));

        // Variables need it
        Long earliestPossibleTime = Long.decode(reader.readLine().strip());
        String[] allBuses = reader.readLine().strip().split(",");

        // Only the buses that are numbers
        List<Integer> busNumbers = Arrays.stream(allBuses)
                .filter(str -> !str.equals("x"))
                .map(Integer::decode)
                // Collect all the results in a new array
                .collect(Collectors.toList());

        // Calculate the minutes to the next bus.
        List<Long> minutesToNextBus = busNumbers.stream()
                .map(busNumber -> busNumber-earliestPossibleTime%busNumber)
                .collect(Collectors.toList());

        long shortestTimeToNextBus = minutesToNextBus.stream().min(Long::compareTo).get();

        int busIndex = minutesToNextBus.indexOf(shortestTimeToNextBus);

        Integer nextBus = busNumbers.get(busIndex);

       System.out.println(String.format("The bus ID multiplied by number of minutes is: %d", shortestTimeToNextBus*nextBus));

    }
}
