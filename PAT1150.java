import java.util.*;

public class PAT1150 {
    static int shortest_dist = Integer.MAX_VALUE;
    static int shortest_id = 0;
    static int[][] dist_a;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        dist_a = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int dist = sc.nextInt();
            dist_a[v1][v2] = dist;
            dist_a[v2][v1] = dist;
        }

        int K = sc.nextInt();
        List<List<Integer>> query_list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            List<Integer> list = new ArrayList<>();
            int n = sc.nextInt();
            for (int j = 0; j < n; j++) {
                list.add(sc.nextInt());
            }
            query_list.add(new ArrayList<>(list));
        }

        for (int i = 0; i < K; i++) {
            ts_circle(query_list.get(i), i + 1);
        }

        System.out.println("Shortest Dist(" + shortest_id + ") = " + shortest_dist);
    }

    private static void ts_circle (List<Integer> list, int id) {
        int dist = 0;
        boolean[] ba_expected = new boolean[dist_a.length];
        Arrays.fill(ba_expected, true);
        boolean[] ba = new boolean[dist_a.length];
        ba[0] = true;
        for (int i = 0; i < list.size() - 1; i++) {
            int v1 = list.get(i);
            int v2 = list.get(i + 1);
            ba[v1] = true;
            ba[v2] = true;
            if (dist_a[v1][v2] == 0) {
                System.out.println("Path " + id + ": NA (Not a TS cycle)");
                return;
            } else {
                dist += dist_a[v1][v2];
            }
        }

        if (list.get(0) == list.get(list.size() - 1) && Arrays.equals(ba, ba_expected)) {
            if (list.size() == ba.length) {
                System.out.println("Path " + id + ": " + dist + " (TS simple cycle)");
            } else {
                System.out.println("Path " + id + ": " + dist + " (TS cycle)");
            }

            if (dist < shortest_dist) {
                shortest_dist = dist;
                shortest_id = id;
            }

        } else {
            System.out.println("Path " + id + ": " + dist + " (Not a TS cycle)");
        }
    }
}
