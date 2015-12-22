import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Route {
    private String[] locations;
    private int totalDistance;

    public Route(String[] locs, HashMap<List<String>, Integer> distances) {
        this.locations = locs;
        int total = 0;
        for (int i=0; i<locations.length-1; i++) {
            List<String> twoLocations = Arrays.asList(locations[i], locations[i + 1]);
            total += distances.get(twoLocations);
        }
        this.totalDistance = total;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public String toString() {
        String out = "";
        for (String s : this.locations) {
            out += s + " -> ";
        }
        out += this.totalDistance;
        return out;
    }
}
