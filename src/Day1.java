import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Day1 {
    private static final String INPUT_FILE = "in/input_day1.txt";

    public static void main(String args[]) {
        System.out.println("Two numbers runtime: " + timerOne());
        System.out.println("Three numbers runtime: " + timerTwo());
    }

    private static int twoNums() {
        ArrayList<Integer> numbers = getNumbers();
        int size = numbers.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int first = numbers.get(i);
                int second = numbers.get(j);
                if (first + second == 2020) {
                    return (first * second);
                }
            }
        }
        return 0;
    }

    private static int threeNums() {
        ArrayList<Integer> numbers = getNumbers();
        int size = numbers.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    int first = numbers.get(i);
                    int second = numbers.get(j);
                    int third = numbers.get(k);
                    if (first + second + third == 2020) {
                        return (first * second * third);
                    }
                }
            }
        }
        return 0;
    }

    private static long timerOne() {
        final long start = System.currentTimeMillis();
        System.out.println(twoNums());
        final long end = System.currentTimeMillis();
        return (end - start);
    }

    private static long timerTwo() {
        final long start = System.currentTimeMillis();
        System.out.println(threeNums());
        final long end = System.currentTimeMillis();
        return (end - start);
    }

    private static ArrayList<Integer> getNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        while (input.hasNext()) {
            numbers.add(input.nextInt());
        }
        input.close();
        Collections.sort(numbers);
        return numbers;
    }
}
