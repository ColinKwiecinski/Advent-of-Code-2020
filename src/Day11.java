import java.util.Objects;
import java.util.Scanner;

public class Day11 {
    private static final String INPUT_FILE = "in/input_day11.txt";
    private static final int COLS = 92;
    private static final int ROWS = 99;

    public static void main(String[] args) {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        char[][] seats = new char[ROWS][COLS];
        int rowIndex = 0;
        while (input.hasNextLine()) {
            char[] cols = new char[COLS];
            String str = input.nextLine();
            for (int i = 0; i < COLS; i++) {
                cols[i] = str.charAt(i);
            }
            seats[rowIndex] = cols;
            rowIndex++;
        }
        System.out.println(partOne(seats, 0));
        System.out.println(partTwo(seats, 0));
    }

    private static boolean inRange(int r, int c) {
        return r < ROWS && r >= 0 && c < COLS && c >= 0;
    }

    private static int partOne(char[][] seats, int occupied) {
        char[][] newSeats = new char[ROWS][COLS];
        int priorOccupied = occupied;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                newSeats[r][c] = seats[r][c]; // doing it here to save array copy operations
                int nearby = testNearby(seats, r, c);
                if (nearby == 0 && seats[r][c] == 'L') {
                    newSeats[r][c] = '#';
                    occupied++;
                } else if (nearby >= 4 && seats[r][c] == '#') {
                    newSeats[r][c] = 'L';
                    occupied--;
                }
            }
        }
        if (priorOccupied == occupied) {
            return occupied;
        }
        return partOne(newSeats, occupied);
    }

    private static int testNearby(char[][] seats, int r, int c) {
        return isOccupied(seats, r - 1, c - 1) + isOccupied(seats, r - 1, c) +
                isOccupied(seats, r - 1, c + 1) + isOccupied(seats, r, c - 1) +
                isOccupied(seats, r, c + 1) + isOccupied(seats, r + 1, c - 1) +
                isOccupied(seats, r + 1, c) + isOccupied(seats, r + 1, c + 1);
    }

    private static int isOccupied(char[][] seats, int row, int col) {
        if (inRange(row, col)) {
            if (seats[row][col] == '#') { // factored out to avoid any out of bounds errors
                return 1;
            }
        }
        return 0;
    }

    private static int partTwo(char[][] seats, int occupied) {
        char[][] newSeats = new char[ROWS][COLS];
        int priorOccupied = occupied;
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                newSeats[r][c] = seats[r][c]; // doing it here to save array copy operations
                int visible = countVisible(seats, r, c);
                if (visible == 0 && seats[r][c] == 'L') {
                    newSeats[r][c] = '#';
                    occupied++;
                } else if (visible >= 5 && seats[r][c] == '#') {
                    newSeats[r][c] = 'L';
                    occupied--;
                }
            }
        }
        if (priorOccupied == occupied) {
            return occupied;
        }
        return partTwo(newSeats, occupied);

    }

    private static int countVisible(char[][] seats, int r, int c) {
        return checkDir(seats, r, c, -1 , -1) + checkDir(seats, r, c, -1 , 0) +
                checkDir(seats, r, c, -1 , 1) + checkDir(seats, r, c, 0 , -1) +
                checkDir(seats, r, c, 1 , -1) + checkDir(seats, r, c, 1 , 0) +
                checkDir(seats, r, c, 1 , 1) + checkDir(seats, r, c, 0 , 1);
    }

    private static int checkDir(char[][] seats, int r, int c, int vert, int horiz) {
        // This should change into a do while loop
        r += vert;
        c += horiz;
        while (inRange(r, c)) {
            if (seats[r][c] == '#') {
                return 1;
            } else if (seats[r][c] == 'L') {
                return 0;
            }
            r += vert;
            c += horiz;
        }
        return 0;
    }
}