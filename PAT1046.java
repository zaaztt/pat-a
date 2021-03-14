import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PAT1046 {
    static List<Integer> ans = new ArrayList<>();

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info1 = br.readLine().split(" ");
        int[] arr = new int[Integer.parseInt(info1[0])];
        int sum = 0;
        for (int i = 1; i <= Integer.parseInt(info1[0]); i++) {
            arr[i - 1] = Integer.parseInt(info1[i]);
            sum += arr[i - 1];
        }
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] spl = br.readLine().split(" ");
            int start = Integer.parseInt(spl[0]) - 1;
            int end = Integer.parseInt(spl[1]) - 1;
            ans.add(distance(arr, start, end, sum));
        }

        for (int i : ans) {
            System.out.println(i);
        }

    }

    private static int distance (int[] arr, int start, int end, int sum) {
        int temp = start;
        int temp_sum = 0;
        while (temp != end) {
            temp_sum += arr[temp];
            temp++;
            temp = temp % arr.length;
        }
        return Math.min(temp_sum, sum - temp_sum);
    }
}
