import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Day 4's task was to process passwords given in a messy input file of groups of
 * key value pairs. Processing included parsing by groups of pairs, and then splitting each
 * pairing into its key values. Then testing if each pairing meets a set of conditions for
 * validity. Outputs the number of valid passports for a given input file.
 */
public class DayFour {
    private static final String INPUT_FILE = "input_day4.txt";

    public static void main(String[] args) {
        System.out.println(countValid());
    }

    private static int countValid() {
        Scanner input = new FileReader(INPUT_FILE).getScanner().useDelimiter("\r\n\r\n");
        int sum = 0;
        while (input.hasNext()) {
            Map<String, String> passport = new HashMap<>(); // Represents one passport
            String[] str = input.next().split(" |\r\n");
            for (String s : str) {
                String[] pair = s.split(":"); // Key value delimiter
                passport.put(pair[0], pair[1]);
            }
            // Cid can be ignored, hence this initial test. Validity is at least 7 valid pairs, exl cid
            if (passport.size() == 8 || (passport.size() == 7 & !passport.containsKey("cid"))) {
                if (testYear(Integer.parseInt(passport.get("byr")), 1920, 2002) &&
                    testYear(Integer.parseInt(passport.get("iyr")), 2010, 2020) &&
                    testYear(Integer.parseInt(passport.get("eyr")), 2020, 2030) &&
                    testHeight(passport.get("hgt")) &&
                    testHcl(passport.get("hcl")) &&
                    testEcl(passport.get("ecl")) &&
                    testPid(passport.get("pid"))
                ) sum++;
            }
        }
        return sum;
    }

    private static boolean testYear(int num, int min, int max) {
        return num >= min && num <= max;
    }

    private static boolean testHeight(String str) {
        int num = Integer.parseInt(str.split("\\D")[0]);
        return ((str.endsWith("cm") && num >= 150 && num <= 193) ||
                (str.endsWith("in") && num >= 59 && num <= 76));
    }

    private static boolean testHcl(String str) {
        return str.matches("#[a-z0-9]{6}");
    }

    private static boolean testEcl(String str) {
        return str.matches("amb|blu|brn|gry|grn|hzl|oth");
    }

    private static boolean testPid(String str) {
        return str.matches("[0-9]{9}");
    }
}