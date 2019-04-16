package ee.taltech.iti0202.recursion;

import java.util.ArrayList;
import java.util.List;

public class Recursion {
  private final static ArrayList<Character> ALLOWED_LETTERS = new ArrayList<>(
      List.of('a', 'e', 'i', 'o', 'u', 'h', 'k', 'l', 'm', 'n', 'p', 'w', 'r', '\'', ' ', 'ā', 'ō', 'ū', '.', ',', '!', '?')
  );


  /**
   * Find the text between the first and last parenthesis.
   *
   * @param word a random word
   * @return content between first and last parenthesis
   */
  public static String parentheses(String word) {
    if (word.length() <= 1) {
      return "";
    }
    Character firstCharacter = word.charAt(0);
    Character lastCharacter = word.charAt(word.length() - 1);
    int firstIndex = firstCharacter.equals('(') ? 0 : 1;
    int lastIndex = lastCharacter.equals(')') ? word.length() : word.length() - 1;
//    if (lastIndex - firstIndex <= 1) {
//      return "";
//    } else
    if (firstIndex == 0 && lastIndex == word.length()) {
      return word;
    }
    return parentheses(word.substring(firstIndex, lastIndex));
  }

  /**
   * Remove every neighbouring duplicate char in the string recursively.
   *
   * @param word a word with duplicates
   * @return a word without any duplicates
   */
  public static String removeDuplicates(String word) {
    if (word.length() <= 1) {
      return word;
    }
    Character firstCharacter = word.charAt(0);
    if (Character.valueOf(word.charAt(1)).equals(firstCharacter)) {
      return removeDuplicates(word.substring(1));
    } else {
      return firstCharacter + removeDuplicates(word.substring(1));
    }
  }

  /**
   * Remove any char that isn't in the Pseudo Hawaiian pidgin language.
   *
   * @param word a word that may contain other chars
   * @return a word where only Hawaiian pidgin chars exist
   */
  public static String pidginfy(String word) {
    if (word.length() == 0) {
      return word;
    }
    Character firstCharacter = word.charAt(0);
    if (!ALLOWED_LETTERS.contains(Character.toLowerCase(firstCharacter))) {
      return pidginfy(word.substring(1));
    } else {
      return firstCharacter + pidginfy(word.substring(1));
    }
  }

  public static void main(String[] args) {
    System.out.println(" ");
  }
}
