import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * Day 6 - Custom Customs, parsing survey results for multiple groups.
 * Input is given as groupings of letters which indicate yes answers to a question.
 * Part 1 is to count the number of unique questions to which anyone in a group answered yes
 * Part 2 is to count the number of unique questions to which everyone in a group answered yes
 * Part 1 Answer: 6542. Part 2 Answer 3299.
 */
public class Day6 {
    private static final String INPUT_FILE = "in/input_day6.txt";

    public static void main(String[] args) {
        System.out.println("Part One Result: " + getPart1());
        System.out.println("Part Two Result: " + getPart2());
    }

    // Returns the sum of unique answers in each group
    private static int getPart1() {
        Scanner input = new FileReader(INPUT_FILE).getScanner().useDelimiter("\r\n\r\n");
        int sum = 0;
        // For each group, add every character to a set and test size of set.
        while (input.hasNext()) {
            HashSet<Character> answerSet = new HashSet<>();
            String answers = input.next().replaceAll("\n|\r", "");
            for (int i = 0; i < answers.length(); i++) {
                answerSet.add(answers.charAt(i));
            }
            sum += answerSet.size();
        }
        return sum;
    }

    // Returns the sum of unique, shared answers in each group
    private static int getPart2() {
        Scanner input = new FileReader(INPUT_FILE).getScanner().useDelimiter("\r\n\r\n");
        int sum = 0;
        while (input.hasNext()) {
            // Create a array of sets, intersect all, add size
            String[] answers = input.next().split("\r\n");
            List<HashSet<Character>> setList = new ArrayList<>();
            for (String str : answers) {
                HashSet<Character> answerSet = new HashSet<>();
                for (int i = 0; i < str.length(); i++) {
                    answerSet.add(str.charAt(i));
                }
                setList.add(answerSet);
            }
            HashSet<Character> resultSet = setList.get(0);
            for (int i = 1; i < setList.size(); i++) {
                resultSet.retainAll(setList.get(i)); // set intersection (destructive)
            }
            sum += resultSet.size();
        }
        return sum;
    }
}