package ee.taltech.iti0202.cpu;
import java.util.HashMap;
import java.util.Map;

public class Cpu {

    private static boolean checkIf(HashMap<String, Integer> registers, String var, String type, int value) {
        int varValue = registers.get(var);
        switch (type) {
            case "==":
                return varValue == value;
            case "!=":
                return varValue != value;
            case ">":
                return varValue > value;
            case ">=":
                return varValue >= value;
            case "<":
                return varValue < value;
            case "<=":
                return varValue <= value;
            default:
                return false;
        }
    }

    public static Map<String, Integer> compute(String instructions) {
        HashMap<String, Integer> registers = new HashMap<>();
        for(String line : instructions.split("\n")) {
            String[] taskComponents = line.split(" ");
            int incOrDec = taskComponents[1].equals("inc") ? 1 : -1;
            registers.put(taskComponents[4], registers.getOrDefault(taskComponents[4], 0));
            registers.put(taskComponents[0], registers.getOrDefault(taskComponents[0], 0)
                    + (checkIf(registers, taskComponents[4], taskComponents[5], Integer.parseInt(taskComponents[6]))
                    ? 1 : 0) * incOrDec * Integer.parseInt(taskComponents[2]));
        }
        return registers;
    }
}