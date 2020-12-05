import java.util.Scanner;

public class Day5 {
    private static final String INPUT_FILE = "input_day5.txt";

    public static void main(String[] args) {
        System.out.println(findSeat());
    }

    private static int findSeat() {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        int max = 0;
        while (input.hasNextLine()) {
            max = Math.max(max, getRow(input.nextLine(), 0, 127));
        }
        return max;
    }

    private static int getRow(String str, int min, int max) {
        if (str.length() == 3) {
            return min * 8 + getCol(str, 0, 7);
        }
        int diff = max - min;
        if (str.startsWith("F")) {
            return getRow(str.substring(1), min, max - (diff / 2 + diff % 2));
        } else if (str.startsWith("B")) {
            return getRow(str.substring(1), min + (diff / 2) + (max % 2), max);
        }
        return -1;
    }

    private static int getCol(String str, int min, int max) {
        if (str.length() == 0) {
            return min;
        }
        int diff = max - min;
        if (str.startsWith("L")) {
            return getCol(str.substring(1), min, max - (diff / 2 + diff % 2));
        } else if (str.startsWith("R")) {
            return getCol(str.substring(1), min + (diff / 2) + (max % 2), max);
        }
        return -1;
    }

}
