import java.util.Scanner;

/**
 * Part one of this problem was to find if a given password in the format
 * "n-m x: password" contained the appropriate number of characters x within the n-m range.
 * The second part changed it so that n & m were indexes of password that had to match an XOR
 * condition to pass.
 *
 * The result is returning the count of passing passwords in each part given input "input_day2.txt"
 * Part 1: 628 passwords passed
 * Part 2: 705 passwords passed
 *
 * Future improvements would be to find a way to consolidate my code to minimize
 * repeating code blocks.
 */
public class Day2 {
    private static final String INPUT_FILE = "in/input_day2.txt";

    public static void main(String[] args) {
        new Day2().run();
    }

    // Wrote it in this structure because I previously had some fields. This allows me to
    // make non-static helper methods.
    public void run() {
        System.out.println(getSumPart1());
        System.out.println(getSumPart2());
    }

    private int getSumPart1() {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        int count = 0;
        while (input.hasNext()) {
            if (testPasswordPart1(input.nextLine())) {
                count++;
            }
        }
        input.close();
        return count;
    }

    // Tests if there at least min number of occurrences and less than max occurrences
    // This could be factored out by making a parse method that returns a String[]
    private boolean testPasswordPart1(String str) {
        String[] fragments = str.split(" ");
        String[] range = fragments[0].split("-");
        int min = Integer.parseInt(range[0]);
        int max = Integer.parseInt(range[1]);
        String ruleChar = fragments[1].split(":")[0]; // This could be improved
        int occ = fragments[2].length() - fragments[2].replaceAll(ruleChar, "").length();
        return (occ <= max && occ >= min);
    }

    private int getSumPart2() {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        int count = 0;
        while (input.hasNext()) {
            if (testPasswordPart2(input.nextLine())) {
                count++;
            }
        }
        input.close();
        return count;
    }

    // XOR password character test
    private boolean testPasswordPart2(String str) {
        String[] fragments = str.split(" ");
        String[] range = fragments[0].split("-");
        int min = Integer.parseInt(range[0]);
        int max = Integer.parseInt(range[1]);
        char ruleChar = fragments[1].charAt(0);
        return (fragments[2].charAt(min - 1) == ruleChar ^ fragments[2].charAt(max - 1) == ruleChar);
    }
}
