import java.util.*;

public class PAT1101 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = sc.nextInt();
        }
        int max_left = a[0];
        int min_right = a[N - 1];
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        right[N - 1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            if (min_right > a[i]) {
                right[i] = 1;
                min_right = a[i];
            }
        }
        StringBuilder strb = new StringBuilder();
        int count = 0;
        if (right[0] == 1) {
            strb.append(a[0]).append(" ");
            count++;
        }

        for (int i = 1; i < N; i++) {
            if (max_left < a[i]) {
                left[i] = 1;
                max_left = a[i];
                if (right[i] == 1) {
                    strb.append(a[i]).append(" ");
                    count++;
                }
            }
        }

        System.out.println(count);
        strb.deleteCharAt(strb.length() - 1);
        System.out.println(strb);

    }
}
