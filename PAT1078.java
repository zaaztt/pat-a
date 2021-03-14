import java.util.Arrays;
import java.util.Scanner;

public class PAT1078 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int MSize = sc.nextInt();
        int N = sc.nextInt();
        while (!isPrime(MSize)) {
            MSize++;
        }
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();
            a[i] = n;
        }
        int[] ht = new int[MSize];
        Arrays.fill(ht, -1);
        StringBuilder strb = new StringBuilder();
        for (int i : a) {
            int loc = i % MSize;
            if (ht[loc] == -1) {
                ht[loc] = i;
                strb.append(loc);
                strb.append(" ");
            } else {
                strb.append("- ");
            }
        }
        strb.deleteCharAt(strb.length() - 1);
        System.out.println(strb);
    }

    private static boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;

    }

}
