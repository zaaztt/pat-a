import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PAT1076 {
    static List<Integer>[] neighbors;
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line1 = br.readLine().split(" ");
        int N = Integer.parseInt(line1[0]);
        int max_l = Integer.parseInt(line1[1]);
        neighbors = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            neighbors[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            String[] line = br.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            if (n == 0) {
                continue;
            } else {
                for (int j = 1; j <= n; j++) {
                    neighbors[Integer.parseInt(line[j])].add(i);
                }
            }
        }
        String[] queries = br.readLine().split(" ");
        int N_q = Integer.parseInt(queries[0]);
        for (int i = 1; i <= N_q; i++) {
            System.out.println(dfs(Integer.parseInt(queries[i]), max_l - 1, 0, new HashSet<>()));
        }
    }

    private static int dfs (int cur, int tar, int level, Set<Integer> set) {
        if (level > tar) {
            return 0;
        }
        if (level == 0) {
            set.add(cur);
        }
        for (int i : neighbors[cur]) {
            set.add(i);
            dfs(i, tar, level + 1, set);
        }
        return set.size() - 1;
    }
}
