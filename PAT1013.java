import java.util.*;


public class PAT1013 {
    static Map<Integer, Vertex> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            map.put(i, new Vertex(i));
        }
        for (int i = 0; i < M; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            Vertex V1 = map.get(v1);
            Vertex V2 = map.get(v2);
            V1.neighbor_list.add(V2);
            V2.neighbor_list.add(V1);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            list.add(sc.nextInt());
        }
        for (int v : list) {
            int ban_val = v;
            boolean[] visited = new boolean[N + 1];
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                Set<Integer> set = new HashSet<>();
                if (i == ban_val) {
                    continue;
                } else {
                    dfs(i, ban_val, visited, set);
                }
                if (set.size() > 0) {
                    sum++;
                }
            }
            System.out.println(sum - 1);
        }
    }

    private static void dfs (int cur, int ban_val, boolean[] visited, Set<Integer> set) {
        if (visited[cur] || cur == ban_val) {
            return;
        } else {
            visited[cur] = true;
            set.add(cur);
        }

        Vertex cur_vertex = map.get(cur);

        for (Vertex vertex : cur_vertex.neighbor_list) {
            dfs(vertex.val, ban_val, visited, set);
        }
    }

    private static class Vertex {
        int val;
        List<Vertex> neighbor_list;
        Vertex(int val) {
            this.val = val;
            neighbor_list = new ArrayList<>();
        }
    }
}
