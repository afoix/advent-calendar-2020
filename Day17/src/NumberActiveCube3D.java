import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class NumberActiveCube3D {
    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day17/initialState.txt";

    public  static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(PATH_TO_YOUR_FILE));
        PocketDimension3D pocketDimension3D = new PocketDimension3D();
        initDimension(scanner, pocketDimension3D);

        for (int cycle = 0; cycle < 6; cycle++) {
            PocketDimension3D newDimension = new PocketDimension3D();
            Coordinate3D minBound = pocketDimension3D.getMinimumActivePosition();
            Coordinate3D maxBound = pocketDimension3D.getMaximumActivePosition();

            for (int x = minBound.getX()-1; x <= maxBound.getX()+1; x++) {
                for (int y = minBound.getY()-1; y <= maxBound.getY()+1; y++) {
                    for (int z = minBound.getZ()-1; z <= maxBound.getZ()+1 ; z++) {
                        Coordinate3D position = new Coordinate3D(x, y, z);
                        boolean wasActive = pocketDimension3D.isCubeActive(position);
                        int numberOfActiveNeighbors = pocketDimension3D.countActiveNeighbors(position);
                        if (wasActive && (numberOfActiveNeighbors == 2 || numberOfActiveNeighbors == 3)) {
                            newDimension.setCubeActive(position, true);
                        }
                        if (!wasActive && numberOfActiveNeighbors == 3) {
                            newDimension.setCubeActive(position, true);
                        }
                    }
                }
            }

            pocketDimension3D = newDimension;
        }

        System.out.println(String.format("The total number of active cubes is: %d", pocketDimension3D.totalCubesActive()));


    }

    private static void initDimension(Scanner scanner, PocketDimension3D pocketDimension3D) {
        int z = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int x = 0; x < line.length(); x++) {
                char character = line.charAt(x);
                switch (character){
                    case '.':
                        pocketDimension3D.setCubeActive(new Coordinate3D(x, 0, z), false);
                        break;
                    case '#':
                        pocketDimension3D.setCubeActive(new Coordinate3D(x, 0, z), true);
                        break;
                }
            }
            z++;
        }
    }
}
