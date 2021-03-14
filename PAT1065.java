import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PAT1065 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> A_list = new ArrayList<>();
        List<String> B_list = new ArrayList<>();
        List<String> C_list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            A_list.add(line[0]);
            B_list.add(line[1]);
            C_list.add(line[2]);
        }
        for (int i = 0; i < N; i++) {
            System.out.println("Case #" + (i+1) + ": " + isLarger(A_list.get(i),B_list.get(i),C_list.get(i)));
        }
    }

    private static boolean isLarger (String A, String B, String C) {
        long A_long = Long.parseLong(A);
        long B_long = Long.parseLong(B);
        long C_long = Long.parseLong(C);

        if (A_long >= 0 && B_long >= 0) {
            int count = 0;
            StringBuilder strbA = new StringBuilder();
            StringBuilder strbB = new StringBuilder();
            if (A.length() > B.length()) {
                for (int i = 0; i < A.length() - B.length(); i++) {
                    strbB.append(0);
                }

            } else if (B.length() > A.length()) {
                for (int i = 0; i < B.length() - A.length(); i++) {
                    strbA.append(0);
                }
            }
            strbA.append(A);
            strbB.append(B);
            StringBuilder strb = new StringBuilder();
            for (int i = A.length() - 1; i >= 0; i--) {
                int a = strbA.charAt(i) - '0';
                int b = strbB.charAt(i) - '0';
                if (a + b + count >= 10) {
                    strb.append((a + b + count) % 10);
                    count = 1;
                } else {
                    strb.append(a + b + count);
                    count = 0;
                }
            }
            if (count == 1) {
                strb.append(1);
            }
            strb.reverse();
            String sum_s = strb.toString();
            if (C_long < 0) {
                return true;
            } else {
                return sum_s.compareTo(C) > 0;
            }
        } else if ((A_long <=0 && B_long >= 0) || (A_long >= 0 && B_long <= 0)) {
            long sum = A_long + B_long;
            if (sum <= 0 && C_long >= 0) return false;
            if (sum >= 0 && C_long < 0) return true;
            else return sum - C_long > 0;
        } else {
            String rev_A = A.substring(1);
            String rev_B = B.substring(1);
            String sum_s = cal_sum(rev_A, rev_B);
            StringBuilder strb = new StringBuilder();
            if (C_long >= 0) return false;
            else {
                String rev_C = C.substring(1);
                return sum_s.compareTo(rev_C) < 0;
            }
        }
    }

    private static String cal_sum (String A, String B) {
        int count = 0;
        StringBuilder strbA = new StringBuilder();
        StringBuilder strbB = new StringBuilder();
        if (A.length() > B.length()) {
            for (int i = 0; i < A.length() - B.length(); i++) {
                strbB.append(0);
            }
            strbA.append(A);
            strbB.append(B);
        }
        if (B.length() > A.length()) {
            for (int i = 0; i < B.length() - A.length(); i++) {
                strbA.append(0);
            }
            strbA.append(A);
            strbB.append(B);
        }
        StringBuilder strb = new StringBuilder();
        for (int i = A.length() - 1; i >= 0; i--) {
            int a = strbA.charAt(i) - '0';
            int b = strbB.charAt(i) - '0';
            if (a + b + count >= 10) {
                strb.append((a + b + count) % 10);
                count = 1;
            } else {
                strb.append(a + b + count);
                count = 0;
            }
        }
        if (count == 1) {
            strb.append(1);
        }
        strb.reverse();
        return strb.toString();
    }
}
