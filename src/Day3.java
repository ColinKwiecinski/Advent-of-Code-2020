import java.util.Scanner;

/**
 * Day 3 - Toboggan Trajectory
 * Solving a traversal of various vertical and horizontal shifts and counting the instances
 * of a character when making the shift. Wrap around to the start of the line if hit end of line.
 * Required File: input_day3.txt
 * Part 1 Answer: 167, Part 2 Answer: 736527114
 */
public class Day3 {
    private static final int LINE_LENGTH = 31;
    private static final String INPUT_FILE = "in/input_day3.txt";

    public static void main(String[] args) {
        System.out.println(countTrees(3, 1)); // Part 1
        System.out.println(combineSlopes()); // Part 2
    }

    private static int combineSlopes() {
        return (countTrees(1,1) * countTrees(3, 1) *
                countTrees(5, 1) * countTrees(7, 1) * countTrees(1, 2));
    }

    private static int countTrees(int lateral, int vertical) {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        int sum = 0;
        int column = 0;
        while (input.hasNextLine()) {
            String row = input.nextLine();
            if (vertical == 2 && input.hasNextLine()) { // Move down two and ignore a line
                input.nextLine();
            }
            if (row.charAt(column) == '#') { // Testing at the end of a shift
                sum++;
            }
            column = (column + lateral) % LINE_LENGTH; // Took this from Greg's solution
        }
        input.close();
        return sum;
    }
}
