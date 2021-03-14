import java.util.Arrays;
import java.util.Scanner;

public class PAT1085 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int p = sc.nextInt();
        int[] a = new int[N];
        for (int i = 0 ; i < N; i++) {
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);

        int end = 0;
        int max = 0;
        for (int start = 0; start < a.length; start++) {
            while (end < a.length && p * a[start] >= a[end]) {
                end++;
            }
            max = Math.max(max, end - start);
            if (end == a.length) {
                break;
            }
        }
        System.out.println(max);
    }
}
