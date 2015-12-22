import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Day09 {



    public static void main(String[] args)  throws FileNotFoundException {
        File file = new File("Day09\\src\\day9.txt");
        Scanner fileIn = new Scanner(file);

        HashMap<List<String>, Integer> distances = new HashMap<>();
        HashSet<String> locations = new HashSet<String>();

        while (fileIn.hasNextLine()) {
            String currentLine = fileIn.nextLine();
            String[] lineParts = currentLine.split(" ");

            List<String> forwardLocs = Arrays.asList(lineParts[0], lineParts[2]);
            List<String> backwardLocs = Arrays.asList(lineParts[2], lineParts[0]);

            distances.put(forwardLocs, Integer.parseInt(lineParts[4]));
            distances.put(backwardLocs, Integer.parseInt(lineParts[4]));
            locations.add(lineParts[0]);
            locations.add(lineParts[2]);

        }
        String[] locsArray = locations.toArray(new String[locations.size()]);


        Route test = new Route(locsArray, distances);

        ArrayList<Route> routes = new ArrayList<Route>();
        List<String> locationsList = new ArrayList<String>(locations);
        populateRoutes(distances, routes, locationsList, 0);
        System.out.println("Shortest route: " + getShortest(routes));
        System.out.println("Longest route: " + getLongest(routes));
    }

    static void populateRoutes(HashMap<List<String>,Integer> dist, ArrayList<Route> routeList, List<String> locs, int start) {
        for (int i = start; i < locs.size(); i++) {
            Collections.swap(locs, i, start);
            populateRoutes(dist, routeList, locs, start + 1);
            Collections.swap(locs, start, i);
        }
        if (start == locs.size() - 1) {
            routeList.add(new Route(locs.toArray(new String[locs.size()]), dist));
        }
    }

    static Route getShortest(ArrayList<Route> routes) {
        Route shortest = routes.get(0);
        for (int i=1; i<routes.size(); i++) {
            if (routes.get(i).getTotalDistance() < shortest.getTotalDistance()) {
                shortest = routes.get(i);
            }
        }
        return shortest;
    }

    static Route getLongest(ArrayList<Route> routes) {
        Route longest = routes.get(0);
        for (int i=1; i<routes.size(); i++) {
            if (routes.get(i).getTotalDistance() > longest.getTotalDistance()) {
                longest = routes.get(i);
            }
        }
        return longest;
    }
}