import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PAT1073 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();
        String[] spl = num.split("E");
        String f_pn = num.substring(0, 1);
        String f_num = spl[0].substring(1);
        String b_pn = spl[1].substring(0, 1);
        String b_num = spl[1].substring(1);
        StringBuilder strb = new StringBuilder();
        if (b_pn.equals("-")) {
            if (f_pn.equals("+")) {

            } else {
                strb.append(f_pn);
            }
            for (int i = 0; i < Integer.parseInt(b_num); i++) {
                strb.append(0);
                if (i == 0) {
                    strb.append(".");
                }
            }
            for (char c : f_num.toCharArray()) {
                if (c == '.') {
                    continue;
                } else {
                    strb.append(c);
                }
            }
        } else {
            if (f_pn.equals("+")) {

            } else {
                strb.append(f_pn);
            }
            int N_ten = Integer.parseInt(b_num);
            if (N_ten >= f_num.length() - 2) {
                for (char c : f_num.toCharArray()) {
                    if (c == '.') {
                        continue;
                    } else {
                        strb.append(c);
                    }
                }
                for (int i = 0; i < (N_ten - f_num.length() + 2); i++) {
                    strb.append(0);
                }
            } else {
                int temp = 0;
                for (int i = 0; i < f_num.length(); i++) {
                    if (f_num.charAt(i) == '.') {
                        temp++;
                        continue;
                    }
                    strb.append(f_num.charAt(i));
                    temp++;
                    if (temp - 2 == N_ten) {
                        strb.append(".");
                    }
                }
            }
        }
        System.out.println(strb);
    }
}
