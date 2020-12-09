import java.util.ArrayList;
import java.util.Scanner;

public class Day9 {
    private static final String INPUT_FILE = "in/input_day9.txt";

    public static void main(String[] args) {
        System.out.println(getAnswer());
    }

    private static int getAnswer() {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        ArrayList<Integer> numbers = new ArrayList<>();
        while (input.hasNextInt()) {
            int next = input.nextInt();
            numbers.add(next);
        }
        //System.out.println(numbers);

        for (int i = 25; i < numbers.size(); i++) {
            int current = numbers.get(i);
            boolean test = false;
            for (int j = i - 25; j < i; j++) { // maybe make this a while loop so it can exit early.
                for (int k = i - 25; k < j; k++) {
                    if (numbers.get(k) + numbers.get(j) == current) {
                        test = true;
                        break;
                    }
                }
                if (test) break;
            }
            if (!test) return current;
        }


        return -1;
    }
}