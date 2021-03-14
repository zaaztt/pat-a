import java.util.*;

public class PAT1097 {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int start_addr = sc.nextInt();
        int N = sc.nextInt();
        int[] test = new int[10001];

        Map<Integer, Node> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int addr = sc.nextInt();
            int val = sc.nextInt();
            int next_addr = sc.nextInt();
            Node node = new Node(addr, val, next_addr);
            map.put(addr, node);
        }
        List<Node> normal = new ArrayList<>();
        List<Node> removed = new ArrayList<>();
        Node start_node = map.get(start_addr);
        Node cur_node = start_node;
        while (map.containsKey(cur_node.addr)) {
            if (test[Math.abs(cur_node.val)] == 1) {
                removed.add(cur_node);
            } else {
                test[Math.abs(cur_node.val)] = 1;
                normal.add(cur_node);
            }
            if (cur_node.next_addr == -1) {
                break;
            }
            cur_node = map.get(cur_node.next_addr);
        }
        for (int i = 0; i < normal.size(); i++) {
            Node node = normal.get(i);
            if (i == normal.size() - 1) {
                node.next_addr = -1;
                System.out.println(String.format("%05d",normal.get(i).addr) + " " + node.val + " " + normal.get(i).next_addr);
            } else {
                node.next_addr = normal.get(i + 1).addr;
                System.out.println(String.format("%05d",normal.get(i).addr) + " " + node.val + " " + String.format("%05d",normal.get(i).next_addr));
            }
        }



        for (int i = 0; i < removed.size(); i++) {
            Node node = removed.get(i);
            if (i == removed.size() - 1) {
                node.next_addr = -1;
                System.out.println(String.format("%05d",removed.get(i).addr) + " " + node.val + " " + removed.get(i).next_addr);
            } else {
                node.next_addr = removed.get(i + 1).addr;
                System.out.println(String.format("%05d",removed.get(i).addr) + " " + node.val + " " + String.format("%05d",removed.get(i).next_addr));
            }
        }

    }

    private static class Node {
        int addr;
        int val;
        int next_addr;
        Node (int addr, int val, int next_addr) {
            this.addr = addr;
            this.val = val;
            this.next_addr = next_addr;
        }
    }
}
