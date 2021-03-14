
import java.util.*;

public class PAT1149 {
    static Map<Integer, Set<Integer>> map = new HashMap<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        for (int i = 0; i < N; i++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();
            if (!map.containsKey(n1)) {
                map.put(n1, new HashSet<>());
            }
            if (!map.containsKey(n2)) {
                map.put(n2, new HashSet<>());
            }
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }
        List<List<Integer>> query_list = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            List<Integer> list = new ArrayList<>();
            int n = sc.nextInt();
            for (int j = 0; j < n; j++) {
                list.add(sc.nextInt());
            }
            query_list.add(new ArrayList<>(list));
        }

        for (int i = 0; i < query_list.size(); i++) {
            List<Integer> list = query_list.get(i);
            if (isCompatible(list)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    private static boolean isCompatible (List<Integer> list) {
        for (int j = 0; j < list.size(); j++) {
            if (map.containsKey(list.get(j))) {
                Set<Integer> set = map.get(list.get(j));
                for (int k = j + 1; k < list.size(); k++) {
                    if (set.contains(list.get(k))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
