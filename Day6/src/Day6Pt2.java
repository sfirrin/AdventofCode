import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.regex.*;

public class Day6Pt2 {

    public static void main(String[] args) {
        File input = new File("Day6\\src\\day6.txt");
        try {
            Scanner directions = new Scanner(input);
            int[][] grid = new int[1000][1000];
            for (int[] iArray : grid) {
                for (int i : iArray) {
                    i = 0;
                }
            }

            while (directions.hasNext()) {
                String nextDirection = directions.nextLine();
                String[] stringDimensions = Day6.getDimensions(nextDirection);
                int[] intDimensions = new int[4];
                for (int i=0; i<4; i++) {
                    intDimensions[i] = Integer.parseInt(stringDimensions[i+1]);
                }

                for (int i = intDimensions[0]; i<=intDimensions[2]; i++) {
                    for (int j=intDimensions[1]; j<=intDimensions[3]; j++) {
                        if (stringDimensions[0].equals("turn on")) grid[i][j]++;
                        else if (grid[i][j]>0 && stringDimensions[0].equals("turn off")) grid[i][j]--;
                        else if (stringDimensions[0].equals("toggle")) grid[i][j] += 2;
                    }
                }
            }

            int totalBrightness = 0;
            for (int i = 0; i<1000; i++) {
                for (int j=0; j<1000; j++) {
                    totalBrightness += grid[i][j];
                }
            }

            System.out.println("Total brightness: " + totalBrightness);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
