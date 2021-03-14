import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class PAT1067_2 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        int N = Integer.parseInt(line[0]);
        int[] arr = new int[N];
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(line[i]);
            arr[num] = i - 1;
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
