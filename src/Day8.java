import java.util.ArrayList;
import java.util.Scanner;

public class Day8 {
    private static final String INPUT_FILE = "in/input_day8.txt";
    private int acc;
    private ArrayList<Instruction> instructions;

    public static void main(String[] args) {
        new Day8().run();
    }

    public void run() {
        acc = 0;
        instructions = new ArrayList<>();
        System.out.println(parseInput());
    }

    private int parseInput() {
        Scanner input = new FileReader(INPUT_FILE).getScanner();

        while (input.hasNextLine()) {
            String[] str = input.nextLine().split(" ");
            instructions.add(new Instruction(str[0], Integer.parseInt(str[1])));
        }
        for (int i = 0; i < instructions.size(); i++) {
            Instruction ins = instructions.get(i);
            if (ins.visited) {
                return acc;
            } else {
                ins.visited = true;
            }
            if (ins.op.equals("acc")) {
                acc += ins.arg;
            } else if (ins.op.equals("jmp")) {
                i += ins.arg - 1;
            }
        }
        return -1;
    }

    private class Instruction {
        private String op;
        private int arg;
        private boolean visited;

        public Instruction (String s, int a) {
            op = s;
            arg = a;
            visited = false;
        }

        public boolean isVisited() {
            return visited;
        }

        public String getOp() {
            return op;
        }

        public int getArg() {
            return arg;
        }
    }
}