import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManhattanDistanceBetweenStartAndEndWithWayPoint {
    private static final String PATH_TO_YOUR_FILE = "/Users/annafoix/dango-git/advent-calendar-2020/Day12/shipInstructions.txt";

    public static void main(String args[]) throws IOException {

        // Scan the input for the instructions
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));

        // Variables needed
        Vector2D position = new Vector2D(0, 0);
        Vector2D waypoint_position = new Vector2D(10, -1);
        List<Vector2D> offsets = new ArrayList<>();
        offsets.add(new Vector2D(1, 0));
        offsets.add(new Vector2D(0, 1));
        offsets.add(new Vector2D(-1, 0));
        offsets.add(new Vector2D(0, -1));

        // Read all the lines
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            // Pattern for subtract the parts
            Pattern pattern = Pattern.compile("^(\\w)(\\d+)$");
            Matcher matcher = pattern.matcher(line);
            matcher.find();

            String command = matcher.group(1);
            Integer distance = Integer.decode(matcher.group(2));

            switch (command) {
                case "E":
                    waypoint_position = waypoint_position.add(offsets.get(0).multiply(distance));
                    break;
                case "S":
                    waypoint_position = waypoint_position.add(offsets.get(1).multiply(distance));
                    break;
                case "W":
                    waypoint_position = waypoint_position.add(offsets.get(2).multiply(distance));
                    break;
                case "N":
                    waypoint_position = waypoint_position.add(offsets.get(3).multiply(distance));
                    break;
                case "L":
                    for (int angle = 0; angle < distance; angle += 90) {
                        waypoint_position = waypoint_position.rotate90DegreesAnticlockwiseAroundOrigin();
                    }
                    break;
                case "R":
                    for (int angle = 0; angle < distance; angle += 90) {
                        waypoint_position = waypoint_position.rotate90DegreesClockwiseAroundOrigin();
                    }
                    break;
                case "F":
                    position = position.add(waypoint_position.multiply(distance));
                    break;
            }
        }

        Integer manhattanDistance = Math.abs(position.getX()) + Math.abs(position.getY());
        System.out.println(String.format("Manhattan distance is: %d", manhattanDistance));

    }
}
