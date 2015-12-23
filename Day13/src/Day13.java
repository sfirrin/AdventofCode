import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Day13 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("Day13\\day13.txt"));
        HashSet<String> people = new HashSet<String>();
        HashMap<List<String>, Integer> relations = new HashMap<>();

        while (input.hasNextLine()) {
            String current = input.nextLine();
            current = current.substring(0, current.length()-1);
            String[] words = current.split(" ");
            people.add(words[0]);
            people.add(words[words.length-1]);

            int sign;
            if (words[2].equals("lose")) sign = -1;
            else sign = 1;

            List<String> pair = Arrays.asList(words[0], words[words.length-1]);
            relations.put(pair, sign*Integer.parseInt(words[3]));
        }


        List<String> peopleList = new ArrayList<String>(people);

        // Part 2 code follows
        for (String person : peopleList) {
            List<String> forward = Arrays.asList("Stephen", person);
            List<String> backward = Arrays.asList(person, "Stephen");
            relations.put(forward, 0);
            relations.put(backward, 0);
        }
        peopleList.add("Stephen");
        // End of part 2 code

        ArrayList<Arrangement> arrangements = new ArrayList<Arrangement>();

        populateArrangementList(arrangements, relations, peopleList, 0);

        System.out.println(getHappiest(arrangements).getHappiness());
    }

    public static void populateArrangementList(ArrayList<Arrangement> arrangementList,
                                               HashMap<List<String>, Integer> relations,
                                               List<String> diners,
                                               int start)
    {
        for (int i=start; i<diners.size(); i++) {
            Collections.swap(diners, i, start);
            populateArrangementList(arrangementList, relations, diners, start+1);
            Collections.swap(diners, start, i);
        }
        if (start == diners.size()-1) {
            arrangementList.add(new Arrangement(diners.toArray(new String[diners.size()]), relations));
        }
    }

    public static Arrangement getHappiest(ArrayList<Arrangement> arrangements) {
        Arrangement happiest = arrangements.get(0);
        for (int i=1; i<arrangements.size(); i++) {
            if (arrangements.get(i).getHappiness() > happiest.getHappiness()) happiest = arrangements.get(i);
        }
        return happiest;
    }

}

class Arrangement {
    private String[] seats;
    private int happiness;

    public Arrangement(String[] seats, HashMap<List<String>, Integer> relations) {
        this.seats = seats;
        this.happiness = findHappiness(seats, relations);
    }

    public static int findHappiness(String[] seats, HashMap<List<String>, Integer> relations) {
        int total = 0;
        for (int i=0; i<seats.length; i++) {
            String current = seats[i];
            String right;
            String left;

            if (i==0) left = seats[seats.length-1];
            else left = seats[i-1];

            if (i==seats.length-1) right = seats[0];
            else right = seats[i+1];

            total += relations.get(Arrays.asList(current, left)) + relations.get(Arrays.asList(current, right));
        }
        return total;
    }

    public int getHappiness() {
        return this.happiness;
    }
}