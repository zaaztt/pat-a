import java.util.*;

public class PAT1072 {
    static int N;
    static int M;
    static int Ds;
    static String shortest_G = null;
    static double shortest_dist = Double.MAX_VALUE / 2;
    static int shortest_dist_from_G_to_Any = 0;
    public static void main(String[] args) {
        Map<String, Vertex> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int K = sc.nextInt();
        Ds = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            map.put(String.valueOf(i), new Vertex(String.valueOf(i)));
        }
        for (int i = 1; i <= M; i++) {
            String s = "G" + i;
            map.put(s, new Vertex(s));
        }
        for (int i = 0; i < K; i++) {
            String v1 = sc.next();
            String v2 = sc.next();
            int dist = sc.nextInt();
            Vertex vertex1 = map.get(v1);
            Vertex vertex2 = map.get(v2);
            vertex1.neighbor_list.add(vertex2);
            vertex2.neighbor_list.add(vertex1);
            vertex1.dist_map.put(v2, dist);
            vertex2.dist_map.put(v1, dist);
        }
        for (int i = 1; i <= M; i++) {
            String s = "G" + i;
            dijk(map.get(s));
        }
        if (shortest_G == null) {
            System.out.println("No Solution");
        } else {
            System.out.println(shortest_G);
            System.out.println(String.format("%.1f", (double)shortest_dist_from_G_to_Any) + " " + String.format("%.1f", shortest_dist / N));
        }
    }

    private static void dijk (Vertex start_vertex) {
        Map<String, Integer> map_dist_from_start_to = new HashMap<>();
        Map<String, Boolean> map_visited = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            map_dist_from_start_to.put(String.valueOf(i), Integer.MAX_VALUE);
            map_visited.put(String.valueOf(i), false);
        }
        for (int i = 1; i <= M; i++) {
            String s = "G" + i;
            map_dist_from_start_to.put(s, Integer.MAX_VALUE);
            map_visited.put(s, false);
        }
        map_dist_from_start_to.put(start_vertex.name, 0);
        PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return map_dist_from_start_to.get(o1.name) - map_dist_from_start_to.get(o2.name);
            }
        });
        pq.add(start_vertex);
        while (!pq.isEmpty()) {
            Vertex cur_vertex = pq.poll();
            if (map_visited.get(cur_vertex.name) == true) {
                continue;
            }
            map_visited.put(cur_vertex.name, true);
            for (Vertex neighbor_vertex : cur_vertex.neighbor_list) {
                if (map_dist_from_start_to.get(neighbor_vertex.name) > map_dist_from_start_to.get(cur_vertex.name) + cur_vertex.dist_map.get(neighbor_vertex.name)) {
                    map_dist_from_start_to.put(neighbor_vertex.name, map_dist_from_start_to.get(cur_vertex.name) + cur_vertex.dist_map.get(neighbor_vertex.name));
                    pq.add(neighbor_vertex);
                }

            }
        }
        int sum = 0;
        int min = map_dist_from_start_to.get("1");
        boolean isInRange = true;
        for (int i = 1; i <= N; i++) {
            int cur_dist = map_dist_from_start_to.get(String.valueOf(i));
            if (cur_dist > Ds) {
                isInRange = false;
                break;
            }
            sum += cur_dist;
            min = Math.min(min, map_dist_from_start_to.get(String.valueOf(i)));
        }
        if (isInRange) {
            if (min > shortest_dist_from_G_to_Any) {
                shortest_G = start_vertex.name;
                shortest_dist = sum;
                shortest_dist_from_G_to_Any = min;
            } else if (min == shortest_dist_from_G_to_Any) {
                if (sum < shortest_dist) {
                    shortest_G = start_vertex.name;
                    shortest_dist = sum;
                    shortest_dist_from_G_to_Any = min;
                }
            }

        }

    }


    private static class Vertex {
        String name;
        List<Vertex> neighbor_list;
        Map<String, Integer> dist_map;
        Vertex(String name) {
            this.name = name;
            neighbor_list = new ArrayList<>();
            dist_map = new HashMap<>();
        }
    }
}
