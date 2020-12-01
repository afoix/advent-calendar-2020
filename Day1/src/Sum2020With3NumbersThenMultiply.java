import java.io.FileNotFoundException;
import java.util.List;

public class Sum2020With3NumbersThenMultiply {

    public static void main(String args[]) throws FileNotFoundException {
        System.out.println("test for Advent calendar");

        // Read the list
        List<Integer> numbers = ReadList.readList();

        // This loop goes through all the numbers
        for (int numberA = 0; numberA < numbers.size(); numberA++ ){
            // This loop goes through all the numbers after numberA
            for (int numberB = numberA + 1; numberB < numbers.size(); numberB++){
                for (int numberC = numberB + 1; numberC < numbers.size(); numberC++) {
                    compareNumbers(numbers.get(numberA), numbers.get(numberB), numbers.get(numberC));
                }
            }
        }
    }

    private static void compareNumbers(Integer numberA, Integer numberB, Integer numberC) {
        Integer sumOfNumbers =  numberA + numberB + numberC;

        if (sumOfNumbers == 2020) {
            Integer product = numberA * numberB * numberC;
            System.out.println(String.format("The numbers are %d, %d and %d. The product is %d", numberA, numberB, numberC, product));
            return;
        }
    }


}
