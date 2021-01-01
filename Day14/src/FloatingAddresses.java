import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FloatingAddresses {
    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day14/bitmask.txt";
    public static final int TOTAL_BITS = 36;

    public static void main(String args[]) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(PATH_TO_YOUR_FILE));

        Pattern pattern = Pattern.compile("^mem\\[(\\d+)\\] = (\\d+)");

        HashMap<Long, Long> memory = new HashMap<>();

        long maskFloating = 0;
        long maskOr = 0;


        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                // This is the line for setting the memory
                long address = Long.decode(matcher.group(1));
                long value = Long.decode(matcher.group(2));
                setValueInMemory(memory, maskFloating, address | maskOr, value);

            } else {
                // This is the line for setting the mask
                String mask = line.substring("mask =".length()).trim();

                maskFloating = 0;
                maskOr = 0;

                for (int position = 0; position < mask.length(); position++) {
                    switch (mask.charAt(position)) {
                        case '1':
                            maskOr = maskOr | (1L << (mask.length() - position - 1));
                            break;

                        case '0':
                            // nothing to do
                            break;

                        case 'X':
                            maskFloating = maskFloating | (1L << (mask.length() - position - 1));
                            break;
                    }
                }
            }

        }

        System.out.println(String.format("The total of all numbers in memory is: %d", memory
                .values()
                .stream()
                .reduce(0L, (a, b) -> a + b)));
    }

    private static void setValueInMemory(HashMap<Long, Long> memory, long maskFloating, long address, Long value) {
        if (maskFloating == 0) {
            memory.put(address, value);
        } else {
            long singleBitMask;
            while(true) {
                singleBitMask = bitScan(maskFloating);
                if (singleBitMask == 0) {
                    break;
                }
                long addressWithZero = address & ~singleBitMask;
                long addressWithOne = address | singleBitMask;
                long maskFloatingRemaining = maskFloating & ~singleBitMask;
                setValueInMemory(memory, maskFloatingRemaining, addressWithZero, value);
                setValueInMemory(memory, maskFloatingRemaining, addressWithOne, value);
                maskFloating = maskFloatingRemaining;
            }
        }
    }

    private static long bitScan(long value) {
        if (value == 0){
            return 0;
        }
        for (int bitNumber = 0; bitNumber < TOTAL_BITS; bitNumber++) {
            long singleBitMask = 1L << bitNumber;
            if ((value & singleBitMask) != 0) {
                return singleBitMask;
            }
        }
        return 0;
    }
}
