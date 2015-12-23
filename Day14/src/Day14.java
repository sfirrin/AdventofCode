import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Day14 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("Day14\\day14.txt"));
        ArrayList<Reindeer> corral = new ArrayList<Reindeer>();
        while (in.hasNextLine()) {
            String currentLine = in.nextLine();
            String[] line = currentLine.split(" ");
            corral.add(new Reindeer(line[0], Integer.parseInt(line[3]), Integer.parseInt(line[6]),
                    Integer.parseInt(line[13])));
        }

        int seconds = 0;
        while (seconds < 2503) {
            corral.get(0).advanceSecond();
            int largest = 0;
            for (int i=1; i<corral.size(); i++) {
                corral.get(i).advanceSecond();
                if (corral.get(i).getPosition()>corral.get(largest).getPosition()) largest = i;
            }
            ArrayList<Integer> largestList = new ArrayList<Integer>();
            largestList.add(largest);
            for (int i=0; i<corral.size(); i++) {
                if (i != largest && corral.get(i).getPosition() == corral.get(largest).getPosition()) {
                    largestList.add(i);
                }
            }
            for (int index : largestList) {
                corral.get(index).addPoint();
            }
            seconds++;
        }

        Reindeer farthest = corral.get(0);
        Reindeer mostPoints = corral.get(0);
        for (Reindeer r : corral) {
            if (r.getPosition() > farthest.getPosition()) farthest = r;
            if (r.getPoints() > mostPoints.getPoints()) mostPoints = r;
        }

        System.out.println("Greatest position: " + farthest.getPosition());
        System.out.println("Most points: " + mostPoints.getPoints());
    }

}


class Reindeer {
    private String name;
    private int speed, flyTime, restTime, timeSpent, position, points;
    private boolean flying;


    public Reindeer(String name, int speed, int flyTime, int restTime) {
        this.name = name;
        this.speed = speed;
        this.flyTime = flyTime;
        this.restTime = restTime;
        flying = true;
        timeSpent = 0;
        position = 0;
    }

    public void advanceSecond() {
        timeSpent++;
        if (!flying && timeSpent >= restTime) {
            flying = true;
            timeSpent = 0;
        } else if (flying) {
            position += speed;
            if (timeSpent >= flyTime) {
                flying = false;
                timeSpent = 0;
            }
        }
    }

    public int getPosition() {
        return this.position;
    }

    public void addPoint() {
        points++;
    }

    public int getPoints() {
        return this.points;
    }

}
