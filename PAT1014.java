import java.util.*;

public class PAT1014 {
    static Map<Integer, Deque<Integer>> map_window = new HashMap<>();
    static int N;
    static int M;
    static int K;
    static int Q;
    static int[] leave_time_a;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        Q = sc.nextInt();
        for (int i = 1; i <= N; i++) {
            map_window.put(i, new LinkedList<>());
        }

        int[] time_cost_a = new int[K + 1];
        leave_time_a = new int[K + 1];
        List<Integer> query_list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            time_cost_a[i + 1] = sc.nextInt();
        }
        for (int i = 0; i < Q; i++) {
            query_list.add(sc.nextInt());
        }
        for (int i = 1; i <= K; i++) {
            insert(i, time_cost_a[i]);
        }
        for (int i : query_list) {
            if (leave_time_a[i] - time_cost_a[i] < 17 * 60) {
                System.out.println(String.format("%02d", leave_time_a[i] / 60) + ":" + String.format("%02d", leave_time_a[i] % 60));
            } else {
                System.out.println("Sorry");
            }
        }

    }

    private static void insert (int cur, int time_cost) {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            sum += map_window.get(i).size();
            if (map_window.get(i).size() == M) {
                continue;
            }
            if (map_window.get(i).size() == 0) {
                map_window.get(i).addLast(8 * 60 + time_cost);
                leave_time_a[cur] = 8 * 60 + time_cost;
                return;
            }
            if (i == 1) {
                continue;
            } else {
                if (map_window.get(i).size() < map_window.get(i - 1).size()) {
                    Deque<Integer> deque = map_window.get(i);
                    leave_time_a[cur] = deque.peekLast() + time_cost;
                    deque.addLast(deque.peekLast() + time_cost);
                    return;
                }
            }
        }
        if (sum < N * M) {
            Deque<Integer> deque = map_window.get(1);
            leave_time_a[cur] = deque.peekLast() + time_cost;
            deque.addLast(deque.peekLast() + time_cost);
            return;
        } else {
            Deque<Integer> min_deque = map_window.get(1);
            int min_id = 1;
            for (int i = 2; i <= N; i++) {
                if (map_window.get(i).peekFirst() < min_deque.peekFirst()) {
                    min_deque = map_window.get(i);
                    min_id = i;
                }
            }
            min_deque.pollFirst();
            leave_time_a[cur] = min_deque.peekLast() + time_cost;
            min_deque.addLast(min_deque.peekLast() + time_cost);
        }
    }
}
