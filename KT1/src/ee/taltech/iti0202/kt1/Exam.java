package ee.taltech.iti0202.kt1;

import java.util.ArrayList;
import java.util.List;

public class Exam {
    /**
     * Returns numbers before the last 4 and after the second last 4.
     * If there is only one 4 in the list, takes all the elements before that.
     * If there is no 4 in the list, returns the whole list.
     *
     * between4([1, 4, 2, 3, 4, 5]) => [2, 3]
     * between4([1, 4, 1, 4, 2, 3, 4, 5]) => [2, 3]
     * between4([1, 2, 4, 2, 3]) => [1, 2]
     * between4([1, 2, 4, 4, 3]) => []
     * between4([1, 2, 3]) => [1, 2, 3]
     */
    public static List<Integer> between4(List<Integer> numbers) {
        List<Integer> betweeners = new ArrayList<>();
        boolean hasFirst = false;
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (!hasFirst) {
                if (numbers.get(i) == 4) {
                    hasFirst = true;
                }
            } else {
                if (numbers.get(i) == 4) {
                    break;
                }
                betweeners.add(0, numbers.get(i));
            }
        }
        if (!hasFirst) {
            return numbers;
        }
        return betweeners;
    }


    /**
     * Given two strings, a and b,
     * create a bigger string made of the first char of a, the first char of b,
     * the second char of a, the second char of b, and so on.
     * Any leftover chars go at the end of the result.
     *
     * mixString("abc", "xyz") => "axbycz"
     * mixString("Hi", "There") => "HTihere"
     * mixString("xxxx", "There") => "xTxhxexre"
     */
    public static String mixString(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int minSize = Math.min(a.length(), b.length());

        for (int i = 0; i < minSize; i++) {
            sb.append(a.charAt(i));
            sb.append(b.charAt(i));
        }
        sb.append(a.substring(minSize));
        sb.append(b.substring(minSize));
        return sb.toString();
    }

//    public static void main(String[] args) {
//        System.out.println(mixString("Hi", "There"));
//    }
}
