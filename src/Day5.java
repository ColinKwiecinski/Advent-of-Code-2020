import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Day 5 - Binary Boarding
 * The input is given in "binary space partitioning" format, which looks like "FBFBBFFRLR"
 * where F,L and B,R relate to the front and back half of the current range respectively.
 * F & B relate to the range of the rows, R & L relate to the range of the column.
 * Seat ID is row * 8 + col
 * As an example, "FBFBBFFRLR" maps to row 44, col 5, seatID 357
 * Answers for my input were 901 for highest seatID and 661 for the missing seat.
 */
public class Day5 {
    private static final String INPUT_FILE = "input_day5.txt";

    public static void main(String[] args) {
        System.out.println("Highest Seat ID: " + findSeat());
    }

    private static int findSeat() {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        ArrayList<Integer> seatIDs = new ArrayList<>();
        int max = 0;
        while (input.hasNextLine()) {
            int nextId = getRow(input.nextLine(), 0, 127);
            max = Math.max(max, nextId);
            seatIDs.add(nextId);
        }
        
        // Find missing seat using n*(n+1)/2 sum logic
        Collections.sort(seatIDs); // More runtime intensive but simpler to code
        int sum = 0;
        for (int i : seatIDs) sum += i; // Finding real sum to compare to predicted sum
        int missing = (max * (max + 1) / 2) - sum - ((seatIDs.get(0) - 1) * seatIDs.get(0) / 2);
        System.out.println("Missing Seat: " + missing);
        return max;
    }

    /**
     * Recursively calculate the row and column given a binary space partitioning string
     * @param str Encoded binary string
     * @param min lowest value in the current range
     * @param max highest value in the current range
     * @return seatID in form row * 8 + col, -1 if a bad input passed
     */
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

    /**
     * Recursively calculate the column given a binary space partitioning string
     * @param str Encoded binary string
     * @param min lowest value in the current range
     * @param max highest value in the current range
     * @return the column value, -1 if a bad input passed
     */
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