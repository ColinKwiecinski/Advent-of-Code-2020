import java.util.ArrayList;
import java.util.Scanner;

public class Day12 {
    private static final String INPUT_FILE = "in/input_day12.txt";
    private static final char[] COMPASS = {'N', 'E', 'S', 'W'};
    private static int h; // ship's horizontal value (E/W)
    private static int v; // ship's vertical value (N/S)
    private static int wpH; // waypoint's relative horizontal to ship
    private static int wpV; // waypoint's relative vertical to ship


    public static void main(String[] args) {
        System.out.println(partOne());
        System.out.println(partTwo());
    }

    private static ArrayList<String> getInput() {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        h = 0; // horizontal
        v = 0; // vertical
        ArrayList<String> result = new ArrayList<>();
        while (input.hasNextLine()) {
            result.add(input.nextLine());
        }
        return result;
    }

    private static int partOne() {
        ArrayList<String > input = getInput();
        int dirIndex = 1; // ship starts facing East
        for (int i = 0; i < input.size(); i++) {
            String str = input.get(i);
            char act = str.charAt(0);
            int val = Integer.parseInt(str.substring(1));

            if (act == 'R') { // clockwise
                dirIndex = (dirIndex + (val / 90)) % COMPASS.length;
            } else if (act == 'L') { // counter clockwise
                dirIndex = (dirIndex - (val / 90)) % COMPASS.length;
                if (dirIndex < 0) {
                    dirIndex = COMPASS.length + dirIndex; // loop back to other end of array
                }
            } else if (act == 'F') {
                moveShip(COMPASS[dirIndex], val);
            } else {
                moveShip(act, val);
            }
        }
        return Math.abs(h) + Math.abs(v);
    }

    private static void moveShip(char dir, int val) {
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

    private static int partTwo() {
        ArrayList<String> input = getInput(); // this also resets v and h to zero
        wpV = 1; // waypoint vertical, starts at 1 north
        wpH = 10; // waypoint horizontal, starts at 10 east

        for (String str : input) {
            char act = str.charAt(0);
            int val = Integer.parseInt(str.substring(1));
            if (act == 'R') {
                // rotate waypoint cw around ship (negative angles)
                rotateWaypoint(val * -1);
            } else if (act == 'L') {
                // rotate waypoint ccw around ship (positive angles)
                rotateWaypoint(val);
            } else if (act == 'F') {
                moveTo(val);
            } else {
                moveWaypoint(act, val);
            }
        }
        return Math.abs(h) + Math.abs(v);
    }

    private static void moveWaypoint(char dir, int val) {
        if (dir == 'N') {
            wpV += val;
        } else if (dir == 'E') {
            wpH += val;
        } else if (dir == 'S') {
            wpV -= val;
        } else if (dir == 'W') {
            wpH -= val;
        }
    }

    private static void moveTo(int count) {
        h += wpH * count;
        v += wpV * count;
    }

    private static void rotateWaypoint(int deg) {
        double rads = Math.toRadians(deg);
        int tempH = wpH;
        int tempV = wpV;
        // Need to round to fix imprecision from deg to rad conversion
        wpH = (int)Math.round((tempH * Math.cos(rads) - tempV * Math.sin(rads)));
        wpV = (int)Math.round((tempV * Math.cos(rads) + tempH * Math.sin(rads)));
    }
}