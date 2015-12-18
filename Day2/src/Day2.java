import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.*;

public class Day2 {
    public static void main(String[] args) {
        File input = new File("C:\\Users\\steve\\IdeaProjects\\AdventOfCode\\Day2\\src\\day2-in.txt");
        int totalPaper = 0;
        int totalRibbon = 0;
        try {
            Scanner inFile = new Scanner(input);
            while (inFile.hasNext()) {
                String currentLine = inFile.nextLine();
                String[] dimensions = currentLine.split("x");
                int l = Integer.parseInt(dimensions[0]);
                int w = Integer.parseInt(dimensions[1]);
                int h = Integer.parseInt(dimensions[2]);
                int[] dimensionsInt = {l, w, h};
                Arrays.sort(dimensionsInt);
                int slack = dimensionsInt[0]*dimensionsInt[1];
//              System.out.println("l " + l + " w " + w + " h " + h + " slack " + slack);
                totalPaper += 2*l*w + 2*w*h + 2*h*l + slack;
                totalRibbon += 2*dimensionsInt[0] + 2*dimensionsInt[1] + l*w*h;

            }

            System.out.println("Total paper " + totalPaper);
            System.out.println("Total ribbon " + totalRibbon);



        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
