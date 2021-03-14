import java.util.Arrays;
import java.util.Scanner;

public class PAT1085_2 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int p = sc.nextInt();
        int[] a = new int[N];
        for (int i = 0 ; i < N; i++) {
            a[i] = sc.nextInt();
        }
        Arrays.sort(a);

        int max = 0;
        for (int start = 0; start < a.length; start++) {
            int bound = p * a[start];
            int left = start;
            int right = a.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (a[mid] > bound) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            max = Math.max(max, left - start);
            if (left == a.length) {
                break;
            }
        }
        System.out.println(max);
    }
}
