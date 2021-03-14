import java.util.Scanner;

public class PAT1093 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int len = s.length();
        int[] right_T = new int[len];
        int[] left_A = new int[len];
        int count_T = 0;
        int count_P = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == 'T') {
                count_T++;
            }
            if (s.charAt(i) == 'A') {
                right_T[i] = count_T;
            }
        }
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == 'P') {
                count_P++;
            }
            if (s.charAt(i) == 'A') {
                left_A[i] = count_P;
            }
        }
        long sum = 0;
        for (int i = 0; i < len; i++) {
            if (left_A[i] > 0 && right_T[i] > 0) {
                sum += left_A[i] * right_T[i];
                sum %= 1000000007;
            }
        }
        System.out.println(sum);
    }
}
