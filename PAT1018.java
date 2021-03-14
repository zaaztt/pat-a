import java.util.*;

public class PAT1018 {
    static int[] dist_f_start_to;
    static int[] capacity_a;
    static int[][] dist_a;
    static int N;
    static int Cmax;
    static Map<Integer, List<Integer>> map = new HashMap<>();
    static List<List<Integer>> shortest_total_list = new ArrayList<>();
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Cmax = sc.nextInt();
        N = sc.nextInt();
        int Sp = sc.nextInt();
        int M = sc.nextInt();
        for (int i = 0; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }
        capacity_a = new int[N + 1];
        dist_a = new int[N + 1][N + 1];
        dist_f_start_to = new int[N + 1];
        int[] pre_to = new int[N + 1];
        int[] weight = new int[N + 1];
        Arrays.fill(dist_f_start_to, Integer.MAX_VALUE / 2);
        dist_f_start_to[0] = 0;
        for (int i = 1; i <= N; i++) {
            capacity_a[i] = sc.nextInt();
        }
        for (int i = 0; i < M; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            int dist = sc.nextInt();
            dist_a[v1][v2] = dist;
            dist_a[v2][v1] = dist;
        }

        dijk();
        dfs_find_way(Sp, new ArrayList<>());

        int[] min_a = new int[2];
        min_a[0] = Integer.MAX_VALUE;
        min_a[1] = Integer.MAX_VALUE;

        List<Integer> ans_list = new ArrayList<>();

        for (List<Integer> list : shortest_total_list) {
            int[] temp_a = find_initial_number(list);
            if (temp_a[0] < min_a[0]) {
                min_a = temp_a;
                ans_list = list;
            } else if (temp_a[0] == min_a[0]) {
                if (temp_a[1] < min_a[1]) {
                    min_a = temp_a;
                    ans_list = list;
                }
            }
        }
        System.out.print(min_a[0] + " ");
        for (int i = 0; i < ans_list.size(); i++) {
            if (i == ans_list.size() - 1) {
                System.out.print(ans_list.get(i));
            } else {
                System.out.print(ans_list.get(i) + "->");
            }
        }
        System.out.println(" " + min_a[1]);
    }

    private static int[] find_initial_number (List<Integer> list) {
        int cur = 0;
        int need = 0;
        for (int i = 1; i < list.size(); i++) {
            if (capacity_a[list.get(i)] + cur > Cmax / 2) {
                cur = capacity_a[list.get(i)] + cur - Cmax / 2;
            } else if (capacity_a[list.get(i)] + cur < Cmax / 2) {
                need += Cmax / 2 - (capacity_a[list.get(i)] + cur);
                cur = 0;
            }
        }
        int[] ans_a = new int[2];
        ans_a[0] = need;
        ans_a[1] = cur;
        return ans_a;
    }

    private static void dfs_find_way (int cur, List<Integer> list) {
        if (cur == 0) {
            list.add(0);
            List<Integer> temp_list = new ArrayList<>(list);
            Collections.reverse(temp_list);
            shortest_total_list.add(new ArrayList<>(temp_list));
            list.remove(list.size() - 1);
            return;
        }
        list.add(cur);
        List<Integer> pre_list = map.get(cur);
        for (int i : pre_list) {
            dfs_find_way(i, list);
        }
        list.remove(list.size() - 1);
    }

    private static void dijk() {

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(0);
        boolean[] visited = new boolean[N + 1];
        while (!pq.isEmpty()) {
            int v = pq.poll();
            if (visited[v]) {
                continue;
            } else {
                visited[v] = true;
            }
            for (int i = 1; i <= N; i++) {
                if (dist_a[v][i] == 0) {
                    continue;
                } else {
                    if (dist_f_start_to[i] > dist_f_start_to[v] + dist_a[v][i]) {
                        dist_f_start_to[i] = dist_f_start_to[v] + dist_a[v][i];
                        map.get(i).clear();
                        map.get(i).add(v);
                        pq.add(i);
                    } else if (dist_f_start_to[i] == dist_f_start_to[v] + dist_a[v][i]) {
                        map.get(i).add(v);
                        pq.add(i);
                    }
                }
            }

        }
    }


}
