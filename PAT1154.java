import java.util.*;

public class PAT1154 {
    static Map<Integer, Vertex> map = new HashMap<>();
    static List<List<Vertex>> neighbor_list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            map.put(i, new Vertex(i));
        }

        for (int i = 0; i < M; i++) {
            List<Vertex> list = new ArrayList<>();
            Vertex v1 = map.get(sc.nextInt());
            Vertex v2 = map.get(sc.nextInt());
            list.add(v1);
            list.add(v2);
            neighbor_list.add(new ArrayList<>(list));
        }

        int K = sc.nextInt();
        List<List<Integer>> query_list = new ArrayList<>();
        for (int j = 0; j < K; j++) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                list.add(sc.nextInt());
            }
            query_list.add(new ArrayList<>(list));
        }
        for (List<Integer> list : query_list) {
            k_color(list);
        }
    }

    private static void k_color (List<Integer> color_list) {
        Set<Integer> color_set = new HashSet<>();
        for (int i = 0; i < color_list.size(); i++) {
            map.get(i).color = color_list.get(i);
            color_set.add(color_list.get(i));
        }
        for (List<Vertex> nb_list : neighbor_list) {
            Vertex v1 = nb_list.get(0);
            Vertex v2 = nb_list.get(1);
            if (v1.color == v2.color) {
                System.out.println("No");
                return;
            }
        }
        System.out.println(color_set.size() + "-coloring");

    }

    private static class Vertex {
        int val;
        int color;
        Vertex (int val) {
            this.val = val;
        }
    }

}
