import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class PAT1067_3 {
    public static void main (String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            int num = sc.nextInt();
            arr[num] = i;
        }
        int sum = 0;
        for (int i = 0; i < N; i++) {
            while (arr[0] != 0) {
                swap(arr, 0, arr[0]);
                sum++;
            }
            if (arr[i] != i) {
                swap(arr, 0, i);
                sum++;
            }
        }
        System.out.println(sum);

    }

    private static void swap (int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
