import java.util.ArrayList;
import java.util.Scanner;

public class Day8 {
    private static final String INPUT_FILE = "in/input_day8.txt";

    public static void main(String[] args) {
        System.out.println(partOne());
        System.out.println(partTwo());
    }

    private static ArrayList<Instruction> getIns() {
        Scanner input = new FileReader(INPUT_FILE).getScanner();
        ArrayList<Instruction> instructions = new ArrayList<>();
        while (input.hasNextLine()) {
            String[] str = input.nextLine().split(" ");
            instructions.add(new Instruction(str[0], Integer.parseInt(str[1])));
        }
        return instructions;
    }

    private static int partOne() {
        ArrayList<Instruction> instructions = getIns();
        int acc = 0;
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

    private static int partTwo() {
        ArrayList<Instruction> instructions = getIns();
        int acc = 0;
        int max = 0;
        int lastOP = 0;
        for (int i = 0; i < instructions.size(); i++) {
            max = Math.max (i, max);

            Instruction ins = instructions.get(i);
            if (i == 394) {
                System.out.println("max case");
                System.out.println(i);
                System.out.println(lastOP);
                ins.changeOp();
            }
            if (ins.visited) {
                System.out.println(max + "return case");
                System.out.println(lastOP);
                System.out.println(i);
                return acc;
            } else {
                ins.visited = true;
            }
            if (ins.op.equals("acc")) {
                acc += ins.arg;
            } else if (ins.op.equals("jmp")) {
                lastOP = i;
                i += ins.arg - 1;
            }
            //System.out.println("broke for loop " + i);
        }
        //System.out.println(acc);
        return acc;
    }
    
    private static class Instruction {
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

        public void changeOp() {
            if (op.equals("jmp")) op = "nop";
            else if (op.equals("nop")) op = "jmp";
        }
    }
}