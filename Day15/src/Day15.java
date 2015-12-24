import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day15 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("Day15\\day15.txt"));
        ArrayList<int[]> ingredients = new ArrayList<int[]>();

        while (in.hasNextLine()) {
            String currentLine = in.nextLine();
            String[] line = currentLine.split(" ");
            String name = line[0].toLowerCase().substring(0, line[0].length()-1);
            int[] properties = new int[5];
            for (int i=2; i<line.length; i+=2) {
                if (i==line.length-1) properties[i-1-(i/2)] = Integer.parseInt(line[i]);
                else properties[i-1-(i/2)] = Integer.parseInt(line[i].substring(0, line[i].length()-1));
            }
            ingredients.add(properties);
        }

        // It's brute force but at least it's kind of smart about how brute it is
        int largestScore = 0;
        int num0, num1, num2, num3;
        for (num0 = 0; num0<100; num0++) {
            for (num1=0; num1<100-num0; num1++) {
                for(num2=0; num2<100-num0-num1; num2++) {
                    num3 = 100-num0-num1-num2;
                    int[] currentScore = new int[5];
                    for (int i=0; i<5; i++) {
                        currentScore[i] = num0*ingredients.get(0)[i]
                                         + num1*ingredients.get(1)[i]
                                         + num2*ingredients.get(2)[i]
                                         + num3*ingredients.get(3)[i];
                        if (currentScore[i] < 0) currentScore[i] = 0;
                    }
                    int totalScore = currentScore[0] * currentScore[1] * currentScore[2] * currentScore[3];
                    if (currentScore[4] == 500 && totalScore > largestScore) largestScore = totalScore;
                }
            }
        }

        System.out.println(largestScore);


    }

}

//class Ingredient {
//    String name;
//    private int[] properties;
//    private int score;
//
//    public Ingredient(String name, int[] properties) {
//        this.name =
//    }
//}