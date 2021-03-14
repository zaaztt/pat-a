import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PAT1089 {
    public static void main(String[] args) {
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
        int[] original2 = original.clone();
        for (int i = 0; i < N; i++) {
            insertSort(original, i);
            /*
            if (isEqual(original, test)) {
                insertSort(original, i + 1);
                System.out.println("Insertion Sort");
                StringBuilder strb = new StringBuilder();
                for (int n : original) {
                    strb.append(n).append(" ");
                }
                strb.deleteCharAt(strb.length() - 1);
                System.out.println(strb);
                return;
            }
            */
            System.out.println("Insertion Sort" + " " + i);
            StringBuilder strb = new StringBuilder();
            for (int n : original) {
                strb.append(n).append(" ");
            }
            strb.deleteCharAt(strb.length() - 1);
            System.out.println(strb);
        }
        List<Integer> l;
        int max_level = 1;
        while (Math.pow(2, max_level) < original2.length) {
            max_level++;
        }
        for (int i = 0; i <= max_level; i++) {
            mergeSort(original2, i);
            /*
            if (isEqual(original2, test)) {
                mergeSort(original2, i + 1);
                System.out.println("Merge Sort");
                StringBuilder strb = new StringBuilder();
                for (int n : original2) {
                    strb.append(n).append(" ");
                }
                strb.deleteCharAt(strb.length() - 1);
                System.out.println(strb);
                return;
            }

             */
            System.out.println("Merge Sort" + " " + i);
            StringBuilder strb = new StringBuilder();
            for (int n : original2) {
                strb.append(n).append(" ");
            }
            strb.deleteCharAt(strb.length() - 1);
            System.out.println(strb);
        }
    }

    private static void insertSort (int[] a, int cur) {
        if (cur == a.length) return;
        if (cur == 0) {
            return;
        } else {
            if (a[cur] < a[cur - 1]) {
                int temp = a[cur];
                a[cur] = a[cur - 1];
                a[cur - 1] = temp;
                insertSort(a, cur - 1);
            }
        }
    }

    private static void mergeSort(int[] a, int level) {
        int each_size = (int) Math.pow(2, level);
        if (each_size == 1) {
            return;
        }
        int init = 0;
        while (init + each_size <= a.length) {
            int end2 = init + each_size - 1;
            merge(a, init, (init + end2) / 2, (init + end2) / 2 + 1, end2);
            init += each_size;
        }
        if (each_size > a.length) {
            int end2 = a.length - 1;
            merge(a, init, init + each_size / 2 - 1, init + each_size / 2, end2);
        }
    }

    private static void merge (int[] a, int start1, int end1, int start2, int end2) {
        int s1 = start1;
        int e2 = end2;
        int[] ret = new int[a.length];
        int cnt = start1;
        if (end1 >= a.length || end2 >= a.length) {
            return;
        }
        if (start1 == end2) {
            return;
        }
        while (start1 <= end1 + 1 || start2 <= end2 + 1) {
            if (start1 == end1 + 1 && start2 == end2 + 1) {
                for (int i = s1; i <= e2; i++) {
                    a[i] = ret[i];
                }
                return;
            }
            if (start1 == end1 + 1) {
                ret[cnt] = a[start2];
                cnt++;start2++;
                continue;
            }
            if (start2 == end2 + 1) {
                ret[cnt] = a[start1];
                cnt++;start1++;
                continue;
            }
            if (a[start1] < a[start2]) {
                ret[cnt] = a[start1];
                start1++;cnt++;
                continue;
            } else {
                ret[cnt] = a[start2];
                start2++;cnt++;
            }
        }

    }


    private static boolean isEqual (int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }
}
