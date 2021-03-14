import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1051_2 {
    static int M;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info1 = br.readLine().split(" ");
        M = Integer.parseInt(info1[0]);
        int N = Integer.parseInt(info1[1]);
        int N_lines = Integer.parseInt(info1[2]);
        int[] arr = new int[N];
        for (int i = 1; i <= N; i++) {
            arr[i - 1] = i;
        }
        int[][] test = new int[N_lines][N];
        for (int i = 0; i < N_lines; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                test[i][j] = Integer.parseInt(line[j]);
            }
        }
        for (int i = 0; i < N_lines; i++) {
            if (dfs(arr, test, i)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean dfs (int[] arr, int[][] test, int line) {
        int cnt_test = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            stack.push(i + 1);
            while (!stack.isEmpty() && stack.peek() == test[line][cnt_test]) {
                stack.pop();
                cnt_test++;
            }
            if (stack.size() == M) return false;
        }
        return stack.isEmpty();
    }
}
