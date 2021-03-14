import java.util.*;


public class PAT1003_2 {
    static Map<Integer, Vertex> map = new HashMap<>();
    static int[][] dist_array;
    static int[] dist_to;
    static int[] weight;
    static Vertex start_vertex;
    static Vertex end_vertex;
    static int dif_ways = 1;
    static int shortest_dist = Integer.MAX_VALUE / 2;
    static int max_rescue = 0;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int C1 = sc.nextInt();
        int C2 = sc.nextInt();
        weight = new int[N];
        for (int i = 0; i < N; i++) {
            int w = sc.nextInt();
            weight[i] = w;
            map.put(i, new Vertex(i, w));
        }
        dist_array = new int[N][N];

        dist_to = new int[N];
        Arrays.fill(dist_to, Integer.MAX_VALUE / 2);
        dist_to[C1] = 0;
        for (int i = 0; i < M; i++) {
            int c1 = sc.nextInt();
            int c2 = sc.nextInt();
            Vertex v1 = map.get(c1);
            Vertex v2 = map.get(c2);
            int L = sc.nextInt();
            dist_array[c1][c2] = L;
            dist_array[c2][c1] = L;
            v1.neighbor_list.add(v2);
            v2.neighbor_list.add(v1);
        }
        start_vertex = map.get(C1);
        end_vertex = map.get(C2);

        dfs(start_vertex, new boolean[N], 0, start_vertex.rescue_team_number);
        System.out.println(dif_ways + " " + max_rescue);
    }

    private static void dfs (Vertex cur, boolean[] ba, int dist_sum, int rescue_sum) {
        if (cur == end_vertex) {
            if (dist_sum < shortest_dist) {
                dif_ways = 1;
                shortest_dist = dist_sum;
                max_rescue = rescue_sum;
            } else if (dist_sum == shortest_dist) {
                dif_ways += 1;
                if (rescue_sum > max_rescue) {
                    max_rescue = rescue_sum;
                }
            }
            return;
        }

        ba[cur.val] = true;
        for (Vertex vertex : cur.neighbor_list) {
            if (ba[vertex.val] == true) {
                continue;
            }
            dist_sum += dist_array[cur.val][vertex.val];
            rescue_sum += vertex.rescue_team_number;
            dfs(vertex, ba, dist_sum, rescue_sum);
            dist_sum -= dist_array[cur.val][vertex.val];
            rescue_sum -= vertex.rescue_team_number;
        }
        ba[cur.val] = false;
    }

    private static class Comp2 implements Comparator<Vertex> {

        @Override
        public int compare(Vertex o1, Vertex o2) {
            return dist_to[o1.val] - dist_to[o2.val];
        }
    }

    private static class Vertex {
        int val;
        int rescue_team_number;
        List<Vertex> neighbor_list;
        Vertex(int val, int rescue_team_number) {
            this.val = val;
            this.rescue_team_number = rescue_team_number;
            neighbor_list = new ArrayList<>();
        }

    }
}
