package ee.taltech.iti0202.sentence;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Sentence class represent words and punctuation.
 */
public class Sentence {
    private String punctuation = "";
    private boolean hasPunctuation = false;
    private ArrayList<String> sentence = new ArrayList<>();
    private static final char[] DEFAULT_PUNCTUATION = new char[] {'.', '?', '!'};
    private static final String DEFAULT_NO_PUNCTUATION = "...";

    /**
     * Given string is treated as possible sentence.
     *
     * Ignore all duplicate whitespaces.
     * If a word ends with ".", "!" or "?" treat it as punctuation.
     * No words can follow after punctuation - just ignore those.
     * @param text Sentence as string
     */
    public Sentence(String text) {
        String[] words = text.trim().split("\\s+");
        for (String word : words) {
            Character lastChar = word.charAt(word.length() - 1);
            for (Character punctuationOption : DEFAULT_PUNCTUATION) {
                if (lastChar.equals(punctuationOption)) {
                    punctuation = punctuationOption.toString();
                    hasPunctuation = true;
                    break;
                }
            }
            if (!hasPunctuation) sentence.add(word);
            else {
                sentence.add(word.substring(0, word.length() - 1));
                break;
            }
        }
    }

    public Sentence() {

    }

    /**
     * Removes the first occurrence of the specified word from this sentence, if it is present.
     * If the word is not in the sentence, returns false.
     * If the sentence already has punctuation, returns false (nothing is removed).
     * Otherwise removes the word and returns true.
     * @param word Word to be removed.
     * @return Whether word was in the sentence and removed.
     */
    public boolean removeWord(String word) {
        if (hasPunctuation) return false;
        return sentence.remove(word);
    }

    /**
     * Adds word to sentence. The word can be any non-empty string without spaces.
     * If the sentence has punctuation added (either by the string in constructor
     * or by addPunctuation method), method return false and word is not added.
     * Otherwise the methods returns true and word is added to sentence.
     * @param word Non-empty string without spaces.
     * @return Whether word was added to sentence (false if sentence has punctuation).
     */
    public boolean addWord(String word) {
        if (hasPunctuation) return false;
        sentence.add(word);
        return true;
    }

    /**
     * Adds punctuation to the sentence.
     *
     * The sentence can have only one punctuation. When trying to add second, method should return false.
     * If there are no words in the sentence, punctuation cannot be added.
     * If punctuation is added, no more words can be added.
     * @param newPunctuation Punctuation string (e.g. "!")
     * @return Whether punctuation was added (false if sentence already had punctuation).
     */
    public boolean addPunctuation(String newPunctuation) {
        if (hasPunctuation || sentence.isEmpty()) return false;
        punctuation = newPunctuation;
        hasPunctuation = true;
        return true;
    }

    /**
     * Removes punctuation.
     *
     * If punctuation is not yet added, the method returns false.
     * If punctuation has been added, it is removed.
     * After removing the punctuation, words can be added.
     * @return Whether punctuation was removed (false if there was no punctuation).
     */
    public boolean removePunctuation() {
        if (!hasPunctuation) return false;
        punctuation = "";
        hasPunctuation = false;
        return true;
    }

    private void firstToUpper() {
        String firstWord = sentence.get(0);
        if (firstWord.length() == 1) firstWord = firstWord.toUpperCase();
        else firstWord = firstWord.substring(0, 1).toUpperCase() + firstWord.substring(1);
        sentence.set(0, firstWord);
    }

    @Override
    public String toString() {
        if (sentence.isEmpty()) return "";
        firstToUpper();
        String[] sentenceToArray = sentence.toArray(new String[0]);
        String sentenceString = String.join(" ", sentenceToArray);
        if (hasPunctuation) sentenceString += punctuation;
        else sentenceString += DEFAULT_NO_PUNCTUATION;
        return sentenceString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sentence)) return false;
        Sentence sentence1 = (Sentence) o;
        firstToUpper();
        ((Sentence) o).firstToUpper();
        return hasPunctuation == sentence1.hasPunctuation
                && punctuation.equals(sentence1.punctuation)
                && sentence.toString().equals(sentence1.sentence.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(punctuation, hasPunctuation, sentence.toString());
    }
}

