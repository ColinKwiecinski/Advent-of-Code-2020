import java.util.Scanner;

public class Day12 {
    private static final String INPUT_FILE = "in/input_day12.txt";
    private static int h;
    private static int v;

    public static void main(String[] args) {
        System.out.println(getAnswer());
    }

    private static int getAnswer() {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        h = 0; // horizontal
        v = 0; // vertical
        int dirIndex = 1; // ship starts facing East
        char[] compass = {'N', 'E', 'S', 'W'};
        while (input.hasNextLine()) {
            String str = input.nextLine();
            char act = str.charAt(0);
            int val = Integer.parseInt(str.substring(1));

            if (act == 'R') {
                dirIndex = (dirIndex + (val / 90)) % compass.length;
            } else if (act == 'L') {
                dirIndex = (dirIndex - (val / 90)) % compass.length;
                if (dirIndex < 0) {
                    dirIndex = compass.length + dirIndex; // loop back to other end of array
                }
            } else if (act == 'F') {
                move(compass[dirIndex], val);
            } else {
                move(act, val);
            }
        }
        return Math.abs(h) + Math.abs(v);
    }

    private static void move(char dir, int val) {
        if (dir == 'N') {
            v += val;
        } else if (dir == 'E') {
            h += val;
        } else if (dir == 'S') {
            v -= val;
        } else if (dir == 'W') {
            h -= val;
        }
    }
}