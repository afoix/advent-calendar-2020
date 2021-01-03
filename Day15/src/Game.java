import java.util.HashMap;

public class Game {
    private long lastNumber = 0;
    private long turn = 1;
    private HashMap<Long, Long> numberLastSaidAt = new HashMap<>();
    private HashMap<Long, Long> numberLastSaidAtBeforeThat = new HashMap<>();

    public long getTurn() { return turn; }

    public long getLastNumber() { return lastNumber; }

    public void sayNumber(long number) {
        if (numberLastSaidAt.containsKey(number)) {
            numberLastSaidAtBeforeThat.put(number, numberLastSaidAt.get(number));
        }
        numberLastSaidAt.put(number, turn);
        lastNumber = number;
        turn++;
    }

    public long calculateNextNumber() {
        if (!numberLastSaidAtBeforeThat.containsKey(lastNumber)) {
            return 0;
        }
        return numberLastSaidAt.get(lastNumber)-numberLastSaidAtBeforeThat.get(lastNumber);
    }

}
