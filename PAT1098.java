import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PAT1098 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] original = new int[N];
        int[] test = new int[N];
        for (int i = 0; i < N; i++) {
            original[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            test[i] = sc.nextInt();
        }
        int[] orig1 = original.clone();
        int[] orig2 = original.clone();
        for (int i = 0; i < N; i++) {
            insertionSort(orig1, i);
            if (Arrays.equals(orig1, test)) {
                while (i < N && Arrays.equals(orig1, test)) {
                    insertionSort(orig1, i);
                    i++;
                }
                System.out.println("Insertion Sort");
                StringBuilder strb = new StringBuilder();
                for (int t : orig1) {
                    strb.append(t).append(" ");
                }
                strb.deleteCharAt(strb.length() - 1);
                System.out.println(strb);
                return;
            }
        }


        for (int i = N; i >= 1; i--) {
            heapSort(orig2, i);
            if (Arrays.equals(orig2, test)) {
                while (i >= 1 && Arrays.equals(orig2, test)) {
                    heapSort(orig2, i);
                    i--;
                }
                System.out.println("Heap Sort");
                StringBuilder strb = new StringBuilder();
                for (int t : orig2) {
                    strb.append(t).append(" ");
                }
                strb.deleteCharAt(strb.length() - 1);
                System.out.println(strb);
                return;
            }
        }

    }

    private static void swap (int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    private static void insertionSort(int[] a, int cur) {
        if (cur == 0) {
            return;
        }
        if (a[cur] < a[cur - 1]) {
            swap(a, cur, cur - 1);
            insertionSort(a, cur - 1);
        }
    }

    private static void sink(int[] a, int cur, int len) {
        int parent = cur;
        int parent_val = a[parent];
        int max_child = 2 * cur + 1;
        while (max_child < len) {
            if (max_child + 1 < len && a[max_child + 1] > a[max_child]) {
                max_child++;
            }

            if (a[max_child] > parent_val) {
                swap(a, parent, max_child);
            }

            parent = max_child;
            max_child = parent * 2 + 1;
        }
    }

    private static void heapSort(int[] a, int len) {
        if (len == a.length) {
            // initialize
            for (int i = a.length / 2 - 1; i >= 0; i--) {
                sink(a, i, a.length);
            }
        }
        if (a[len - 1] < a[0]) {
            swap(a, 0, len - 1);
            sink(a, 0, len - 1);
        }

    }

}
