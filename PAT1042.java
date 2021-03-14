import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PAT1042 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] spl = br.readLine().split(" ");
        int[] model = new int[spl.length];
        for (int i = 0; i < spl.length; i++) {
            model[i] = Integer.parseInt(spl[i]);
        }
        int[] ans = new int[54];
        for (int i = 1; i <= 54; i++) {
            ans[i - 1] = i;
        }
        for (int i = 0; i < n; i++) {
            int[] temp = new int[spl.length];
            for (int j = 0; j < spl.length; j++) {
                int loc = model[j];
                temp[loc - 1] = ans[j];
            }
            ans = temp;
        }
        StringBuilder strb = new StringBuilder();
        for (int i : ans) {
            strb.append(exchange(i));
            strb.append(" ");
        }
        strb.deleteCharAt(strb.length() - 1);
        System.out.println(strb);
    }

    public static String exchange (int i) {
        if ((i - 1) / 13 == 0) {
            return "S" + String.valueOf((i - 1) % 13 + 1);
        }
        if ((i - 1) / 13 == 1) {
            return "H" + String.valueOf((i - 1) % 13 + 1);
        }
        if ((i - 1) / 13 == 2) {
            return "C" + String.valueOf((i - 1) % 13 + 1);
        }
        if ((i - 1) / 13 == 3) {
            return "D" + String.valueOf((i - 1) % 13 + 1);
        }
        if ((i - 1) / 13 == 4) {
            return "J" + String.valueOf((i - 1) % 13 + 1);
        }
        return null;
    }
}
