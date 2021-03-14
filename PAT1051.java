import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PAT1051 {
    static Set<List<Integer>> set = new HashSet<>();
    static int M;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info1 = br.readLine().split(" ");
        M = Integer.parseInt(info1[0]);
        int N = Integer.parseInt(info1[1]);
        int N_lines = Integer.parseInt(info1[2]);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }
        dfs(list, 0, new ArrayList<>(), new ArrayList<>());
        /*
        for (List<Integer> l : set) {
            for (int i : l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }

         */
        List<List<Integer>> big_l = new ArrayList<>();
        for (int i = 0; i < N_lines; i++) {
            String[] line = br.readLine().split(" ");
            List<Integer> l = new ArrayList<>();
            for (int j = 0; j < line.length; j++) {
                l.add(Integer.parseInt(line[j]));
            }
            big_l.add(new ArrayList<>(l));
        }
        for (List<Integer> l : big_l) {
            if (set.contains(l)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    }

    private static void dfs (List<Integer> list, int start, List<Integer> cur, List<Integer> poped) {
        if (poped.size() == list.size()) {
            set.add(new ArrayList<>(poped));
            return;
        }
        if (start == list.size()) {
            if (cur.size() != 0) {
                int temp = cur.remove(cur.size() - 1);
                poped.add(temp);
                dfs(list, start, cur, poped);
                cur.add(temp);
                poped.remove(poped.size() - 1);
            }
            return;
        }

        if (cur.size() == 0) {
            cur.add(list.get(start));
            dfs (list, start + 1, cur, poped);
            cur.remove(cur.size() - 1);
            return;
        }
        if (cur.size() == M) {
            int rem = cur.remove(cur.size() - 1);
            poped.add(rem);
            dfs(list, start, cur, poped);
            cur.add(rem);
            poped.remove(poped.size() - 1);
            return;
        }
        cur.add(list.get(start));
        dfs(list,start + 1, cur, poped);
        cur.remove(cur.size() - 1);
        int temp = cur.remove(cur.size() - 1);
        poped.add(temp);
        dfs(list, start, cur, poped);
        cur.add(temp);
        poped.remove(poped.size() - 1);

    }
}
