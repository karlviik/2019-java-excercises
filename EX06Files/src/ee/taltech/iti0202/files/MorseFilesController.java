package ee.taltech.iti0202.files;
import ee.taltech.iti0202.files.input.InputFilesBufferReader;
import ee.taltech.iti0202.files.input.InputFilesScanner;
import ee.taltech.iti0202.files.morse.MorseTranslator;
import ee.taltech.iti0202.files.output.OutputFilesWriter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MorseFilesController {

    public static void main(String[] args) {
        InputFilesScanner scanner = new InputFilesScanner();
        List<String> lines = scanner.readTextFromFile("ee/taltech/iti0202/files/morse.txt");

        InputFilesBufferReader bufferReader = new InputFilesBufferReader();
        List<String> lines2 = bufferReader.readTextFromFile("ee/taltech/iti0202/files/morse.txt");

        System.out.println("---------------");

        MorseTranslator translator = new MorseTranslator();
        Map<String, String> codes = translator.addMorseCodes(lines);

        List<String> input = new LinkedList<>();
        input.add("Lorem ipsum dolor sit amet, consectetur adipiscing elit,");
        input.forEach(System.out::println); //your input lines

        List<String> morseLines = translator.translateLinesToMorse(input);
        morseLines.forEach(System.out::println); //your input lines in Morse

        List<String> normalLines = translator.translateLinesFromMorse(morseLines);
        normalLines.forEach(System.out::println); //your input lines in regular text

        OutputFilesWriter writer = new OutputFilesWriter();
        System.out.println(writer.writeLinesToFile(normalLines, "output.txt")); //true
        //This should also create a new file/ write in an existing file
    }
}
