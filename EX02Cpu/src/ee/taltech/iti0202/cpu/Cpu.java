package ee.taltech.iti0202.cpu;
import java.util.HashMap;
import java.util.Map;

public class Cpu {

    private static final int CHANGED_VAR = 0;
    private static final int INC_DEC = 1;
    private static final int CHANGE_VALUE = 2;
    private static final int COND_VAR = 4;
    private static final int COMP_TYPE = 5;
    private static final int COMP_VALUE = 6;

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
        for (String line : instructions.split("\n")) {
            String[] taskComponents = line.split(" ");
            int incOrDec = taskComponents[INC_DEC].equals("inc") ? 1 : -1;
            registers.put(taskComponents[COND_VAR], registers.getOrDefault(taskComponents[COND_VAR], 0));
            registers.put(taskComponents[CHANGED_VAR], registers.getOrDefault(taskComponents[CHANGED_VAR],
                    0) + (checkIf(registers, taskComponents[COND_VAR], taskComponents[COMP_TYPE],
                    Integer.parseInt(taskComponents[COMP_VALUE])) ? 1 : 0) * incOrDec
                    * Integer.parseInt(taskComponents[CHANGE_VALUE]));
        }
        return registers;
    }
}
