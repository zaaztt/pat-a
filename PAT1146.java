import java.util.*;

public class PAT1146 {
    static Node virtual_head = new Node(0);
    static Map<Integer, Node> map = new HashMap<>();
    static Set<Node> root_set = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        for (int i = 1; i <= N; i++) {
            Node node = new Node(i);
            map.put(i, node);
            root_set.add(node);
        }
        for (int i = 0; i < M; i++) {
            Node start_node = map.get(sc.nextInt());
            Node end_node = map.get(sc.nextInt());
            start_node.children_set.add(end_node);
            if (root_set.contains(end_node)) {
                root_set.remove(end_node);
            }
        }

        int K = sc.nextInt();
        List<List<Integer>> query_list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                list.add(sc.nextInt());
            }
            query_list.add(new ArrayList<>(list));
        }
        for (Node root_node : root_set) {
            virtual_head.children_set.add(root_node);
        }
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < query_list.size(); i++) {
            List<Integer> list = query_list.get(i);
            if (isTopo(list)) {
                continue;
            } else {
                strb.append(i).append(" ");
            }
        }
        System.out.println(strb.deleteCharAt(strb.length() - 1));

    }

    private static boolean isTopo (List<Integer> list) {
        Set<Node> pre_level = new HashSet<>();
        Set<Node> cur_level = new HashSet<>();
        pre_level.add(virtual_head);
        for (int i = 0; i < list.size(); i++) {
            Node cur_node = map.get(list.get(i));
            boolean flag1 = false;
            for (Node pre_node : pre_level) {
                if (pre_node.children_set.contains(cur_node)) {
                    cur_level.add(cur_node);
                    flag1 = true;
                    break;
                }
            }
            if (flag1) {
                continue;
            } else {
                boolean flag2 = false;
                for (Node node : cur_level) {
                    if (node.children_set.contains(cur_node)) {
                        flag2 = true;
                        pre_level = new HashSet<>(cur_level);
                        cur_level.clear();
                        cur_level.add(cur_node);
                        break;
                    }
                }
                if (flag2) {
                    continue;
                } else {
                    return false;
                }
            }

        }
        return true;
    }

    private static class Node {
        int val;
        Set<Node> children_set;

        Node (int val) {
            this.val = val;
            children_set = new HashSet<>();

        }
    }

}
