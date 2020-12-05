import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Day 4's task was to process passwords given in a messy input file of groups of
 * key value pairs. Processing included parsing by groups of pairs, and then splitting each
 * pairing into its key values. Then testing if each pairing meets a set of conditions for
 * validity. Outputs the number of valid passports for a given input file.
 */
public class Day4 {
    private static final String INPUT_FILE = "input_day4.txt";

    public static void main(String[] args) {
        System.out.println(countValid());
    }

    /**
     * Counts passports that pass these conditions:
     * byr (Birth Year) - four digits; at least 1920 and at most 2002.
     * iyr (Issue Year) - four digits; at least 2010 and at most 2020.
     * eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
     * hgt (Height) - a number followed by either cm or in:
     * If cm, the number must be at least 150 and at most 193.
     * If in, the number must be at least 59 and at most 76.
     * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
     * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
     * pid (Passport ID) - a nine-digit number, including leading zeroes.
     * cid (Country ID) - ignored, missing or not.
     * @return number of valid passports
     */
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