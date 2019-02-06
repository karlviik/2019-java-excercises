package ee.taltech.iti0202.strings;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class Main {
    /**
     * Classic count the words exercise.
     * <p>
     * From input count all the words and collect results to map.
     *
     * @param sentence array of strings, can't be null.
     * @return map containing all word to count mappings.
     */
    public static Map<String, Integer> wordCount(String[] sentence) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : sentence) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }


    /**
     * Find the most frequent word in given array of strings.
     *
     * If there are multiple most frequent words to choose from pick any of them.
     *
     * @param sentence array of strings, can't be null.
     * @return most frequent word in the sentence
     */
    public static String mostFrequentWord(String[] sentence) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : sentence) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        int count = 0;
        String word = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > count) {
                count = entry.getValue();
                word = entry.getKey();
            }
        }
        return word;
    }

    /**
     * Loop over the given list of strings to build a resulting list of string like this:
     * when a string appears the 2nd, 4th, 6th, etc. time in the list, append the string to the result.
     * <p>
     * Return the empty list if no string appears a 2nd time.
     * <p>
     * Use map to count times that string has appeared.
     *
     * @param words input list to filter
     * @return list of strings matching criteria
     */
    public static List<String> onlyEvenWords(List<String> words) {
        Map<String, Integer> map = new HashMap<>();
        ArrayList<String> evenWords = new ArrayList<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
            if (map.get(word) % 2 == 0) {
                evenWords.add(word);
            }
        }
        return evenWords;
    }

    /**
     * Loop over the given string to build a result string like this:
     * when a character appears the 2nd, 4th, 6th, etc. time in the string, append the character to the result.
     * <p>
     * Return the empty string if no character appears a 2nd time.
     * <p>
     * Use map to count times that character has appeared.
     * Easy way to get char array (char[]) from string: input.toCharArray();
     *
     * @param input string
     * @return string
     */
    public static String onlyEvenCharacters(String input) {
        StringBuilder evenChars = new StringBuilder();
        HashMap<Character, Integer> charmap = new HashMap<>();
        for (char single : input.toCharArray()) {
            charmap.put(single, charmap.getOrDefault(single, 0) + 1);
            if (charmap.get(single) % 2 == 0) {
                evenChars.append(single);
            }
        }
        return evenChars.toString();
    }
}
