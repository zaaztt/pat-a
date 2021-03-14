import java.util.Scanner;

public class PAT1147 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        int[][] a = new int[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                a[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < M; i++) {
            StringBuilder strb = new StringBuilder();
            if (isMaxHeap(a[i])) {
                System.out.println("Max Heap");
                postOrder(a[i], 0, strb);
                strb.deleteCharAt(strb.length() - 1);
                System.out.println(strb);
            } else if (isMinHeap(a[i])) {
                System.out.println("Min Heap");
                postOrder(a[i], 0, strb);
                strb.deleteCharAt(strb.length() - 1);
                System.out.println(strb);
            } else {
                System.out.println("Not Heap");
                postOrder(a[i], 0, strb);
                strb.deleteCharAt(strb.length() - 1);
                System.out.println(strb);
            }
        }
    }

    private static void postOrder (int[] a, int cur, StringBuilder strb) {
        if (cur >= a.length) {
            return;
        }
        postOrder(a, 2 * cur + 1, strb);
        postOrder(a, 2 * cur + 2, strb);
        strb.append(a[cur]).append(' ');
    }

    private static boolean isMaxHeap (int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (2 * i + 1 < a.length) {
                if (a[i] >= a[2 * i + 1]) {
                    continue;
                } else {
                    return false;
                }
            }
            if (2 * i + 2 < a.length) {
                if (a[i] >= a[2 * i + 2]) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isMinHeap (int[] a) {
        for (int i = 0; i < a.length; i++) {
            if (2 * i + 1 < a.length) {
                if (a[i] <= a[2 * i + 1]) {
                    continue;
                } else {
                    return false;
                }
            }
            if (2 * i + 2 < a.length) {
                if (a[i] <= a[2 * i + 2]) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
