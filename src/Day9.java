import java.util.ArrayList;
import java.util.Scanner;

public class Day9 {
    private static final String INPUT_FILE = "in/input_day9.txt";

    public static void main(String[] args) {
        getAnswer();
    }

    /**
     * Prints answers to part one and two
     */
    private static void getAnswer() {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        ArrayList<Integer> numbers = new ArrayList<>();
        while (input.hasNextInt()) {
            int next = input.nextInt();
            numbers.add(next);
        }
        System.out.println(partOne(numbers));
        System.out.println(partTwo(numbers));
    }

    /**
     * Returns value where the previous 25 numbers do not contain two numbers that sum to that value
     * @param numbers ArrayList of all input numbers
     * @return int single value that is not a possible sum
     */
    private static int partOne(ArrayList<Integer> numbers) {
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

    /**
     * Finds the encryption weakness in XMAS list of numbers by finding a subset of
     * input numbers that sums to the number from partOne
     * @param numbers ArrayList of all input numbers
     * @return the sum of the highest and lowest numbers, -1 if error occurs
     */
    private static int partTwo(ArrayList<Integer> numbers) {
        int p1 = partOne(numbers);
        ArrayList<Integer> result = recurseTwo(numbers, p1, 2);
        int min = Integer.MAX_VALUE;
        int max = 0;
        if (result != null) {
            for (int i : result) {
                min = Math.min(min, i);
                max = Math.max(max, i);
            }
            return min + max;
        } else { // Failure case indicates error
            return -1;
        }
    }

    /**
     * Searches for the subset that sums to the value of partOne
     * @param numbers - ArrayList of all input numbers
     * @param target - target number from part one
     * @param setSize - current size of subset to examine
     * @return the subset of sequential numbers that sum to target
     */
    private static ArrayList<Integer> recurseTwo(ArrayList<Integer> numbers, int target, int setSize) {
        for (int i = 0; i < numbers.size() - setSize; i++) { // Loop thru every possible combo
            ArrayList<Integer> currentSet = new ArrayList<>();
            for (int j = i; j < i + setSize; j++) { // build a subset of size setSize
                currentSet.add(numbers.get(j));
            }
            int currentSum = 0;
            for (int value : currentSet) { // Sum all elements in subSet
                currentSum += value;
            }
            if (currentSum == target) {
                return currentSet; // base case
            }
        }
        if (setSize < numbers.size()) { // recursive case
            return recurseTwo(numbers, target, setSize + 1);
        } else {
            return null; // end case for failure
        }
    }
}