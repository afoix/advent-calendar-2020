import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class NumberActiveCube4D {
    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day17/initialState.txt";

    public  static void main(String args[]) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(PATH_TO_YOUR_FILE));
        PocketDimension4D pocketDimension4D = new PocketDimension4D();
        initDimension(scanner, pocketDimension4D);

        for (int cycle = 0; cycle < 6; cycle++) {
            PocketDimension4D newDimension = new PocketDimension4D();
            Coordinate4D minBound = pocketDimension4D.getMinimumActivePosition();
            Coordinate4D maxBound = pocketDimension4D.getMaximumActivePosition();

            for (int x = minBound.getX()-1; x <= maxBound.getX()+1; x++) {
                for (int y = minBound.getY()-1; y <= maxBound.getY()+1; y++) {
                    for (int z = minBound.getZ()-1; z <= maxBound.getZ()+1 ; z++) {
                        for (int w = minBound.getW()-1; w <= maxBound.getW()+1; w++) {
                            Coordinate4D position = new Coordinate4D(x, y, z, w);
                            boolean wasActive = pocketDimension4D.isCubeActive(position);
                            int numberOfActiveNeighbors = pocketDimension4D.countActiveNeighbors(position);
                            if (wasActive && (numberOfActiveNeighbors == 2 || numberOfActiveNeighbors == 3)) {
                                newDimension.setCubeActive(position, true);
                            }
                            if (!wasActive && numberOfActiveNeighbors == 3) {
                                newDimension.setCubeActive(position, true);
                            }
                        }
                    }
                }
            }

            pocketDimension4D = newDimension;
        }

        System.out.println(String.format("The total number of active cubes is: %d", pocketDimension4D.totalCubesActive()));


    }

    private static void initDimension(Scanner scanner, PocketDimension4D pocketDimension4D) {
        int z = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int x = 0; x < line.length(); x++) {
                char character = line.charAt(x);
                switch (character){
                    case '.':
                        pocketDimension4D.setCubeActive(new Coordinate4D(x, 0, z, 0), false);
                        break;
                    case '#':
                        pocketDimension4D.setCubeActive(new Coordinate4D(x, 0, z, 0), true);
                        break;
                }
            }
            z++;
        }
    }
}
