import java.util.*;

public class PAT1090 {
    static int count = 0;
    static int max_level = 0;
    static Map<Integer, Retailer> map;
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        double base_price = sc.nextDouble();
        double r = sc.nextDouble();
        map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(i, new Retailer(i));
        }
        int root_id = 0;
        for (int i = 0; i < N; i++) {
            int par_id = sc.nextInt();
            if (par_id == -1) {
                map.get(i).root = 1;
                root_id = i;
            } else {
                map.get(par_id).children.add(map.get(i));
            }
        }
        bt(root_id, 0);
        double final_price = base_price * Math.pow((1 + 0.01 * r) , max_level);
        System.out.println(String.format("%.2f", final_price) + " " + count);
    }

    private static void bt (int cur, int level) {
        if (level > max_level) {
            max_level = level;
            count = 1;
        } else if (level == max_level) {
            count++;
        }
        for (Retailer ret : map.get(cur).children) {
            bt(ret.id, level + 1);
        }
    }

    private static class Retailer {
        int id;
        int root = 0;
        List<Retailer> children;
        Retailer (int id) {
            this.id = id;
            children = new ArrayList<>();
        }
    }
}
