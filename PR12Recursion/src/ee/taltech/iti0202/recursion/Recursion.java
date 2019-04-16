package ee.taltech.iti0202.recursion;

import java.util.ArrayList;
import java.util.List;

public class Recursion {
  private final static ArrayList<Character> ALLOWED_LETTERS = new ArrayList<>(List.of('a', 'e', 'i', 'o', 'u', 'h', 'k', 'l', 'm', 'n', 'p', 'w', 'r', '\'', ' ', 'ā', 'ō', 'ū', '.', ',', '!', '?'));


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

//    System.out.println(parentheses("I am useless text(Find me), yet again useless")); // "(Find me)"
//    System.out.println(parentheses("a()")); // ""
//    System.out.println(parentheses("What do you do if (sentence has (many parentheses) and where it ends)")); // "(sentencce has (many parentheses) and where it ends)"
//    System.out.println();

    System.out.println(pidginfy("af")); // "abcdefg"
//    System.out.println(removeDuplicates("foakfjdirmdogmvooasf")); // "foakfjdirmdogmvoasf"
//    System.out.println(removeDuplicates("ilIliiiiilIili1lilllliiilil1ilili111111lili1")); // "ilIlilIili1lililil1ilili1lili1"
//    System.out.println();
//
//    System.out.println(pidginfy("Kūle'a ka'ōpopo'ōpiopio ma luna o ka'īlio palaualelo.")); // "Kūle'a ka'ōpopo'ōpiopio ma luna o ka'lio palaualelo."
//    System.out.println(pidginfy("kasmdfastu naidsfnasidn weraiskdfm sdfasdf''assdffaksndfasdf")); // "kamau nainain weraikm a''aakna"
//    System.out.println(pidginfy("He nani ka'iliahi, akā,'a'ohe mea'ala, no ka mea he mea'alala'i ka raiki, pono nō ka'ohe."));
    //"He nani ka'iliahi, akā,'a'ohe mea'ala, no ka mea he mea'alala'i ka raiki, pono nō ka'ohe."

  }
}
