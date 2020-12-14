import java.util.*;

public class Day10 {
    private static final String INPUT_FILE = "in/input_day10.txt";
    private static final int TRIB_SIZE = 5;

    public static void main(String[] args) {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        ArrayList<Integer> inputData = new ArrayList<>();
        while (input.hasNextInt()) {
            inputData.add(input.nextInt());
        }
        inputData.add(0); // Wall plug has joltage zero
        Collections.sort(inputData);
        inputData.add(inputData.get(inputData.size() - 1) + 3); // Phone adapter has + 3 to max
        System.out.println(partOne(inputData));
        System.out.println(partTwo(inputData));
    }

    private static int partOne(ArrayList<Integer> input) {
        int ones = 0;
        int threes = 0; // start at one because of built in phone adapter
        int joltSum = 0;
        for (int i = 0; i < input.size(); i++) {
            int diff = input.get(i) - joltSum;
            if (diff == 1) {
                ones++;
            } else if (diff == 3) {
                threes++;
            }
            joltSum += diff;
        }
        return ones * threes;
    }

    private static long partTwo(ArrayList<Integer> input) {
        long sum = 1;
        ArrayList<Integer> splitIndices = new ArrayList<>();
        splitIndices.add(0);
        for (int i = 0; i < input.size() - 1; i++) {
            int diff = input.get(i + 1) - input.get(i);
            if (diff == 3) {
                splitIndices.add(i + 1);
            }
        }
        ArrayList<Integer> tribSeq = buildTrib(1, 1, 2, TRIB_SIZE);
        for (int i = 0; i < splitIndices.size() - 1; i++) {
            sum *= partTwoTrib(input.subList(splitIndices.get(i), splitIndices.get(i + 1)), tribSeq);
        }
        return sum;
    }

    private static int partTwoTrib(List<Integer> input, ArrayList<Integer> tribs) {
        return tribs.get(input.size() - 1);
    }

    // Returns a Tribonacci sequence with given seeds a, b, c, up to specified size
    private static ArrayList<Integer> buildTrib(int a, int b, int c, int size) {
        int count = 2; // first three seed numbers are included
        ArrayList<Integer> result = new ArrayList<>();
        result.add(a);
        result.add(b);
        result.add(c);
        while (count < size) {
            int nextSum = result.get(count) + result.get(count - 1) + result.get(count - 2);
            result.add(nextSum);
            count++;
        }
        return result;
    }
}
