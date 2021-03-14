import java.io.IOException;
import java.util.*;

public class PAT1079_2 {
    static Map<Integer, Node> map = new HashMap<>();
    static double unit_price;
    static double r;

    public static void main (String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        unit_price = sc.nextDouble();
        r = sc.nextDouble();
        for (int i = 0; i < N; i++) {
            Node node = new Node(i, 0);
            map.put(i, node);
        }
        Node root = map.get(0);
        root.level = 0;
        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();
            if (n == 0) {
                Node cur = map.get(i);
                cur.retailer = 1;
                cur.m = sc.nextInt();
            } else {
                Node temp = map.get(i);
                for (int j = 0; j < n; j++) {
                    Node cur = map.get(sc.nextInt());
                    temp.neighbors.add(cur);
                }
            }
        }



        System.out.println(String.format("%.1f", setLevel(root)));
    }

    private static class Node {
        int m;
        int id;
        List<Node> neighbors;
        int retailer;
        int level;

        Node(int id, int ret) {
            this.id = id;
            neighbors = new ArrayList<>();
            this.retailer = ret;
        }

        Node(int id, int ret, int m) {
            this.id = id;
            neighbors = new ArrayList<>();
            this.retailer = ret;
            this.m = m;
        }
    }

    private static double setLevel (Node cur) {
        double sum = 0;
        if (cur.retailer == 1) {
            return cur.m * unit_price * Math.pow(1 + r / 100, cur.level);
        }
        for (Node n : cur.neighbors) {
            n.level = cur.level + 1;
            sum += setLevel(n);
        }
        return sum;
    }
}
