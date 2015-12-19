import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day5 {
    private static char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
    private static String[] BAD_STRINGS = {"ab", "cd", "pq", "xy"};

    public static void main(String[] args) {
        File input = new File("Day5\\src\\day5.txt");
        try {
            Scanner strings = new Scanner(input);
            int numNice = 0;
            while (strings.hasNext()) {
                if (pt2IsNice(strings.next())) numNice++;
            }
        System.out.println(numNice + " nice strings");


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

    }

    public static boolean isNice(String word) {
        boolean doubleChar = false;
        boolean threeVowels = false;
        boolean hasBadStrings = false;
        int numVowels = 0;
        for (int i=0; i<word.length(); i++) {
            if (i !=0 && word.charAt(i) == word.charAt(i-1)) doubleChar = true;
            for (char c  : VOWELS) {
                if (word.charAt(i) == c) numVowels++;
            }
            for (String s : BAD_STRINGS) {
                if (i != 0 && word.substring(i-1, i+1).equals(s)) hasBadStrings = true;
            }
        }
        if (numVowels >= 3) threeVowels = true;
        return doubleChar && threeVowels && !hasBadStrings;
    }

    public static boolean pt2IsNice(String word) {
        boolean twoPairs = false;
        boolean repeating = false;
        for (int i=2; i<word.length(); i++) {
            if (word.charAt(i) == word.charAt(i-2)) repeating = true;
            if (i>2) {
                String currentSub = word.substring(i-1,i+1);
                for (int j=1; j<i-1;j++) {
                    if (currentSub.equals(word.substring(j-1,j+1))) twoPairs = true;
                }
            }
        }

        return repeating && twoPairs;
    }
}
