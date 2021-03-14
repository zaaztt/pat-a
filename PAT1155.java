import java.util.*;

public class PAT1155 {
    static boolean isMaxHeap;
    static boolean isMinHeap;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = sc.nextInt();
        }

        int max = a[0];
        int min = a[0];
        for (int i : a) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }

        if (a[0] == max) {
            isMaxHeap = true;
            isMinHeap = false;
        } else if (a[0] == min) {
            isMinHeap = true;
            isMaxHeap = false;
        }

        dfs(a, 0, new ArrayList<>());
        if (isMaxHeap) {
            System.out.println("Max Heap");
        } else if (isMinHeap) {
            System.out.println("Min Heap");
        } else {
            System.out.println("Not Heap");
        }

    }

    private static void dfs (int[] a, int cur, List<Integer> list) {
        if (cur >= a.length) {
            StringBuilder strb = new StringBuilder();
            for (int i : list) {
                strb.append(i).append(' ');
            }
            System.out.println(strb.deleteCharAt(strb.length() - 1));
            return;
        }
        if (list.size() > 0) {
            if (!isMaxHeap && !isMinHeap) {

            } else if (isMaxHeap) {
                if (a[cur] <= list.get(list.size() - 1)) {

                } else {
                    isMaxHeap = false;
                }
            } else if (isMaxHeap) {
                if (a[cur] >= list.get(list.size() - 1)) {

                } else {
                    isMinHeap = false;
                }
            }
        }
        list.add(a[cur]);

        if (2 * cur + 1 >= a.length || 2 * cur + 2 >= a.length) {
            dfs(a, 2 * cur + 1, list);
        } else {
            dfs(a, 2 * cur + 2, list);
            dfs(a, 2 * cur + 1, list);
        }


        list.remove(list.size() - 1);
    }
}
