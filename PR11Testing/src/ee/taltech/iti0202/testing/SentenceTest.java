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

//    @Test
//    public void testToString_WhitespaceSentence_IsEmptySentence() {
//        Sentence sentence = new Sentence("                              ");
//
//        String actual = sentence.toString();
//
//        assertEquals("", actual);
//    }

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
    public void testToString_ShortSentence_HasSentence() {
        Sentence sentence = new Sentence("a");

        String actual = sentence.toString();

        assertEquals("A...", actual);
    }

    @Test
    public void testToString_MultipleWordSentence_KeepUppercase() {
        Sentence sentence = new Sentence("ABcd efGH");

        String actual = sentence.toString();

        assertEquals("ABcd efGH...", actual);
    }

    @Test
    public void testRemoveWord_MultipleWordSentenceRemoveWord_WordIsRemoved() {
        Sentence sentence = new Sentence("Hello there");

        sentence.removeWord("there");
        String actual = sentence.toString();

        assertEquals("Hello...", actual);
    }

    @Test
    public void testRemoveWord_MultipleWordSentenceRemoveWord_FirstWordInstanceIsRemoved() {
        Sentence sentence = new Sentence("Hello there ha there");

        sentence.removeWord("there");
        String actual = sentence.toString();

        assertEquals("Hello ha there...", actual);
    }

    @Test
    public void testRemoveWord_MultipleWordSentenceRemoveWord_FirstWordOfSentenceIsRemoved() {
        Sentence sentence = new Sentence("Hello there");

        sentence.removeWord("Hello");
        String actual = sentence.toString();

        assertEquals("There...", actual);
    }

    @Test
    public void testRemoveWord_PunctuationSentenceRemoveWord_NoWordRemoved() {
        Sentence sentence = new Sentence("Hello there!");

        sentence.removeWord("there");
        String actual = sentence.toString();

        assertEquals("Hello there!", actual);
    }

    @Test
    public void testRemoveWord_MultipleWordPunctuationSentence_WordRemoveReturnFalse() {
        Sentence sentence = new Sentence("Hello there!");

        boolean actual = sentence.removeWord("there");

        assertFalse(actual);
    }

    @Test
    public void testRemoveWord_MultipleWordSentence_WordRemoveReturnTrue() {
        Sentence sentence = new Sentence("Hello there");

        boolean actual = sentence.removeWord("there");

        assertTrue(actual);
    }

    @Test
    public void testAddWord_EmptySentenceAddWord_HasWord() {
        Sentence sentence = new Sentence();

        sentence.addWord("hi");
        String actual = sentence.toString();

        assertEquals("Hi...", actual);
    }

    @Test
    public void testAddWord_NewSentenceAddWord_HasWord() {
        Sentence sentence = new Sentence("Hi there");

        sentence.addWord("heyo");
        String actual = sentence.toString();

        assertEquals("Hi there heyo...", actual);
    }

    @Test
    public void testAddWord_NewPunctuationSentenceAddWord_NoNewWord() {
        Sentence sentence = new Sentence("Hi there.");

        sentence.addWord("heyo");
        String actual = sentence.toString();

        assertEquals("Hi there.", actual);
    }

    @Test
    public void testAddWord_NewPunctuationSentenceAddWord_AddWordReturnFalse() {
        Sentence sentence = new Sentence("Hi there.");

        boolean actual = sentence.addWord("heyo");

        assertFalse(actual);
    }

    @Test
    public void testAddWord_NewSentenceAddWord_AddWordReturnTrue() {
        Sentence sentence = new Sentence("Hi there");

        boolean actual = sentence.addWord("heyo");

        assertTrue(actual);
    }

    @Test
    public void testToString_NewSentenceAddPunctuationWord_HasNewWord() {
        Sentence sentence = new Sentence("Hi there");

        sentence.addWord("?!?!");
        String actual = sentence.toString();

        assertEquals("Hi there ?!?!...", actual);
    }

    @Test
    public void testRemovePunctuation_NewPunctuationSentenceRemovePunctuation_ReturnTrue() {
        Sentence sentence = new Sentence("Hi there.");

        boolean actual = sentence.removePunctuation();

        assertTrue(actual);
    }

    @Test
    public void testRemovePunctuation_NewNoPunctuationSentenceRemovePunctuation_ReturnFalse() {
        Sentence sentence = new Sentence("Hi there");

        boolean actual = sentence.removePunctuation();

        assertFalse(actual);
    }

    @Test
    public void testRemovePunctuation_NewNoPunctuationSentenceWithPunctuationWordRemovePunctuation_ReturnFalse() {
        Sentence sentence = new Sentence("Hi there");

        sentence.addWord("........");
        boolean actual = sentence.removePunctuation();

        assertFalse(actual);
    }

    @Test
    public void testAddPunctuation_NewNoPunctuationSentenceAddPunctuation_ReturnTrue() {
        Sentence sentence = new Sentence("Hi there");

        boolean actual = sentence.addPunctuation("!");

        assertTrue(actual);
    }

    @Test
    public void testAddPunctuation_EmptySentenceAddPunctuation_ReturnFalse() {
        Sentence sentence = new Sentence();

        boolean actual = sentence.addPunctuation("!");

        assertFalse(actual);
    }

    @Test
    public void testAddPunctuation_NewSentenceWithPunctuationAddAnotherPunctuation_ReturnFalse() {
        Sentence sentence = new Sentence("haha.");

        boolean actual = sentence.addPunctuation("!");

        assertFalse(actual);
    }

    @Test
    public void testAddPunctuation_EmptySentenceAddPunctuation_NoPunctuation() {
        Sentence sentence = new Sentence();

        sentence.addPunctuation("!");
        String actual = sentence.toString();

        assertEquals("", actual);
    }

    @Test
    public void testAddPunctuation_NewPunctuationSentenceAddPunctuation_NoExtraPunctuation() {
        Sentence sentence = new Sentence("Heyo.");

        sentence.addPunctuation("!");
        String actual = sentence.toString();

        assertEquals("Heyo.", actual);
    }

    @Test
    public void testAddPunctuation_NewSentenceAddPunctuation_hasPunctuation() {
        Sentence sentence = new Sentence("Heyo");

        sentence.addPunctuation("!");
        String actual = sentence.toString();

        assertEquals("Heyo!", actual);
    }

    @Test
    public void testAddPunctuation_NewSentenceAddWordPunctuation_hasPunctuation() {
        Sentence sentence = new Sentence("Heyo");

        sentence.addPunctuation("haha");
        String actual = sentence.toString();

        assertEquals("Heyohaha", actual);
    }

    @Test
    public void testRemovePunctuation_NewSentenceAddWordPunctuationAndRemoveIt_hasNoPunctuation() {
        Sentence sentence = new Sentence("Heyo");

        sentence.addPunctuation("haha");
        sentence.removePunctuation();
        String actual = sentence.toString();

        assertEquals("Heyo...", actual);
    }

}