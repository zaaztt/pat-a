import java.util.Arrays;
import java.util.Scanner;

public class PAT1152 {
    static boolean[] ba;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt();
        int K = sc.nextInt();
        String s = sc.next();

        ba = new boolean[(int)Math.pow(10, K)];
        Arrays.fill(ba, true);
        setPrime(K);

        for (int start = 0, end = K; end <= L; start++, end++) {
            String temp_s = s.substring(start, end);
            int n = Integer.valueOf(temp_s);
            if (ba[n]) {
                System.out.println(temp_s);
                return;
            }
        }
        System.out.println(404);
    }


    private static void setPrime (int K) {
        ba[0] = false;
        ba[1] = false;

        for (int i = 2; i < (int) Math.pow(10, K); i++) {
            if (ba[i]) {
                for (int j = i + i;  j < (int) Math.pow(10, K); j += i) {
                    ba[j] = false;
                }
            }
        }
    }
}
