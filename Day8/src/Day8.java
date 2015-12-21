import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.*;

public class Day8 {
    private static Pattern escapedChar = Pattern.compile("(\\\\\\\\|\\\\\")");
    private static Pattern escapedHex = Pattern.compile("(\\\\x[0-9a-f]{2})");
    private static Pattern encodedQuote = Pattern.compile("(\")");
    private static Pattern encodedBackslash = Pattern.compile("(\\\\)");

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("Day8\\src\\day8.txt");
        Scanner fileIn = new Scanner(file);
        int totalCodeLength = 0;
        int totalStringLength = 0;
        int totalEncodedLength = 0;

        while (fileIn.hasNext()) {
            String currentLine = fileIn.nextLine();
            int codeLength = currentLine.length();
            int stringLength = codeLength - 2 - numMatches(escapedChar.matcher(currentLine))
                    - 3*numMatches(escapedHex.matcher(currentLine));
            int encodedLength = codeLength + 2 + numMatches(encodedQuote.matcher(currentLine))
                    + numMatches(encodedBackslash.matcher(currentLine));

            System.out.println(currentLine);
            System.out.println("Codelength: " + codeLength);
            System.out.println("Stringlength: " + stringLength);
            System.out.println("Encodedlength: " + encodedLength);
            totalCodeLength += codeLength;
            totalStringLength += stringLength;
            totalEncodedLength += encodedLength;

        }

        System.out.println("Total code length: " + totalCodeLength);
        System.out.println("Total string length: " + totalStringLength);
        System.out.println("Total encoded length: " + totalEncodedLength);
        System.out.println("Code-string length: " + (totalCodeLength-totalStringLength));
        System.out.println("Encoded-code length: " + (totalEncodedLength-totalCodeLength));
    }

    private static int numMatches(Matcher matcher) {
        int i;
        for (i=0; matcher.find(); i++);
        return i;
    }
}
