import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MakeWolframAlphaInput {

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
                .map(busNumber -> busNumber - earliestPossibleTime % busNumber)
                .collect(Collectors.toList());

        List<String> rCoefficients = busNumbers.stream().map(busNumber -> busNumber - Arrays
                .asList(allBuses)
                .indexOf(busNumber.toString()))
                .map(rCoefficient -> rCoefficient.toString())
                .collect(Collectors.toList());

        String rExpression = String.join(",", rCoefficients);
        String mExpression = String.join(",", busNumbers.stream().map(busNumber -> busNumber.toString()).collect(Collectors.toList()));

        System.out.println(String.format("ChineseRemainder[{%s},{%s}]", rExpression, mExpression));

    }
}
