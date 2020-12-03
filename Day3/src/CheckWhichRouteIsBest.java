import java.io.IOException;
import java.util.List;

public class CheckWhichRouteIsBest {

    public static void main(String args[]) throws IOException {
        // Variables for read the file
        List<String> lines = CheckIfTreeOrNot.getTreeMap();

        // variables necessary for the calculations
        long counterTree = CheckIfTreeOrNot.countTreesOnSlope(lines, 1, 1);
        counterTree = counterTree*CheckIfTreeOrNot.countTreesOnSlope(lines, 3, 1);
        counterTree = counterTree*CheckIfTreeOrNot.countTreesOnSlope(lines, 5, 1);
        counterTree = counterTree*CheckIfTreeOrNot.countTreesOnSlope(lines, 7, 1);
        counterTree = counterTree*CheckIfTreeOrNot.countTreesOnSlope(lines, 1, 2);

        System.out.print(String.format("The multiplication of the trees is: %s ", counterTree));

    }
}
