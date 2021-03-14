import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class PAT1050 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line1 = br.readLine();
        String line2 = br.readLine();
        boolean[] flag = new boolean[256];
        for (char c : line2.toCharArray()) {
            flag[c] = true;
        }

        StringBuilder strb = new StringBuilder();
        for (char c : line1.toCharArray()) {
            if (flag[c] == true) continue;
            strb.append(c);
        }
        System.out.println(strb);
    }
}
