import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PAT1060 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        String num1 = line[1];
        String num2 = line[2];
        boolean flag = true;
        String e_num1 = exchange(num1, N);
        String e_num2 = exchange(num2, N);
        if (e_num1.equals(e_num2)) {
            System.out.println("YES " + e_num1);
        } else {
            System.out.println("NO " + e_num1 + " " + e_num2);
        }
    }

    private static String exchange (String s, int N) {
        StringBuilder strb = new StringBuilder();
        int front_len = s.split("\\.")[0].length();
        if (front_len == 1 && s.split("\\.")[0].charAt(0) == '0') {
            front_len = 0;
        } else {
            strb.append(s.split("\\.")[0]);
        }
        int back_len = 0;
        if (s.split("\\.").length == 2) {
            back_len = s.split("\\.")[1].length();
            strb.append(s.split("\\.")[1]);
        }
        if (N > front_len + back_len) {
            for (int i = 0; i < N - front_len - back_len; i++) {
                strb.append(0);
            }
        }
        StringBuilder res = new StringBuilder();
        res.append("0.");
        for (int i = 0; i < N; i++) {
            res.append(strb.charAt(i));
        }
        res.append("*10^");
        res.append(front_len);
        return res.toString();
    }
}
