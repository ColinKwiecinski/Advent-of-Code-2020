import java.util.Scanner;

public class DayThree {
    private static final int LINE_LENGTH = 31;

    public static void main(String[] args) {
        System.out.println(countTrees(3, 1)); // Part 1
        System.out.println(combineSlopes()); // Part 2
    }

    private static int combineSlopes() {
        return (countTrees(1,1) * countTrees(3, 1) *
                countTrees(5, 1) * countTrees(7, 1) * countTrees(1, 2));
    }

    private static int countTrees(int lateral, int vertical) {
        Scanner input = new FileReader("input_day3.txt").getScanner();
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
            for (int i = 0; i < lateral; i++) { // Over lateral
                column++;
                if (column >= LINE_LENGTH) {
                    column = 0;
                }
            }
        }
        return sum;
    }
}
