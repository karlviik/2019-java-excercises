package ee.taltech.iti0202.testing;

import org.junit.Test;

import static org.junit.Assert.*;

public class SentenceTest {

    @Test
    public void testToString_EmptySentence_IsEmptyString() {
        Sentence sentence = new Sentence();

        String actual = sentence.toString();

        assertEquals("", actual);
    }

//    @Test
//    public void testToString_EmptyString_IsEmptyString() {
//        Sentence sentence = new Sentence("");
//
//        String actual = sentence.toString();
//
//        assertEquals("", actual);
//    }

    @Test
    public void testToString_HasSentence_IsSentence() {
        Sentence sentence = new Sentence("Wasd.");

        String actual = sentence.toString();

        assertEquals("Wasd.", actual);
    }

    @Test
    public void testToString_LowercaseSentence_IsCapitalizedSentence() {
        Sentence sentence = new Sentence("wasd.");

        String actual = sentence.toString();

        assertEquals("Wasd.", actual);
    }

    @Test
    public void testToString_WhitespaceSentence_IsEmptySentence() {
        Sentence sentence = new Sentence("                              ");

        String actual = sentence.toString();

        assertEquals("...", actual);
    }

    @Test
    public void testToString_ExtraWhitespaceSentence_IgnoresExtraWhitespace() {
        Sentence sentence = new Sentence("Hello    there  ");

        String actual = sentence.toString();

        assertEquals("Hello there...", actual);
    }

    @Test
    public void testToString_PunctuationSentence_IgnoresWordsAfterPunctuation() {
        Sentence sentence1 = new Sentence("Hello there. Hellllooooo");
        Sentence sentence2 = new Sentence("Hello there! Hellllooooo");
        Sentence sentence3 = new Sentence("Hello there? Hellllooooo");

        String actual1 = sentence1.toString();
        String actual2 = sentence2.toString();
        String actual3 = sentence3.toString();

        assertEquals("Hello there.", actual1);
        assertEquals("Hello there!", actual2);
        assertEquals("Hello there?", actual3);

    }

    @Test
    public void testToString_MultiplePunctuationsInARowSentence_KeepMultiplePunctuation() {
        Sentence sentence = new Sentence("Hello there...");

        String actual = sentence.toString();

        assertEquals("Hello there...", actual);
    }

    @Test
    public void testToString_MultipleWordSentenceRemoveWord_WordIsRemoved() {
        Sentence sentence = new Sentence("Hello there");

        sentence.removeWord("there");
        String actual = sentence.toString();

        assertEquals("Hello...", actual);
    }

    @Test
    public void testToString_MultipleWordSentenceRemoveWord_FirstWordInstanceIsRemoved() {
        Sentence sentence = new Sentence("Hello there ha there");

        sentence.removeWord("there");
        String actual = sentence.toString();

        assertEquals("Hello ha there...", actual);
    }

    @Test
    public void testToString_MultipleWordSentenceRemoveWord_FirstWordOfSentenceIsRemoved() {
        Sentence sentence = new Sentence("Hello there");

        sentence.removeWord("Hello");
        String actual = sentence.toString();

        assertEquals("There...", actual);
    }

    @Test
    public void testToString_PunctuationSentenceRemoveWord_NoWordRemoved() {
        Sentence sentence = new Sentence("Hello there!");

        sentence.removeWord("there");
        String actual = sentence.toString();

        assertEquals("Hello there!", actual);
    }

}