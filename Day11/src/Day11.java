public class Day11 {

    public static void main(String[] args) {
        StringBuffer input = new StringBuffer("hxbxwxba");
        StringBuffer output = input;

        while (!isValid(output)) {
            output = increment(output);
        }

        System.out.println("The first new valid password: " + output);

        do {
            output = increment(output);
        } while (!isValid(output));

        System.out.println("The second new valid password: " + output);
    }

    public static StringBuffer increment(StringBuffer in) {
        StringBuffer out = in;
        boolean incrementing = true;
        int index = out.length()-1;
        while (incrementing) {
            if (out.charAt(index) == 'z') {
                out.replace(index, index+1, "a");
                index--;
            } else {
                int valueOfChar = out.charAt(index);
                String nextChar = String.valueOf((char)(valueOfChar+1));
                out.replace(index, index+1, nextChar);
                incrementing = false;
            }
        }

        return out;
    }

    public static boolean isValid(StringBuffer pass) {
        boolean noIOL = true;
        boolean straight = false;
        boolean twoDoubles = false;

        int lastDouble = -1;
        int numDoubles = 0;

        for (int i=0; i<pass.length(); i++) {
            if (pass.charAt(i) == 'i' || pass.charAt(i) == 'o' || pass.charAt(i) == 'l') noIOL = false;
            if (i>0 && pass.charAt(i) == pass.charAt(i-1) && lastDouble != i-1) {
                numDoubles++;
                lastDouble = i;
            }
            if (i>1 && (int)pass.charAt(i) == ((int)pass.charAt(i-1)+1)
                    && (int)pass.charAt(i-1) == ((int)pass.charAt(i-2)+1)) {
                straight = true;
            }
        }

        if (numDoubles >= 2) twoDoubles = true;

        return noIOL && straight && twoDoubles;
    }
}
