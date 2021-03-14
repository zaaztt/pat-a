import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PAT1040 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int max = 0;
        for (int i = 0; i < 2 * s.length() - 1; i++) {
            int sum = 0;
            int c1 = i / 2;
            int c2 = i / 2 + i % 2;
            if (c1 == c2) {
                sum = 1;
                c1--;c2++;
                while (c1 >= 0 && c2 < s.length() && s.charAt(c1) == s.charAt(c2)) {
                    c1--;
                    c2++;
                    sum += 2;
                }
            } else {
                while (c1 >= 0 && c2 < s.length() && s.charAt(c1) == s.charAt(c2)) {
                    c1--;
                    c2++;
                    sum += 2;
                }
            }
            if (sum > max) max = sum;
        }
        System.out.println(max);

    }

}

