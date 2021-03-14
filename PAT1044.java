import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PAT1044 {
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int N = Integer.parseInt(info[0]);
        int M = Integer.parseInt(info[1]);
        String[] spl = br.readLine().split(" ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
             arr[i] = Integer.parseInt(spl[i]);
        }
        int target = 0;
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += arr[j];
                if (sum == M) {
                    target = M;
                    break;
                }
                if (sum > M) {
                    temp.add(sum);
                    break;
                }
            }
            if (target != 0) {
                break;
            }
        }
        if (target == 0) {
            Collections.sort(temp);
            target = temp.get(0);
        }

        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += arr[j];
                if (sum == target) {
                    System.out.println((i + 1) + "-" + (j + 1));
                    break;
                }
                if (sum > target) continue;
            }
        }
    }


    private static int sum_i (int[] arr, int i, int j) {
        int sum = 0;
        for (int t = i; t < j; t++) {
            sum += arr[t];
        }
        return sum;
    }
}
