import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class PAT1074 {
    static HashMap<Integer, Node> map;
    public static void main (String[] args) throws IOException {
        map = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        int start_addr = Integer.parseInt(info[0]);
        int N = Integer.parseInt(info[1]);
        int rev_N = Integer.parseInt(info[2]);
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            int addr = Integer.parseInt(line[0]);
            int val = Integer.parseInt(line[1]);
            int next = Integer.parseInt(line[2]);
            map.put(addr, new Node(addr, val, next));
        }
        map.put(-1, new Node(-1, -1, -1));
        int n = N / rev_N;
        int temp = 0;
        int final_start = 0;
        int prev = -1;
        for (int i = 0; i < N; i++) {
            if (i % rev_N == 0 && temp < n) {
                start_addr = reverse(start_addr, rev_N);
                if (prev != -1) {
                    map.get(prev).next = start_addr;
                }
                if (i == 0) {
                    final_start = start_addr;
                }
                temp++;
            }
            prev = start_addr;
            if (start_addr == -1) {
                break;
            }
            start_addr = map.get(start_addr).next;
        }

        Node cur = map.get(final_start);
        for (int i = 0; i < N; i++) {
            if (cur.next == -1) {
                System.out.println(String.format("%05d",cur.addr) + " " + cur.val + " " + cur.next);
            } else {
                System.out.println(String.format("%05d",cur.addr) + " " + cur.val + " " + String.format("%05d",cur.next));
            }
            cur = map.get(cur.next);
        }

    }

    private static int reverse (int start, int rev_N) {
        Node first = map.get(start);
        Node temp1 = first;
        Node temp2 = map.get(temp1.next);
        for (int i = 0; i < rev_N - 1; i++) {
            int temp = temp2.next;
            temp2.next = temp1.addr;
            temp1 = temp2;
            temp2 = map.get(temp);
        }
        first.next = temp2.addr;
        return temp1.addr;
    }

    private static class Node {
        int addr;
        int val;
        int next;
        Node (int addr, int val, int next) {
            this.addr = addr;
            this.val = val;
            this.next = next;
        }
    }
}
