package ee.taltech.iti0202.files.morse;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MorseTranslator {
    private HashMap<String, String> alphabetToMorse;
    private HashMap<String, String> alphabetFromMorse;

    public Map<String, String> addMorseCodes(List<String> lines) {
        HashMap<String, String> alphabetToMorse = new HashMap<>();
        HashMap<String, String> alphabetFromMorse = new HashMap<>();
        for (String line : lines) {
            line = line.replace("\n", "");
            String[] relation = line.split("\\s");
            alphabetToMorse.put(relation[0], relation[1]);
            alphabetFromMorse.put(relation[1], relation[0]);
        }
        this.alphabetToMorse = alphabetToMorse;
        this.alphabetFromMorse = alphabetFromMorse;
        return alphabetToMorse;
    }

    public List<String> translateLinesToMorse(List<String> lines) {
        LinkedList<String> translatedLines = new LinkedList<>();
        for (String line : lines) {
            translatedLines.add(translateLineToMorse(line.toLowerCase()));
        }
        return translatedLines;
    }

    public List<String> translateLinesFromMorse(List<String> lines) {
        LinkedList<String> translatedLines = new LinkedList<>();
        for (String line : lines) {
            translatedLines.add(translateLineFromMorse(line));
        }
        return translatedLines;
    }

    private String translateLineToMorse(String line) {
        LinkedList<String> translatedWords = new LinkedList<>();
        String[] words = line.split("\\s");
        for (String word : words) {
            LinkedList<String> translatedWord = new LinkedList<>();
            for (Character letter : word.toCharArray()) {
                translatedWord.add(alphabetToMorse.get(letter.toString()));
            }
            translatedWords.add(String.join(" ", translatedWord));
        }
        return String.join("\t", translatedWords);
    }

    private String translateLineFromMorse(String line) {
        LinkedList<String> translatedWords = new LinkedList<>();
        String[] words = line.split("\\t");
        for (String word : words) {
            LinkedList<String> translatedWord = new LinkedList<>();
            String[] letters = word.split("\\s");
            for (String letter : letters) {
                translatedWord.add(alphabetFromMorse.get(letter));
            }
            translatedWords.add(String.join("", translatedWord));
        }
        return String.join(" ", translatedWords);
    }
}
