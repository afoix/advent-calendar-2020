import java.io.FileNotFoundException;

public class NumberInThe2020thPosition {

    public static void main(String args[]) throws FileNotFoundException {
        Game game = new Game();
        game.sayNumber(0);
        game.sayNumber(1);
        game.sayNumber(5);
        game.sayNumber(10);
        game.sayNumber(3);
        game.sayNumber(12);
        game.sayNumber(19);

        while (game.getTurn() <= 2020) {
            game.sayNumber(game.calculateNextNumber());
        }
        System.out.println(String.format("The number for the 2020 position is: %d", game.getLastNumber()));
    }
}
