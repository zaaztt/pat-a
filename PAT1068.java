import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PAT1068 {
    public static void main (String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int target = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        List<Integer> list =traceback(arr, target, 0,0, new ArrayList<>());
        if (list == null) {
            System.out.println("No Solution");
        } else {
            StringBuilder strb = new StringBuilder();
            for (int i : list) {
                strb.append(i);
                strb.append(" ");
            }
            strb.deleteCharAt(strb.length() - 1);
            System.out.println(strb);
        }
    }

    private static List<Integer> traceback (int[] arr, int target, int n, int sum, List<Integer> temp) {
        if (sum == target) {
            return new ArrayList<>(temp);
        }
        if (sum > target || n == arr.length) {
            return null;
        }
        temp.add(arr[n]);
        sum += arr[n];
        List<Integer> temp2 = traceback(arr, target, n + 1, sum, temp);
        if (temp2 != null) {
            return temp2;
        }
        temp.remove(temp.size() - 1);
        sum -= arr[n];
        temp2 = traceback(arr, target, n + 1, sum, temp);
        if (temp2 != null) {
            return temp2;
        }
        return null;
    }
}
