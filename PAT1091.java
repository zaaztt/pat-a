import java.util.Scanner;

public class PAT1091 {
    static boolean[][][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt(); // row
        int N = sc.nextInt(); // col
        int L = sc.nextInt(); // height
        int T = sc.nextInt();
        int[][][] a = new int[M][N][L];
        visited = new boolean[M][N][L];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    a[j][k][i] = sc.nextInt();
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    int temp = dfs(a, j, k, i);
                    if (temp >= T) {
                        sum += temp;
                    }
                }
            }
        }
        System.out.println(sum);

    }

    private static int dfs (int[][][] a, int row, int col, int ht) {
        if (!inbound(a, row, col, ht) || a[row][col][ht] == 0 || visited[row][col][ht]) {
            return 0;
        } else {
            visited[row][col][ht] = true;
            int x_up = dfs(a, row + 1, col, ht);
            int x_down = dfs(a, row - 1, col, ht);
            int y_up = dfs(a, row, col + 1, ht);
            int y_down = dfs(a, row, col - 1, ht);
            int z_up = dfs(a, row, col, ht + 1);
            int z_down = dfs(a, row, col, ht - 1);
            return 1 + x_up + x_down + y_down + y_up + z_down + z_up;
        }
    }

    private static boolean inbound (int[][][] a, int row, int col, int ht) {
        if (row < 0 || row >= a.length) {
            return false;
        }
        if (col < 0 || col >= a[0].length) {
            return false;
        }
        if (ht < 0 || ht >= a[0][0].length) {
            return false;
        }
        return true;
    }
}
