import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class seatsEndedUpOccupied {

    private static final String PATH_TO_YOUR_FILE = "/Users/annafoix/dango-git/advent-calendar-2020/Day11/seatsOccupiedPattern.txt";

    public static void main(String args[]) throws IOException {
        // Variables for read the file
        SeatState[][] seats = getSeatMap();

        while (true) {
            SeatState[][] newState = stepSimulation(seats);
            if (newState == null) {
                break;
            }

            seats = newState;
        }

        Integer counterSeats = countOccupatedSeats(seats);

        System.out.print(String.format("The sits that ended up occupied are: %s ", counterSeats));

    }

    private static Integer countOccupatedSeats(SeatState[][] seats) {
        Integer counterSeats = 0;

        for (int yPosition = 0; yPosition < seats[0].length; yPosition++) {
            for (int xPosition = 0; xPosition < seats.length; xPosition++) {
                if (seats[xPosition][yPosition]==SeatState.OCCUPIED_SEAT){
                    counterSeats++;
                }
            }
        }
        return counterSeats;
    }

    public enum SeatState {
        FLOOR,
        EMPTY_SEAT,
        OCCUPIED_SEAT
    }

    public static SeatState[][] stepSimulation(SeatState[][] previousState) {
        SeatState[][] newState = copyState(previousState);
        boolean didChangeAnything = false;

        for (int yPosition = 0; yPosition < newState[0].length; yPosition++) {
            for (int xPosition = 0; xPosition < newState.length; xPosition++) {
                if (newState[xPosition][yPosition] == SeatState.FLOOR) {
                    continue;
                }

                int adjacent = countAdjacentOccupiedSeats(previousState, xPosition, yPosition);
                if (previousState[xPosition][yPosition] == SeatState.OCCUPIED_SEAT && adjacent >= 4) {
                    newState[xPosition][yPosition] = SeatState.EMPTY_SEAT;
                    didChangeAnything = true;
                }
                if (previousState[xPosition][yPosition] == SeatState.EMPTY_SEAT && adjacent == 0) {
                    newState[xPosition][yPosition] = SeatState.OCCUPIED_SEAT;
                    didChangeAnything = true;
                }
            }

        }

        if (!didChangeAnything) {
            return null;
        }

        return newState;
    }

    private static int countAdjacentOccupiedSeats(SeatState[][] seats, int xPosition, int yPosition) {
        Integer occupiedSeats = 0;
        for (int deltaX = -1; deltaX <= 1; deltaX++) {
            for (int deltaY = -1; deltaY <= 1; deltaY++) {
                if (deltaX == 0 && deltaY == 0) {
                    continue;
                }
                int seatX = xPosition + deltaX;
                int seatY = yPosition + deltaY;

                if (seatX < 0 || seatY < 0) {
                    continue;
                }
                if (seatX >= seats.length || seatY >= seats[0].length) {
                    continue;
                }
                if (seats[seatX][seatY] == SeatState.OCCUPIED_SEAT) {
                    occupiedSeats++;
                }
            }

        }
        return occupiedSeats;
    }

    private static SeatState[][] copyState(SeatState[][] previousState) {
        SeatState[][] copy = new SeatState[previousState.length][];
        for (int row = 0; row < previousState.length; row++) {
            copy[row] = Arrays.copyOf(previousState[row], previousState[row].length);
        }
        return copy;
    }

    public static SeatState[][] getSeatMap() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));
        String line = reader.readLine().trim();
        final Integer seatingAreaWidth = line.length();
        List<String> lines = new ArrayList<>();

        lines.add(line);

        while (true) {
            line = reader.readLine();
            if (line == null || line.trim().length() == 0) {
                break;
            }
            lines.add(line.trim());
        }

        final Integer seatingAreaHeight = lines.size();
        SeatState[][] seats = new SeatState[seatingAreaWidth][];

        for (int xPosition = 0; xPosition < seatingAreaWidth; xPosition++) {
            seats[xPosition] = new SeatState[seatingAreaHeight];
        }

        for (int yPosition = 0; yPosition < seatingAreaHeight; yPosition++) {
            String row = lines.get(yPosition);

            for (int xPosition = 0; xPosition < seatingAreaWidth; xPosition++) {
                char character = row.charAt(xPosition);
                switch (character) {
                    case '.': {
                        seats[xPosition][yPosition] = SeatState.FLOOR;
                        break;
                    }
                    case 'L': {
                        seats[xPosition][yPosition] = SeatState.EMPTY_SEAT;
                        break;
                    }
                }

            }
        }
        return seats;
    }
}
