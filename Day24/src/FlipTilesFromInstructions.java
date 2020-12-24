import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;


public class FlipTilesFromInstructions {
    private static final String PATH_TO_YOUR_FILE = "/Users/annafoix/dango-git/advent-calendar-2020/Day24/tilesConvination.txt";

    public static void main(String args[]) throws IOException {

        // Scan the input for the instructions
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));

        // Variables needed
        Collection<String> blackTiles = new HashSet<String>();

        // Read all the lines
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            // Loop through all the characters of the line
            // Those are the variables for the position
            Integer x = 0;
            Integer y = 0;

            for (int characterIndex = 0; characterIndex < line.length(); characterIndex++) {
                char character = line.charAt(characterIndex);

                switch (character) {
                    case 'n':
                    case 's': {
                        characterIndex++;
                        char nextCharacter = line.charAt(characterIndex);
                        y += (character == 'n') ? 1 : -1;
                        x += (nextCharacter == 'e') ? 1 : -1;
                        break;
                    }
                    case 'e':
                    case 'w': {
                        x += (character == 'e') ? 2 : -2;
                        break;
                    }
                }

            }

            String nameOfTile = String.format("%d, %d", x, y);
            if (blackTiles.contains(nameOfTile)) {
                blackTiles.remove(nameOfTile);
            } else {
                blackTiles.add(nameOfTile);
            }

        }
        System.out.print(String.format("The number of black tiles is: %d", blackTiles.size()));

    }
}
