import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

// It ain't pretty but it'll do
public class DayOne {
    public static void main(String args[]) throws FileNotFoundException {
        System.out.println("Two numbers runtime: " + timerOne());
        System.out.println("Three numbers runtime: " + timerTwo());
    }

    // There's probably an efficient data structure that would make this not N^2 but brute force
    // is how i'm gonna do it
    private static int twoNums() throws FileNotFoundException {
        ArrayList<Integer> numbers = getNumbers();
        int size = numbers.size();
        for (int i = 0; i < size; i++) {
            //System.out.println("Currently on number: " + numbers.get(i));
            for (int j = 0; j < size; j++) {
                int first = numbers.get(i);
                int second = numbers.get(j);
                if (first + second == 2020) {
                    System.out.println("First number is: " + first);
                    System.out.println("Second number is " + second);
                    return (first * second);
                }
            }
        }
        return 0;
    }

    private static int threeNums() throws FileNotFoundException {
        ArrayList<Integer> numbers = getNumbers();
        int size = numbers.size();
        for (int i = 0; i < size; i++) {
            //System.out.println("Currently on number: " + numbers.get(i));
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    int first = numbers.get(i);
                    int second = numbers.get(j);
                    int third = numbers.get(k);
                    if (first + second + third == 2020) {
                        System.out.println("First number is: " + first);
                        System.out.println("Second number is " + second);
                        System.out.println("Third number is " + third);
                        return (first * second * third);
                    }
                }
            }
        }
        return 0;
    }

    private static long timerOne() throws FileNotFoundException {
        final long start = System.currentTimeMillis();
        System.out.println(twoNums());
        final long end = System.currentTimeMillis();
        return (end - start);
    }

    private static long timerTwo() throws FileNotFoundException {
        final long start = System.currentTimeMillis();
        System.out.println(threeNums());
        final long end = System.currentTimeMillis();
        return (end - start);
    }

    private static ArrayList<Integer> getNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        try {
            File input = new File("input_day1.txt");
            Scanner scan = new Scanner(input);
            while (scan.hasNext()) {
                numbers.add(scan.nextInt());
            }
            scan.close();
        } catch (FileNotFoundException e){
            System.out.println("Error reading file");
            e.printStackTrace();
        }
        Collections.sort(numbers);
        return numbers;
    }
}
