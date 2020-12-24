import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class SimulateLivingArt {
    private static final String PATH_TO_YOUR_FILE = "/Users/annafoix/dango-git/advent-calendar-2020/Day24/tilesConvination.txt";

    public static void main(String args[]) throws IOException {

        // Scan the input for the instructions
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));

        // Variables needed
        Collection<Tile> blackTiles = new HashSet<Tile>();

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

            Tile tile = new Tile(x, y);
            if (blackTiles.contains(tile)) {
                blackTiles.remove(tile);
            } else {
                blackTiles.add(tile);
            }

        }

        for (int day = 0; day < 100; day++) {
            Collection<Tile> endOfDay = new HashSet<Tile>();
            for (Tile tile : blackTiles) {
                Collection<Tile> tilesAdjacent = getTilesAdjacent(tile);
                endOfDay.add(tile);
                endOfDay.addAll(tilesAdjacent);
            }

            Collection<Tile> yesterdayBlackTiles = blackTiles;
            endOfDay.removeIf(tile ->{
                boolean wasBlackYesterday = yesterdayBlackTiles.contains(tile);
                Set<Tile> tilesAdjacent = getTilesAdjacent(tile);
                tilesAdjacent.retainAll(yesterdayBlackTiles);

                if (wasBlackYesterday && (tilesAdjacent.size()==0 || tilesAdjacent.size()>2)){
                    return true;
                }
                if (!wasBlackYesterday && tilesAdjacent.size()==2){
                    return false;
                }

                return !wasBlackYesterday;
            });


            blackTiles = endOfDay;
        }


        System.out.print(String.format("The number of black tiles for 100 days is: %d", blackTiles.size()));

    }

    private static HashSet<Tile> getTilesAdjacent(Tile centerTile) {
        HashSet<Tile> tiles = new HashSet<>();
        tiles.add(new Tile(centerTile.getX()-2, centerTile.getY()));
        tiles.add(new Tile(centerTile.getX()+2, centerTile.getY()));
        tiles.add(new Tile(centerTile.getX()-1, centerTile.getY()-1));
        tiles.add(new Tile(centerTile.getX()+1, centerTile.getY()-1));
        tiles.add(new Tile(centerTile.getX()-1, centerTile.getY()+1));
        tiles.add(new Tile(centerTile.getX()+1, centerTile.getY()+1));
        return tiles;
    }
}
