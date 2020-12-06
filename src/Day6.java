import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Day6 {
    private static final String INPUT_FILE = "in/input_day6.txt";

    public static void main(String[] args) {
        System.out.println(getPart1());
        System.out.println(getPart2());
    }

    private static int getPart1() {
        Scanner input = new FileReader(INPUT_FILE).getScanner().useDelimiter("\r\n\r\n");
        int sum = 0;
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

    private static int getPart2() {
        Scanner input = new FileReader(INPUT_FILE).getScanner().useDelimiter("\r\n\r\n");
        int sum = 0;
        while (input.hasNext()) {
            // Create a array of sets, intersect, add size
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
                resultSet.retainAll(setList.get(i));
            }
            sum += resultSet.size();
        }
        return sum;
    }
}