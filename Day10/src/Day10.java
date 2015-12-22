

public class Day10 {


    public static void main(String[] args) {
        StringBuffer in = new StringBuffer("3113322113");
        int counter = 0;
        while (counter < 50) {
            StringBuffer out = new StringBuffer("");
            for (int i=0; i<in.length(); i++) {
                boolean repeating = true;
                int numRepeats = 1;
                char current = in.charAt(i);
                while (repeating) {
                    if (i+numRepeats<in.length() && current == in.charAt(i+numRepeats)) {
                        numRepeats++;
                    } else repeating = false;
                }

//                System.out.println(numRepeats);
//                System.out.println(current);


                out.append(numRepeats);
                out.append(current);
                i += numRepeats-1;
            }

//            System.out.println(in);
//            System.out.println(out);
            in = out;
            counter++;
            System.out.println(counter);
        }

        System.out.println(in.length());
    }
}
