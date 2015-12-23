import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class Day12 {

    public static void main(String[] args) throws FileNotFoundException{
        File inFile = new File("Day12\\src\\day12.json");
        Scanner inScanner = new Scanner(inFile);
        String in = inScanner.nextLine();

        System.out.println("Sum of numbers in original file: " + findSum(in));

        JSONArray input = new JSONArray(in);
        JSONArray newJSON = removeRedFromArray(input);
        String newJSONString = newJSON.toString();
        System.out.println("Sum of numbers excluding objects with 'red': " + findSum(newJSONString));
    }

    public static JSONArray removeRedFromArray(JSONArray inArray) {
        JSONArray outArray = inArray;
        for (int i=0; i<inArray.length(); i++) {

            if (inArray.get(i) instanceof JSONArray) {
                outArray.put(i, removeRedFromArray(inArray.getJSONArray(i)));

            } else if (inArray.get(i) instanceof JSONObject) {
                if (containsRed(outArray.getJSONObject(i))) {
                    outArray.put(i, 0);
                } else outArray.put(i, removeRedFromObj(outArray.getJSONObject(i)));
            }
        }
        return outArray;
    }

    public static JSONObject removeRedFromObj(JSONObject inObject) {
        JSONObject outObject = inObject;
        for (String key : inObject.keySet()) {

            if (inObject.get(key) instanceof JSONArray) {
                outObject.put(key, removeRedFromArray(inObject.getJSONArray(key)));

            } else if (inObject.get(key) instanceof JSONObject) {
                if (containsRed(outObject.getJSONObject(key))) {
                    outObject.put(key, 0);
                } else outObject.put(key, removeRedFromObj(outObject.getJSONObject(key)));
            }
        }
        return outObject;
    }

    public static int findSum(String in) {
        Pattern findInts = Pattern.compile("-?\\d+");
        Matcher intMatcher = findInts.matcher(in);
        ArrayList<String> allInts = new ArrayList<String>();

        while (intMatcher.find()) {
            allInts.add(intMatcher.group());
        }

        int total = 0;
        for (String s : allInts) {
            total += Integer.parseInt(s);
        }
        return total;
    }

    public static boolean containsRed(JSONObject inObject) {
        for (String key : inObject.keySet()) {
            if (inObject.get(key) instanceof String && ((String)inObject.get(key)).contains("red")) return true;
        }
        return false;
    }

}
