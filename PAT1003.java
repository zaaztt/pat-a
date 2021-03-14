import java.util.*;


public class PAT1003 {
    static Map<Integer, Vertex> map = new HashMap<>();
    static int[][] dist_array;
    static int[] dist_to;
    static int[] weight;
    static boolean[] visited;
    static Vertex start_vertex;
    static Vertex end_vertex;
    static int dif_ways = 1;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int C1 = sc.nextInt();
        int C2 = sc.nextInt();
        weight = new int[N];
        visited = new boolean[N];
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

        dijk(start_vertex);
        System.out.println(end_vertex.same_route + " " + weight[end_vertex.val]);
    }

    private static void dijk (Vertex root) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comp2());
        pq.offer(root);

        while (!pq.isEmpty()) {
            Vertex cur = pq.poll();
            if (visited[cur.val]) {
                continue;
            }
            visited[cur.val] = true;
            for (Vertex neighbor_vertex : cur.neighbor_list) {
                if (dist_to[cur.val] + dist_array[cur.val][neighbor_vertex.val] < dist_to[neighbor_vertex.val]) {
                    dist_to[neighbor_vertex.val] = dist_to[cur.val] + dist_array[cur.val][neighbor_vertex.val];
                    weight[neighbor_vertex.val] = weight[cur.val] + neighbor_vertex.rescue_team_number;
                    neighbor_vertex.same_route = cur.same_route;
                    pq.add(neighbor_vertex);
                } else if (dist_to[cur.val] + dist_array[cur.val][neighbor_vertex.val] == dist_to[neighbor_vertex.val]) {
                    if (weight[neighbor_vertex.val] < weight[cur.val] + neighbor_vertex.rescue_team_number) {
                        weight[neighbor_vertex.val] = weight[cur.val] + neighbor_vertex.rescue_team_number;
                    }
                    neighbor_vertex.same_route += cur.same_route;
                    pq.add(neighbor_vertex);
                }
            }
        }
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
        int same_route = 1;
        Vertex(int val, int rescue_team_number) {
            this.val = val;
            this.rescue_team_number = rescue_team_number;
            neighbor_list = new ArrayList<>();
        }

    }
}
