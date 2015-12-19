import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 {

    public static void main(String[] args) {

        String baseInput = "iwrupvqb";
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            int index = 0;
            String hexSolution;

            while (true) {
                md.update((baseInput + String.valueOf(index)).getBytes());
                byte[] solution = md.digest();
                hexSolution = DatatypeConverter.printHexBinary(solution);
                if (hexSolution.startsWith("000000")) break;
                index++;
            }

            System.out.println(hexSolution + "\n" + index);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No MD5 algorithm");
        }

    }

}
