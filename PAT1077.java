import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class PAT1077 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();
        String longest = "";
        String[] lines = new String[N];
        int minLen = 1000000;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            StringBuilder strb = new StringBuilder(line);
            strb.reverse();
            lines[i] = strb.toString();
            minLen = Math.min(minLen, strb.length());
        }
        for (int i = 0; i < minLen; i++) {
            char tar = lines[0].charAt(i);
            boolean flag = true;
            for (String s : lines) {
                if (s.charAt(i) != tar) {
                    flag = false;
                    break;
                }
            }
            if (flag == false) {
                break;
            } else {
                longest += tar;
            }
        }
        if (longest.length() == 0) {
            System.out.println("nai");
        } else {
            System.out.println(new StringBuilder(longest).reverse());
        }
    }
}
