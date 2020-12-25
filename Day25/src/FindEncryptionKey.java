import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FindEncryptionKey {
    private static final String PATH_TO_YOUR_FILE = "/Users/annafoix/dango-git/advent-calendar-2020/Day25/input.txt";

    public static void main(String args[]) throws IOException {

        // Scan the input for the instructions
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));

        // Variables need it
        Long cardPublicKey = Long.decode(reader.readLine().strip());
        Long doorPublicKey = Long.decode(reader.readLine().strip());


        long cardLoopSize = 0L;
        long value = 1L;

        while (value != cardPublicKey) {
            value *= 7;
            value = value % 20201227;
            cardLoopSize += 1;
        }

        Long encryptionKey = transformSubjectNumber(doorPublicKey, cardLoopSize);

        System.out.println(String.format("The encryption key is: %d", encryptionKey));

    }


    private static Long transformSubjectNumber(Long subjectNumber, Long loopSize){
        long value = 1L;
        for (int i = 0; i < loopSize; i++) {
            value *= subjectNumber;
            value = value % 20201227;
        }
        return value;
    }
}
