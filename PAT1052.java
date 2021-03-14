import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1052 {
    static Map<String, Node> map = new HashMap<>();

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info1 = br.readLine().split(" ");
        int N = Integer.parseInt(info1[0]);
        String start = info1[1];
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            String[] spl = br.readLine().split(" ");
            int val = Integer.parseInt(spl[1]);
            String addr = spl[0];
            String next = spl[2];
            nodes[i] = new Node(val, addr, next);
            map.put(addr, nodes[i]);
        }
        String temp = start;
        while (!temp.equals("-1")) {
            map.get(temp).flag = true;
            temp = map.get(temp).next;
        }
        List<Node> list = new ArrayList<>();
        for (Node n : nodes) {
            if (n.flag == true) list.add(n);
        }
        Collections.sort(list, new Comparator2());
        System.out.println(list.size() + " " + list.get(0).addr);
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                list.get(i).next = list.get(i + 1).addr;
            } else {
                list.get(i).next = "-1";
            }
            System.out.println(list.get(i).addr + " " + list.get(i).val + " " + list.get(i).next);
        }
    }

    private static class Node {
        int val;
        String addr;
        String next;
        boolean flag;
        Node (int val, String addr, String next) {
            this.val = val;
            this.addr = addr;
            this.next = next;
        }
    }

    private static class Comparator2 implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.val - o2.val;
        }
    }
}
