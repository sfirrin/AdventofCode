import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.regex.*;

public class Day6 {

    public static void main(String[] args) {
        File input = new File("Day6\\src\\day6.txt");
        try {
            Scanner directions = new Scanner(input);
            boolean[][] grid = new boolean[1000][1000];
            for (boolean[] bArray : grid) {
                for (boolean b : bArray) {
                    b = false;
                }
            }

            while (directions.hasNext()) {
                String nextDirection = directions.nextLine();
                String[] stringDimensions = getDimensions(nextDirection);
                int[] intDimensions = new int[4];
                for (int i=0; i<4; i++) {
                    intDimensions[i] = Integer.parseInt(stringDimensions[i+1]);
                }

                for (int i = intDimensions[0]; i<=intDimensions[2]; i++) {
                    for (int j=intDimensions[1]; j<=intDimensions[3]; j++) {
                        if (stringDimensions[0].equals("turn on")) grid[i][j] = true;
                        else if (stringDimensions[0].equals("turn off")) grid[i][j] = false;
                        else if (stringDimensions[0].equals("toggle")) grid[i][j] = !grid[i][j];
                    }
                }
            }

            int numberLit = 0;
            for (int i = 0; i<1000; i++) {
                for (int j=0; j<1000; j++) {
                    if (grid[i][j]) numberLit++;
                }
            }

            System.out.println("Total number lit: " + numberLit);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static String[] getDimensions(String line) {
        Pattern directionPattern = Pattern.compile("(toggle|turn on|turn off)\\s(\\d+),(\\d+)\\sthrough\\s(\\d+),(\\d+)");
        String[] coordinates = new String[5];
        Matcher directionMatcher = directionPattern.matcher(line);
        directionMatcher.find();
        for (int i=0; i<5; i++) {
            coordinates[i] = directionMatcher.group(i+1);
        }
        return coordinates;
    }

}
