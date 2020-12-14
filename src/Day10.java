import com.sun.javafx.geom.Edge;

import java.util.*;

public class Day10 {
    private static final String INPUT_FILE = "in/input_day10.txt";

    public static void main(String[] args) {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        ArrayList<Integer> inputData = new ArrayList<>();
        while (input.hasNextInt()) {
            inputData.add(input.nextInt());
        }
        Collections.sort(inputData);
        System.out.println(partOne(inputData));
        System.out.println(partTwo(inputData));
    }

    private static int partOne(ArrayList<Integer> input) {
        int ones = 0;
        int threes = 1; // start at one because of built in phone adapter
        int joltSum = 0;
        for (int i = 0; i < input.size(); i++) {
            int diff = input.get(i) - joltSum;
            if (diff == 1) {
                ones++;
            } else if (diff == 3) {
                threes++;
            }
            joltSum += diff;
        }
        return ones * threes;
    }

    private static int partTwo(ArrayList<Integer> input) {
        int sum = 0;
        ArrayList<Integer> splitIndices = new ArrayList<>();
        for (int i = 0; i < input.size() - 1; i++) {
            int diff = input.get(i + 1) - input.get(i);
            if (diff == 3) {
                splitIndices.add(i);
            }
        }
        for (int i = 0; i < splitIndices.size() - 1; i++) {
            sum *= partTwoSolver(input.subList(splitIndices.get(i), splitIndices.get(i + 1)));
        }
        return sum;
    }

    private static int partTwoSolver(List<Integer> input) {
        // break into subsections at each three, find number of permutations for each subsection, multiply together to get result.
        Map<Vertex, Edge> edgeTo = new HashMap<>();
        Map<Vertex, Integer> distTo = new HashMap<>();
        // init a set for visited nodes?
        // init a queue for order to visit nodes?

        for (Integer i : input) {
            // create every vertex based on input
            // set.add(new Vertex(i));
        }

        // add edges between vertices based on input size. keep track of sum? don't add edges that are more than 3 apart

        // build path tree

        // backtrack to find number of paths...

        // return count of paths between s and t. hopefully shouldn't need to verify that they're not more than three apart
        return -1;
    }


    private class Vertex {
        private final int data;

        public Vertex(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }
    }

    private class Edge {
        private final Vertex from;
        private final Vertex to;

        public Edge(Vertex to, Vertex from) {
            this.to = to;
            this.from = from;
        }

        public Vertex from() {
            return this.from;
        }

        public Vertex to() {
            return this.to;
        }
    }
}
